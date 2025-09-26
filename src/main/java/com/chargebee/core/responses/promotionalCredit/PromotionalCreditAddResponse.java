package com.chargebee.core.responses.promotionalCredit;

import com.chargebee.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PromotionalCreditAdd operation. Contains the response data from the
 * API.
 */
public final class PromotionalCreditAddResponse {

  private final Customer customer;

  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditAddResponse(Builder builder) {

    this.customer = builder.customer;

    this.promotionalCredit = builder.promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditAddResponse object. */
  public static PromotionalCreditAddResponse fromJson(String json) {
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
      throw new RuntimeException("Failed to parse PromotionalCreditAddResponse from JSON", e);
    }
  }

  /** Create a new builder for PromotionalCreditAddResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PromotionalCreditAddResponse. */
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

    public PromotionalCreditAddResponse build() {
      return new PromotionalCreditAddResponse(this);
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
