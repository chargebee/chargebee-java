package com.chargebee.v4.services;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.client.request.RequestOptions;
import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.RetryConfig;
import com.chargebee.v4.telemetry.RequestTelemetryContext;
import com.chargebee.v4.telemetry.RequestTelemetryResult;
import com.chargebee.v4.telemetry.TelemetryAdapter;
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

@DisplayName("Per-request telemetry adapter via RequestOptions/withOptions")
class ServiceTelemetryOptionsTest {

  /** Minimal concrete service mirroring the generated services' withOptions override. */
  private static final class TestService extends BaseService<TestService> {
    TestService(ChargebeeClient client) {
      super(client);
    }

    private TestService(ChargebeeClient client, RequestOptions options) {
      super(client, options);
    }

    @Override
    TestService with(RequestOptions newOptions) {
      return new TestService(client, newOptions);
    }

    public TestService withOptions(RequestOptions options) {
      return with(options);
    }

    Response ping() throws ChargebeeException {
      return get("customer", "list", "/customers", null);
    }
  }

  private static final class RecordingTransport implements Transport {
    @Override
    public Response send(Request request) {
      return new Response(200, new HashMap<>(), "{}".getBytes());
    }

    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
      return CompletableFuture.completedFuture(send(request));
    }
  }

  private static ChargebeeClient clientWithoutAdapter() {
    return ChargebeeClient.builder("key_test", "acme")
        .transport(new RecordingTransport())
        .retry(RetryConfig.builder().enabled(false).build())
        .build();
  }

  private static final class RecordingAdapter implements TelemetryAdapter {
    final List<String> events = new ArrayList<>();
    RequestTelemetryContext context;

    @Override
    public Object onRequestStart(RequestTelemetryContext ctx, Map<String, String> requestHeaders) {
      events.add("start");
      context = ctx;
      return "span";
    }

    @Override
    public void onRequestEnd(Object handle, RequestTelemetryResult result) {
      events.add("end");
    }
  }

  @Test
  @DisplayName("adapter set per-request via withOptions(RequestOptions) fires for the call")
  void perRequestAdapterViaWithOptions() throws Exception {
    RecordingAdapter adapter = new RecordingAdapter();
    TestService service = new TestService(clientWithoutAdapter());

    service.withOptions(RequestOptions.builder().telemetryAdapter(adapter).build()).ping();

    assertEquals(List.of("start", "end"), adapter.events);
    assertEquals("chargebee.customer.list", adapter.context.getSpanName());
  }

  @Test
  @DisplayName("no per-request adapter and no client adapter => telemetry skipped")
  void noAdapterSkips() throws Exception {
    RecordingAdapter adapter = new RecordingAdapter();
    TestService service = new TestService(clientWithoutAdapter());

    service.ping();

    assertTrue(adapter.events.isEmpty());
  }
}
