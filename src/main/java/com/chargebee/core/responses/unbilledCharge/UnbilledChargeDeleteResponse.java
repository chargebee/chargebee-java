package com.chargebee.core.responses.unbilledCharge;

import com.chargebee.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UnbilledChargeDelete operation. Contains the response data from the
 * API.
 */
public final class UnbilledChargeDeleteResponse {

  private final UnbilledCharge unbilledCharge;

  private UnbilledChargeDeleteResponse(Builder builder) {

    this.unbilledCharge = builder.unbilledCharge;
  }

  /** Parse JSON response into UnbilledChargeDeleteResponse object. */
  public static UnbilledChargeDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __unbilledChargeJson = JsonUtil.getObject(json, "unbilled_charge");
      if (__unbilledChargeJson != null) {
        builder.unbilledCharge(UnbilledCharge.fromJson(__unbilledChargeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeDeleteResponse. */
  public static class Builder {

    private UnbilledCharge unbilledCharge;

    private Builder() {}

    public Builder unbilledCharge(UnbilledCharge unbilledCharge) {
      this.unbilledCharge = unbilledCharge;
      return this;
    }

    public UnbilledChargeDeleteResponse build() {
      return new UnbilledChargeDeleteResponse(this);
    }
  }

  /** Get the unbilledCharge from the response. */
  public UnbilledCharge getUnbilledCharge() {
    return unbilledCharge;
  }
}
