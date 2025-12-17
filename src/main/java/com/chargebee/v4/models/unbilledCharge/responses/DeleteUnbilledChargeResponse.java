package com.chargebee.v4.models.unbilledCharge.responses;

import com.chargebee.v4.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteUnbilledCharge operation. Contains the response data from the
 * API.
 */
public final class DeleteUnbilledChargeResponse extends BaseResponse {
  private final UnbilledCharge unbilledCharge;

  private DeleteUnbilledChargeResponse(Builder builder) {
    super(builder.httpResponse);

    this.unbilledCharge = builder.unbilledCharge;
  }

  /** Parse JSON response into DeleteUnbilledChargeResponse object. */
  public static DeleteUnbilledChargeResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteUnbilledChargeResponse object with HTTP response. */
  public static DeleteUnbilledChargeResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __unbilledChargeJson = JsonUtil.getObject(json, "unbilled_charge");
      if (__unbilledChargeJson != null) {
        builder.unbilledCharge(UnbilledCharge.fromJson(__unbilledChargeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteUnbilledChargeResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteUnbilledChargeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteUnbilledChargeResponse. */
  public static class Builder {

    private UnbilledCharge unbilledCharge;

    private Response httpResponse;

    private Builder() {}

    public Builder unbilledCharge(UnbilledCharge unbilledCharge) {
      this.unbilledCharge = unbilledCharge;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteUnbilledChargeResponse build() {
      return new DeleteUnbilledChargeResponse(this);
    }
  }

  /** Get the unbilledCharge from the response. */
  public UnbilledCharge getUnbilledCharge() {
    return unbilledCharge;
  }
}
