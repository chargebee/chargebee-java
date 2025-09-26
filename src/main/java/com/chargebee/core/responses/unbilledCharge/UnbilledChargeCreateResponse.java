package com.chargebee.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UnbilledChargeCreate operation. Contains the response data from the
 * API.
 */
public final class UnbilledChargeCreateResponse {

  private final List<UnbilledCharge> unbilledCharges;

  private UnbilledChargeCreateResponse(Builder builder) {

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into UnbilledChargeCreateResponse object. */
  public static UnbilledChargeCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeCreateResponse. */
  public static class Builder {

    private List<UnbilledCharge> unbilledCharges;

    private Builder() {}

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public UnbilledChargeCreateResponse build() {
      return new UnbilledChargeCreateResponse(this);
    }
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }
}
