package com.chargebee.core.responses.subscription;

import java.util.List;

import com.chargebee.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionImportUnbilledCharges operation. Contains the response
 * data from the API.
 */
public final class SubscriptionImportUnbilledChargesResponse {

  private final List<UnbilledCharge> unbilledCharges;

  private SubscriptionImportUnbilledChargesResponse(Builder builder) {

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into SubscriptionImportUnbilledChargesResponse object. */
  public static SubscriptionImportUnbilledChargesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionImportUnbilledChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionImportUnbilledChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionImportUnbilledChargesResponse. */
  public static class Builder {

    private List<UnbilledCharge> unbilledCharges;

    private Builder() {}

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public SubscriptionImportUnbilledChargesResponse build() {
      return new SubscriptionImportUnbilledChargesResponse(this);
    }
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }
}
