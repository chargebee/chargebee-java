package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.APIException;
import com.chargebee.v4.exceptions.NetworkException;
import com.chargebee.v4.exceptions.codes.NotFoundApiErrorCode;
import com.chargebee.v4.internal.RetryConfig;
import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.transport.Transport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Telemetry integration")
class TelemetryExecutorTest {

  private static final class RecordingTransport implements Transport {
    private final List<Request> requests = new ArrayList<>();

    @Override
    public Response send(Request request) {
      requests.add(request);
      return new Response(200, new HashMap<>(), "{}".getBytes());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
      return CompletableFuture.completedFuture(send(request));
    }
  }

  private static final class TelemetryResponseTransport implements Transport {
    @Override
    public Response send(Request request) {
      Map<String, List<String>> headers = new HashMap<>();
      headers.put(
          "X-Chargebee-Telemetry",
          List.of("cb;start_time=@1781280400;time_ms=3800, tp-stripe;pm=card;time_ms=620"));
      return new Response(200, headers, "{}".getBytes());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
      return CompletableFuture.completedFuture(send(request));
    }
  }

  @Test
  @DisplayName("Should skip telemetry when no adapter is configured")
  void shouldSkipWhenNoAdapter() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);
    assertEquals(1, transport.requests.size());
  }

  @Test
  @DisplayName("Should call adapter once per API call including retries")
  void shouldCallAdapterOnceIncludingRetries() {
    RecordingTransport transport = new RecordingTransport();
    List<String> events = new ArrayList<>();
    List<Request> allRequests = new ArrayList<>();
    RequestTelemetryContext[] capturedContext = new RequestTelemetryContext[1];
    RequestTelemetryResult[] capturedResult = new RequestTelemetryResult[1];

    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            events.add("start");
            capturedContext[0] = context;
            requestHeaders.put("traceparent", "00-test-trace");
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {
            events.add("end");
            capturedResult[0] = result;
          }
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(
                new Transport() {
                  private int attempt;

                  @Override
                  public Response send(Request request) {
                    allRequests.add(request);
                    attempt++;
                    if (attempt == 1) {
                      throw new NetworkException("connection reset", new Exception(), request);
                    }
                    transport.requests.add(request);
                    return new Response(200, new HashMap<>(), "{\"list\":[]}".getBytes());
                  }

                  @Override
                  public CompletableFuture<Response> sendAsync(Request request) {
                    return CompletableFuture.completedFuture(send(request));
                  }
                })
            .retry(RetryConfig.builder().enabled(true).maxRetries(2).baseDelayMs(0).build())
            .telemetryAdapter(adapter)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertEquals(List.of("start", "end"), events);
    assertEquals("chargebee.customer.list", capturedContext[0].getSpanName());
    assertEquals("customer", capturedContext[0].getResource());
    assertEquals("list", capturedContext[0].getOperation());
    assertEquals("acme", capturedContext[0].getChargebeeSite());
    assertTrue(capturedContext[0].getStartAttributes().get("url.full").startsWith("https://acme.chargebee.com"));
    assertEquals(200, capturedResult[0].getHttpStatusCode());
    assertEquals(2, allRequests.size());
    assertEquals("00-test-trace", allRequests.get(0).getHeaders().get("traceparent"));
    assertEquals("00-test-trace", allRequests.get(1).getHeaders().get("traceparent"));
  }

  @Test
  @DisplayName("Should capture X-Chargebee-Telemetry response header on success")
  void shouldCaptureChargebeeTelemetryResponseHeader() {
    RequestTelemetryResult[] capturedResult = new RequestTelemetryResult[1];

    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {
            capturedResult[0] = result;
          }
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(new TelemetryResponseTransport())
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(true)
            .build();

    Request request =
        Request.builder()
            .method("POST")
            .url("https://acme.chargebee.com/api/v2/invoices/consolidate")
            .telemetryResource("invoice")
            .telemetryOperation("consolidate")
            .build();

    client.sendWithRetry(request);

    Map<String, Object> endAttributes = capturedResult[0].getEndAttributes();
    assertEquals(
        "cb;start_time=@1781280400;time_ms=3800, tp-stripe;pm=card;time_ms=620",
        endAttributes.get("http.response.header.x-chargebee-telemetry"));
    assertEquals(3800L, endAttributes.get("chargebee.telemetry.cb.time_ms"));
    assertEquals(620L, endAttributes.get("chargebee.telemetry.tp.stripe.time_ms"));
    assertEquals("card", endAttributes.get("chargebee.telemetry.tp.stripe.pm"));
  }

  @Test
  @DisplayName("Should capture chargebee-* request headers and exclude the PII origin family")
  void shouldCaptureChargebeeRequestHeaders() {
    RequestTelemetryContext[] capturedContext = new RequestTelemetryContext[1];

    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            capturedContext[0] = context;
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(new RecordingTransport())
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(true)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .header("chargebee-business-entity-id", "be_123")
            .header("Chargebee-Idempotency-Key", "idem-key-1")
            .header("Authorization", "Basic super-secret")
            .header("X-Custom", "nope")
            .header("chargebee-request-origin-ip", "202.170.207.70")
            .header("chargebee-request-origin-user", "amara@acme.com")
            .build();

    client.sendWithRetry(request);

    Map<String, String> attrs = capturedContext[0].getStartAttributes();
    assertEquals("be_123", attrs.get("http.request.header.chargebee-business-entity-id"));
    assertEquals("idem-key-1", attrs.get("http.request.header.chargebee-idempotency-key"));
    assertNull(attrs.get("http.request.header.authorization"));
    assertNull(attrs.get("http.request.header.x-custom"));
    assertNull(attrs.get("http.request.header.chargebee-request-origin-ip"));
    assertNull(attrs.get("http.request.header.chargebee-request-origin-user"));
    assertFalse(attrs.toString().contains("202.170.207.70"));
    assertFalse(attrs.toString().contains("amara@acme.com"));
  }

  @Test
  @DisplayName("Should record Chargebee error.type on API failures")
  void shouldRecordChargebeeErrorTypeOnFailure() {
    RequestTelemetryResult[] capturedResult = new RequestTelemetryResult[1];

    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {
            capturedResult[0] = result;
          }
        };

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers/cust_1")
            .telemetryResource("customer")
            .telemetryOperation("retrieve")
            .build();

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(
                new Transport() {
                  @Override
                  public Response send(Request transportRequest) {
                    throw new APIException(
                        404,
                        "invalid_request",
                        NotFoundApiErrorCode.RESOURCE_NOT_FOUND,
                        "Not found",
                        "{}",
                        transportRequest,
                        new Response(404, new HashMap<>(), "{}".getBytes()));
                  }

                  @Override
                  public CompletableFuture<Response> sendAsync(Request transportRequest) {
                    CompletableFuture<Response> failed = new CompletableFuture<>();
                    failed.completeExceptionally(
                        new APIException(
                            404,
                            "invalid_request",
                            NotFoundApiErrorCode.RESOURCE_NOT_FOUND,
                            "Not found",
                            "{}",
                            transportRequest,
                            new Response(404, new HashMap<>(), "{}".getBytes())));
                    return failed;
                  }
                })
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(true)
            .build();

    assertThrows(APIException.class, () -> client.sendWithRetry(request));

    Map<String, Object> endAttributes = capturedResult[0].getEndAttributes();
    assertEquals("invalid_request", endAttributes.get(TelemetryAttributeKeys.ERROR_TYPE));
    assertEquals("invalid_request", endAttributes.get(TelemetryAttributeKeys.CHARGEBEE_ERROR_TYPE));
    assertEquals(404, capturedResult[0].getHttpStatusCode());
  }

  @Test
  @DisplayName("Should not fail API call when adapter hooks throw")
  void shouldNotFailWhenAdapterThrows() {
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(new RecordingTransport())
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(
                new TelemetryAdapter() {
                  @Override
                  public Object onRequestStart(
                      RequestTelemetryContext context, Map<String, String> requestHeaders) {
                    throw new RuntimeException("start failed");
                  }

                  @Override
                  public void onRequestEnd(Object handle, RequestTelemetryResult result) {
                    throw new RuntimeException("end failed");
                  }
                })
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    assertDoesNotThrow(() -> client.sendWithRetry(request));
  }

  @Test
  @DisplayName("Should log adapter failures at WARNING (not SEVERE) and still return the response")
  void shouldLogAdapterFailureAtWarning() {
    Logger logger = Logger.getLogger(TelemetryExecutor.class.getName());
    List<LogRecord> records = new ArrayList<>();
    Handler captor =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            records.add(record);
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };
    Level previousLevel = logger.getLevel();
    boolean previousUseParent = logger.getUseParentHandlers();
    logger.addHandler(captor);
    logger.setLevel(Level.ALL);
    // Suppress propagation so the captured exception is not also printed to the console during tests.
    logger.setUseParentHandlers(false);

    try {
      ChargebeeClient client =
          ChargebeeClient.builder("key_test", "acme")
              .transport(new RecordingTransport())
              .retry(RetryConfig.builder().enabled(false).build())
              .telemetryAdapter(
                  new TelemetryAdapter() {
                    @Override
                    public Object onRequestStart(
                        RequestTelemetryContext context, Map<String, String> requestHeaders) {
                      throw new RuntimeException("start failed");
                    }

                    @Override
                    public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
                  })
              .build();

      Request request =
          Request.builder()
              .method("GET")
              .url("https://acme.chargebee.com/api/v2/customers")
              .telemetryResource("customer")
              .telemetryOperation("list")
              .build();

      Response response = client.sendWithRetry(request);

      assertEquals(200, response.getStatusCode());
      assertTrue(
          records.stream()
              .anyMatch(
                  r ->
                      r.getLevel() == Level.WARNING
                          && r.getMessage().contains("onRequestStart failed")),
          "expected a WARNING log for the adapter failure");
      assertTrue(
          records.stream().noneMatch(r -> r.getLevel() == Level.SEVERE),
          "telemetry failures must not be logged at SEVERE");
    } finally {
      logger.removeHandler(captor);
      logger.setLevel(previousLevel);
      logger.setUseParentHandlers(previousUseParent);
    }
  }

  @Test
  @DisplayName("Should send Prefer chargebee-telemetry=include when preferChargebeeTelemetry is true")
  void shouldSendPreferHeaderWhenOptedIn() {
    RecordingTransport transport = new RecordingTransport();
    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(true)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertEquals(1, transport.requests.size());
    assertEquals(
        TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_PREFER_VALUE,
        transport.requests.get(0).getHeaders().get(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_PREFER_HEADER));
  }

  @Test
  @DisplayName("Should not override an existing Prefer request header")
  void shouldNotOverrideExistingPreferHeader() {
    RecordingTransport transport = new RecordingTransport();
    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(true)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .header("Prefer", "respond-async")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertEquals("respond-async", transport.requests.get(0).getHeaders().get("Prefer"));
  }

  @Test
  @DisplayName("Should not send Prefer by default when telemetry adapter is configured")
  void shouldNotSendPreferByDefault() {
    RecordingTransport transport = new RecordingTransport();
    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertFalse(transport.requests.get(0).getHeaders().containsKey("Prefer"));
  }

  @Test
  @DisplayName("Should not send Prefer when preferChargebeeTelemetry is false")
  void shouldNotSendPreferWhenDisabled() {
    RecordingTransport transport = new RecordingTransport();
    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span-1";
          }

          @Override
          public void onRequestEnd(Object handle, RequestTelemetryResult result) {}
        };

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .telemetryAdapter(adapter)
            .preferChargebeeTelemetry(false)
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertFalse(transport.requests.get(0).getHeaders().containsKey("Prefer"));
  }

  @Test
  @DisplayName("Should not send Prefer when telemetry adapter is not configured")
  void shouldNotSendPreferWhenNoAdapter() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .telemetryResource("customer")
            .telemetryOperation("list")
            .build();

    client.sendWithRetry(request);

    assertFalse(transport.requests.get(0).getHeaders().containsKey("Prefer"));
  }
}
