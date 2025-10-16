package com.chargebee.v4.core.responses.event;

import com.chargebee.v4.core.models.event.Event;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EventRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class EventRetrieveResponse extends BaseResponse {
  private final Event event;

  private EventRetrieveResponse(Event event, Response httpResponse) {
    super(httpResponse);

    this.event = event;
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
}
