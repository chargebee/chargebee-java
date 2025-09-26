package com.chargebee.core.responses.timeMachine;

import com.chargebee.core.models.timeMachine.TimeMachine;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for TimeMachineTravelForward operation. Contains the response data from
 * the API.
 */
public final class TimeMachineTravelForwardResponse {

  private final TimeMachine timeMachine;

  private TimeMachineTravelForwardResponse(Builder builder) {

    this.timeMachine = builder.timeMachine;
  }

  /** Parse JSON response into TimeMachineTravelForwardResponse object. */
  public static TimeMachineTravelForwardResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __timeMachineJson = JsonUtil.getObject(json, "time_machine");
      if (__timeMachineJson != null) {
        builder.timeMachine(TimeMachine.fromJson(__timeMachineJson));
      }

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

    private Builder() {}

    public Builder timeMachine(TimeMachine timeMachine) {
      this.timeMachine = timeMachine;
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
}
