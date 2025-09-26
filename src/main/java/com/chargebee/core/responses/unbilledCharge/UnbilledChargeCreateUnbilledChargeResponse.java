package com.chargebee.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UnbilledChargeCreateUnbilledCharge operation. Contains the response
 * data from the API.
 */
public final class UnbilledChargeCreateUnbilledChargeResponse {

  private final List<UnbilledCharge> unbilledCharges;

  private UnbilledChargeCreateUnbilledChargeResponse(Builder builder) {

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into UnbilledChargeCreateUnbilledChargeResponse object. */
  public static UnbilledChargeCreateUnbilledChargeResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargeCreateUnbilledChargeResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeCreateUnbilledChargeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeCreateUnbilledChargeResponse. */
  public static class Builder {

    private List<UnbilledCharge> unbilledCharges;

    private Builder() {}

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public UnbilledChargeCreateUnbilledChargeResponse build() {
      return new UnbilledChargeCreateUnbilledChargeResponse(this);
    }
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }
}
