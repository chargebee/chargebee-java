package com.chargebee.v4.models.timeMachine.responses;

import com.chargebee.v4.models.timeMachine.TimeMachine;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TravelForwardForTimeMachine operation. Contains the response data
 * from the API.
 */
public final class TravelForwardForTimeMachineResponse extends BaseResponse {
  private final TimeMachine timeMachine;

  private TravelForwardForTimeMachineResponse(Builder builder) {
    super(builder.httpResponse);

    this.timeMachine = builder.timeMachine;
  }

  /** Parse JSON response into TravelForwardForTimeMachineResponse object. */
  public static TravelForwardForTimeMachineResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TravelForwardForTimeMachineResponse object with HTTP response. */
  public static TravelForwardForTimeMachineResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __timeMachineJson = JsonUtil.getObject(json, "time_machine");
      if (__timeMachineJson != null) {
        builder.timeMachine(TimeMachine.fromJson(__timeMachineJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TravelForwardForTimeMachineResponse from JSON", e);
    }
  }

  /** Create a new builder for TravelForwardForTimeMachineResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TravelForwardForTimeMachineResponse. */
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

    public TravelForwardForTimeMachineResponse build() {
      return new TravelForwardForTimeMachineResponse(this);
    }
  }

  /** Get the timeMachine from the response. */
  public TimeMachine getTimeMachine() {
    return timeMachine;
  }
}
