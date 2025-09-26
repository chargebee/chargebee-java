package com.chargebee.core.responses.ramp;

import com.chargebee.core.models.ramp.Ramp;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for RampRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RampRetrieveResponse {

  private final Ramp ramp;

  private RampRetrieveResponse(Ramp ramp) {

    this.ramp = ramp;
  }

  /** Parse JSON response into RampRetrieveResponse object. */
  public static RampRetrieveResponse fromJson(String json) {
    try {

      Ramp ramp = Ramp.fromJson(JsonUtil.getObject(json, "ramp"));

      return new RampRetrieveResponse(ramp);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampRetrieveResponse from JSON", e);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
