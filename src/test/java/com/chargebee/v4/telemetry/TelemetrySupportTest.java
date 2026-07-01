package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.exceptions.APIException;
import com.chargebee.v4.exceptions.codes.NotFoundApiErrorCode;
import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TelemetrySupport")
class TelemetrySupportTest {

  @Test
  @DisplayName("Should promote chargebee-* headers and exclude the PII origin family")
  void shouldBuildRequestHeaderSpanAttributes() {
    Map<String, String> headers = new HashMap<>();
    headers.put("chargebee-foo", "bar");
    headers.put("Chargebee-Idempotency-Key", "idem-key-1");
    headers.put("Authorization", "Basic secret");
    headers.put("chargebee-request-origin-ip", "202.170.207.70");

    Map<String, String> attributes =
        TelemetrySupport.buildRequestHeaderSpanAttributes(headers);

    assertEquals("bar", attributes.get("http.request.header.chargebee-foo"));
    assertEquals("idem-key-1", attributes.get("http.request.header.chargebee-idempotency-key"));
    assertFalse(attributes.containsKey("http.request.header.authorization"));
    assertFalse(attributes.containsKey("http.request.header.chargebee-request-origin-ip"));
  }

  @Test
  @DisplayName("Should set error.type from Chargebee API error type, not HTTP status")
  void shouldSetErrorTypeFromChargebeeApiErrorType() {
    Request request =
        Request.builder()
            .method("GET")
            .url("https://acme.chargebee.com/api/v2/customers/cust_1")
            .build();
    Response response = new Response(404, new HashMap<>(), "{}".getBytes());
    APIException apiException =
        new APIException(
            404,
            "invalid_request",
            NotFoundApiErrorCode.RESOURCE_NOT_FOUND,
            "Not found",
            "{}",
            request,
            response);

    RequestTelemetryError error = TelemetrySupport.extractRequestTelemetryError(apiException);
    Map<String, Object> attributes =
        TelemetrySupport.buildRequestEndSpanAttributes(
            new TelemetrySupport.RequestTelemetryResultInput(404, 10L, error));

    assertEquals("invalid_request", attributes.get(TelemetryAttributeKeys.ERROR_TYPE));
    assertEquals("invalid_request", attributes.get(TelemetryAttributeKeys.CHARGEBEE_ERROR_TYPE));
    assertEquals("resource_not_found", attributes.get(TelemetryAttributeKeys.CHARGEBEE_ERROR_CODE));
    assertFalse(attributes.containsValue("404"));
  }
}
