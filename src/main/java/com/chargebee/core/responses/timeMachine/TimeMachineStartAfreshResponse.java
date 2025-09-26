package com.chargebee.core.responses.timeMachine;

import com.chargebee.core.models.timeMachine.TimeMachine;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for TimeMachineStartAfresh operation. Contains the response data from
 * the API.
 */
public final class TimeMachineStartAfreshResponse {

  private final TimeMachine timeMachine;

  private TimeMachineStartAfreshResponse(Builder builder) {

    this.timeMachine = builder.timeMachine;
  }

  /** Parse JSON response into TimeMachineStartAfreshResponse object. */
  public static TimeMachineStartAfreshResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __timeMachineJson = JsonUtil.getObject(json, "time_machine");
      if (__timeMachineJson != null) {
        builder.timeMachine(TimeMachine.fromJson(__timeMachineJson));
      }

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

    private Builder() {}

    public Builder timeMachine(TimeMachine timeMachine) {
      this.timeMachine = timeMachine;
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
}
