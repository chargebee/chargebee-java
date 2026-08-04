package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.APIException;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SDK telemetry header emission")
class SdkTelemetryEmitterTest {

  private static Request listCustomersRequest() {
    return Request.builder()
        .method("GET")
        .url("https://acme.chargebee.com/api/v2/customers")
        .telemetryResource("customer")
        .telemetryOperation("list")
        .build();
  }

  private static Request retrieveCustomerRequest() {
    return Request.builder()
        .method("GET")
        .url("https://acme.chargebee.com/api/v2/customers/cust_1")
        .telemetryResource("customer")
        .telemetryOperation("retrieve")
        .build();
  }

  private static final class RecordingTransport implements Transport {
    private final List<Request> requests = new ArrayList<>();
    private final Map<String, List<String>> responseHeaders;

    RecordingTransport() {
      this(new HashMap<>());
    }

    RecordingTransport(Map<String, List<String>> responseHeaders) {
      this.responseHeaders = responseHeaders;
    }

    @Override
    public Response send(Request request) {
      requests.add(request);
      return new Response(200, responseHeaders, "{\"list\":[]}".getBytes());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
      return CompletableFuture.completedFuture(send(request));
    }
  }

  @Test
  @DisplayName("Should omit header on first call and attach N+1 header on second call")
  void shouldEmitNPlusOneHeader() {
    Map<String, List<String>> responseHeaders = new HashMap<>();
    responseHeaders.put("chargebee-request-id", List.of("req_abc123"));
    RecordingTransport transport = new RecordingTransport(responseHeaders);

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertNull(transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));

    String header = transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertNotNull(header);
    assertTrue(header.contains("resource=customer;operation=list"));
    assertTrue(header.contains("start_time=@"));
    assertTrue(header.contains("http_status=200"));
    assertTrue(header.contains("request_id=\"req_abc123\""));
  }

  @Test
  @DisplayName("Should not attach or record when sdk telemetry is disabled")
  void shouldRespectOptOut() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .sdkTelemetryEnabled(false)
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertNull(transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should record failure details for the next header")
  void shouldRecordFailureOnNextHeader() {
    RecordingTransport successTransport =
        new RecordingTransport(Map.of("chargebee-request-id", List.of("req_fail")));

    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(
                new Transport() {
                  private int attempt;

                  @Override
                  public Response send(Request request) {
                    attempt++;
                    if (attempt == 1) {
                      throw new APIException(
                          404,
                          "invalid_request",
                          NotFoundApiErrorCode.RESOURCE_NOT_FOUND,
                          "Not found",
                          "{}",
                          request,
                          new Response(404, Map.of("chargebee-request-id", List.of("req_fail")), "{}".getBytes()));
                    }
                    successTransport.requests.add(request);
                    return successTransport.send(request);
                  }

                  @Override
                  public CompletableFuture<Response> sendAsync(Request request) {
                    return CompletableFuture.completedFuture(send(request));
                  }
                })
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    assertThrows(APIException.class, () -> client.sendWithRetry(retrieveCustomerRequest()));
    client.sendWithRetry(listCustomersRequest());

    String header = successTransport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertNotNull(header);
    assertTrue(header.contains("operation=retrieve"));
    assertTrue(header.contains("http_status=404"));
    assertTrue(header.contains("error_code=\"resource_not_found\""));
  }

  @Test
  @DisplayName("Should emit feature tokens independently of OTel adapter")
  void shouldEmitFeatureTokensWithoutOtelAdapter() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(true).maxRetries(1).build())
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    String header = transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertNotNull(header);
    assertTrue(header.contains(SdkTelemetryHeader.FT_RETRY_CONFIG));
    assertTrue(header.contains(SdkTelemetryHeader.FT_CUSTOM_TRANSPORT));
    assertFalse(header.contains(SdkTelemetryHeader.FT_TELEMETRY_ADAPTER));
  }

  @Test
  @DisplayName("Should emit ft-telemetry_adapter when OTel adapter is configured")
  void shouldEmitTelemetryAdapterFeatureToken() {
    RecordingTransport transport = new RecordingTransport();
    TelemetryAdapter adapter =
        new TelemetryAdapter() {
          @Override
          public Object onRequestStart(
              RequestTelemetryContext context, Map<String, String> requestHeaders) {
            return "span";
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

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    String header = transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertNotNull(header);
    assertTrue(header.contains(SdkTelemetryHeader.FT_TELEMETRY_ADAPTER));
  }

  @Test
  @DisplayName("Should support async N+1 emission")
  void shouldEmitAsyncNPlusOneHeader() throws Exception {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    client.sendWithRetryAsync(listCustomersRequest()).get();
    client.sendWithRetryAsync(retrieveCustomerRequest()).get();

    assertNull(transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertNotNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should keep telemetry state per client instance, not per configuration")
  void shouldNotShareStateBetweenIdenticallyConfiguredClients() {
    RecordingTransport transport = new RecordingTransport();
    RetryConfig retry = RetryConfig.builder().enabled(false).build();

    ChargebeeClient first =
        ChargebeeClient.builder("key_test", "acme").transport(transport).retry(retry).build();
    ChargebeeClient second =
        ChargebeeClient.builder("key_test", "acme").transport(transport).retry(retry).build();

    first.sendWithRetry(listCustomersRequest());
    second.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should not update snapshot when telemetry metadata is missing")
  void shouldSkipSnapshotWithoutTelemetryMetadata() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    Request withoutMetadata =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers")
            .build();

    client.sendWithRetry(withoutMetadata);
    client.sendWithRetry(listCustomersRequest());

    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }
}
