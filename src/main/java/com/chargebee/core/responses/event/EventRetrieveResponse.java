package com.chargebee.core.responses.event;

import com.chargebee.core.models.event.Event;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EventRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class EventRetrieveResponse {

  private final Event event;

  private EventRetrieveResponse(Event event) {

    this.event = event;
  }

  /** Parse JSON response into EventRetrieveResponse object. */
  public static EventRetrieveResponse fromJson(String json) {
    try {

      Event event = Event.fromJson(JsonUtil.getObject(json, "event"));

      return new EventRetrieveResponse(event);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EventRetrieveResponse from JSON", e);
    }
  }

  /** Get the event from the response. */
  public Event getEvent() {
    return event;
  }
}
