package com.chargebee.v4.models.timeMachine.responses;

import com.chargebee.v4.models.timeMachine.TimeMachine;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TimeMachineTravelForward operation. Contains the response data from
 * the API.
 */
public final class TimeMachineTravelForwardResponse extends BaseResponse {
  private final TimeMachine timeMachine;

  private TimeMachineTravelForwardResponse(Builder builder) {
    super(builder.httpResponse);

    this.timeMachine = builder.timeMachine;
  }

  /** Parse JSON response into TimeMachineTravelForwardResponse object. */
  public static TimeMachineTravelForwardResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TimeMachineTravelForwardResponse object with HTTP response. */
  public static TimeMachineTravelForwardResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __timeMachineJson = JsonUtil.getObject(json, "time_machine");
      if (__timeMachineJson != null) {
        builder.timeMachine(TimeMachine.fromJson(__timeMachineJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TimeMachineTravelForwardResponse from JSON", e);
    }
  }

  /** Create a new builder for TimeMachineTravelForwardResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TimeMachineTravelForwardResponse. */
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

    public TimeMachineTravelForwardResponse build() {
      return new TimeMachineTravelForwardResponse(this);
    }
  }

  /** Get the timeMachine from the response. */
  public TimeMachine getTimeMachine() {
    return timeMachine;
  }

  @Override
  public String toString() {
    return "TimeMachineTravelForwardResponse{" + "timeMachine=" + timeMachine + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimeMachineTravelForwardResponse that = (TimeMachineTravelForwardResponse) o;
    return java.util.Objects.equals(timeMachine, that.timeMachine);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(timeMachine);
  }
}
