package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.client.ChargebeeClient;
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

  /**
   * Extends {@link com.chargebee.v4.transport.DefaultTransport} so {@code ct} is not reported, while
   * still capturing outbound requests.
   */
  private static final class RecordingDefaultTransport
      extends com.chargebee.v4.transport.DefaultTransport {
    private final List<Request> requests = new ArrayList<>();

    RecordingDefaultTransport() {
      super(com.chargebee.v4.transport.TransportConfig.builder().apiKey("key_test").build());
    }

    @Override
    public Response send(Request request) {
      requests.add(request);
      return new Response(200, Map.of(), "{\"list\":[]}".getBytes());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
      return CompletableFuture.completedFuture(send(request));
    }
  }

  @Test
  @DisplayName("Should omit header when no features are enabled")
  void shouldOmitHeaderWhenNoFeatures() {
    RecordingDefaultTransport transport = new RecordingDefaultTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(false).build())
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertNull(transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should not attach when sdk telemetry is disabled")
  void shouldRespectOptOut() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(true).maxRetries(1).build())
            .sdkTelemetryEnabled(false)
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertNull(transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should emit keyed feature codes once on the first call only")
  void shouldEmitFeaturesOncePerClient() {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(true).maxRetries(1).build())
            .build();

    client.sendWithRetry(listCustomersRequest());
    client.sendWithRetry(retrieveCustomerRequest());

    String first = transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertNotNull(first);
    assertEquals("f;ct;rc", first);
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should emit ta when OTel adapter is configured")
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

    String header = transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME);
    assertEquals("f;ta;ct", header);
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should support async once-per-client emission")
  void shouldEmitAsyncOncePerClient() throws Exception {
    RecordingTransport transport = new RecordingTransport();
    ChargebeeClient client =
        ChargebeeClient.builder("key_test", "acme")
            .transport(transport)
            .retry(RetryConfig.builder().enabled(true).maxRetries(1).build())
            .build();

    client.sendWithRetryAsync(listCustomersRequest()).get();
    client.sendWithRetryAsync(retrieveCustomerRequest()).get();

    assertEquals(
        "f;ct;rc", transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertNull(transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }

  @Test
  @DisplayName("Should keep telemetry state per client instance")
  void shouldNotShareStateBetweenIdenticallyConfiguredClients() {
    RecordingTransport transport = new RecordingTransport();
    RetryConfig retry = RetryConfig.builder().enabled(true).maxRetries(1).build();

    ChargebeeClient first =
        ChargebeeClient.builder("key_test", "acme").transport(transport).retry(retry).build();
    ChargebeeClient second =
        ChargebeeClient.builder("key_test", "acme").transport(transport).retry(retry).build();

    first.sendWithRetry(listCustomersRequest());
    second.sendWithRetry(retrieveCustomerRequest());

    assertEquals(2, transport.requests.size());
    assertEquals(
        "f;ct;rc", transport.requests.get(0).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
    assertEquals(
        "f;ct;rc", transport.requests.get(1).getHeaders().get(SdkTelemetryHeader.HEADER_NAME));
  }
}
