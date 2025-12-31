package com.chargebee.v4.models.event.responses;

import com.chargebee.v4.models.event.Event;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EventRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class EventRetrieveResponse extends BaseResponse {
  private final Event event;

  private EventRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.event = builder.event;
  }

  /** Parse JSON response into EventRetrieveResponse object. */
  public static EventRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EventRetrieveResponse object with HTTP response. */
  public static EventRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __eventJson = JsonUtil.getObject(json, "event");
      if (__eventJson != null) {
        builder.event(Event.fromJson(__eventJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EventRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for EventRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EventRetrieveResponse. */
  public static class Builder {

    private Event event;

    private Response httpResponse;

    private Builder() {}

    public Builder event(Event event) {
      this.event = event;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EventRetrieveResponse build() {
      return new EventRetrieveResponse(this);
    }
  }

  /** Get the event from the response. */
  public Event getEvent() {
    return event;
  }

  @Override
  public String toString() {
    return "EventRetrieveResponse{" + "event=" + event + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EventRetrieveResponse that = (EventRetrieveResponse) o;
    return java.util.Objects.equals(event, that.event);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(event);
  }
}
