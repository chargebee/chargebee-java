package com.chargebee.v4.core.responses.timeMachine;

import com.chargebee.v4.core.models.timeMachine.TimeMachine;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for TimeMachineRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TimeMachineRetrieveResponse {

  private final TimeMachine timeMachine;

  private TimeMachineRetrieveResponse(TimeMachine timeMachine) {

    this.timeMachine = timeMachine;
  }

  /** Parse JSON response into TimeMachineRetrieveResponse object. */
  public static TimeMachineRetrieveResponse fromJson(String json) {
    try {

      TimeMachine timeMachine = TimeMachine.fromJson(JsonUtil.getObject(json, "time_machine"));

      return new TimeMachineRetrieveResponse(timeMachine);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TimeMachineRetrieveResponse from JSON", e);
    }
  }

  /** Get the timeMachine from the response. */
  public TimeMachine getTimeMachine() {
    return timeMachine;
  }
}
