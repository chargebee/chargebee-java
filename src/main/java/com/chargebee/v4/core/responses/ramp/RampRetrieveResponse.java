package com.chargebee.v4.core.responses.ramp;

import com.chargebee.v4.core.models.ramp.Ramp;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RampRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RampRetrieveResponse extends BaseResponse {
  private final Ramp ramp;

  private RampRetrieveResponse(Ramp ramp, Response httpResponse) {
    super(httpResponse);

    this.ramp = ramp;
  }

  /** Parse JSON response into RampRetrieveResponse object. */
  public static RampRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RampRetrieveResponse object with HTTP response. */
  public static RampRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Ramp ramp = Ramp.fromJson(JsonUtil.getObject(json, "ramp"));

      return new RampRetrieveResponse(ramp, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampRetrieveResponse from JSON", e);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
