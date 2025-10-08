package com.chargebee.v4.core.responses.timeMachine;

import com.chargebee.v4.core.models.timeMachine.TimeMachine;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TimeMachineRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TimeMachineRetrieveResponse {

  private final TimeMachine timeMachine;

  private final Response httpResponse;

  private TimeMachineRetrieveResponse(TimeMachine timeMachine, Response httpResponse) {

    this.timeMachine = timeMachine;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into TimeMachineRetrieveResponse object. */
  public static TimeMachineRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TimeMachineRetrieveResponse object with HTTP response. */
  public static TimeMachineRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      TimeMachine timeMachine = TimeMachine.fromJson(JsonUtil.getObject(json, "time_machine"));

      return new TimeMachineRetrieveResponse(timeMachine, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TimeMachineRetrieveResponse from JSON", e);
    }
  }

  /** Get the timeMachine from the response. */
  public TimeMachine getTimeMachine() {
    return timeMachine;
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
