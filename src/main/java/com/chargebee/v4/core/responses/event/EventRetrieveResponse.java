package com.chargebee.v4.core.responses.event;

import com.chargebee.v4.core.models.event.Event;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EventRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class EventRetrieveResponse {

  private final Event event;

  private final Response httpResponse;

  private EventRetrieveResponse(Event event, Response httpResponse) {

    this.event = event;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into EventRetrieveResponse object. */
  public static EventRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EventRetrieveResponse object with HTTP response. */
  public static EventRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Event event = Event.fromJson(JsonUtil.getObject(json, "event"));

      return new EventRetrieveResponse(event, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EventRetrieveResponse from JSON", e);
    }
  }

  /** Get the event from the response. */
  public Event getEvent() {
    return event;
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
