package com.chargebee.v4.models.timeMachine.responses;

import com.chargebee.v4.models.timeMachine.TimeMachine;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TimeMachineRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TimeMachineRetrieveResponse extends BaseResponse {
  private final TimeMachine timeMachine;

  private TimeMachineRetrieveResponse(TimeMachine timeMachine, Response httpResponse) {
    super(httpResponse);

    this.timeMachine = timeMachine;
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
}
