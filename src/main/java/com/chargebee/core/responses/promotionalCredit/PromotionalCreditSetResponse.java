package com.chargebee.core.responses.promotionalCredit;

import com.chargebee.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PromotionalCreditSet operation. Contains the response data from the
 * API.
 */
public final class PromotionalCreditSetResponse {

  private final Customer customer;

  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditSetResponse(Builder builder) {

    this.customer = builder.customer;

    this.promotionalCredit = builder.promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditSetResponse object. */
  public static PromotionalCreditSetResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __promotionalCreditJson = JsonUtil.getObject(json, "promotional_credit");
      if (__promotionalCreditJson != null) {
        builder.promotionalCredit(PromotionalCredit.fromJson(__promotionalCreditJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditSetResponse from JSON", e);
    }
  }

  /** Create a new builder for PromotionalCreditSetResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PromotionalCreditSetResponse. */
  public static class Builder {

    private Customer customer;

    private PromotionalCredit promotionalCredit;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder promotionalCredit(PromotionalCredit promotionalCredit) {
      this.promotionalCredit = promotionalCredit;
      return this;
    }

    public PromotionalCreditSetResponse build() {
      return new PromotionalCreditSetResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the promotionalCredit from the response. */
  public PromotionalCredit getPromotionalCredit() {
    return promotionalCredit;
  }
}
