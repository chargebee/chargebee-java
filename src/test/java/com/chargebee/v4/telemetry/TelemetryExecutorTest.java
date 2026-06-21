package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.NetworkException;
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
}
