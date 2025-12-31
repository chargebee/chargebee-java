package com.chargebee.v4.models.timeMachine.responses;

import com.chargebee.v4.models.timeMachine.TimeMachine;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TimeMachineStartAfresh operation. Contains the response data from
 * the API.
 */
public final class TimeMachineStartAfreshResponse extends BaseResponse {
  private final TimeMachine timeMachine;

  private TimeMachineStartAfreshResponse(Builder builder) {
    super(builder.httpResponse);

    this.timeMachine = builder.timeMachine;
  }

  /** Parse JSON response into TimeMachineStartAfreshResponse object. */
  public static TimeMachineStartAfreshResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TimeMachineStartAfreshResponse object with HTTP response. */
  public static TimeMachineStartAfreshResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __timeMachineJson = JsonUtil.getObject(json, "time_machine");
      if (__timeMachineJson != null) {
        builder.timeMachine(TimeMachine.fromJson(__timeMachineJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TimeMachineStartAfreshResponse from JSON", e);
    }
  }

  /** Create a new builder for TimeMachineStartAfreshResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TimeMachineStartAfreshResponse. */
  public static class Builder {

    private TimeMachine timeMachine;

    private Response httpResponse;

    private Builder() {}

    public Builder timeMachine(TimeMachine timeMachine) {
      this.timeMachine = timeMachine;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public TimeMachineStartAfreshResponse build() {
      return new TimeMachineStartAfreshResponse(this);
    }
  }

  /** Get the timeMachine from the response. */
  public TimeMachine getTimeMachine() {
    return timeMachine;
  }

  @Override
  public String toString() {
    return "TimeMachineStartAfreshResponse{" + "timeMachine=" + timeMachine + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimeMachineStartAfreshResponse that = (TimeMachineStartAfreshResponse) o;
    return java.util.Objects.equals(timeMachine, that.timeMachine);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(timeMachine);
  }
}
