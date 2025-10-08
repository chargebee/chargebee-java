package com.chargebee.v4.core.responses.ramp;

import com.chargebee.v4.core.models.ramp.Ramp;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RampRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RampRetrieveResponse {

  private final Ramp ramp;

  private final Response httpResponse;

  private RampRetrieveResponse(Ramp ramp, Response httpResponse) {

    this.ramp = ramp;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into RampRetrieveResponse object. */
  public static RampRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RampRetrieveResponse object with HTTP response. */
  public static RampRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Ramp ramp = Ramp.fromJson(JsonUtil.getObject(json, "ramp"));

      return new RampRetrieveResponse(ramp, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampRetrieveResponse from JSON", e);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
