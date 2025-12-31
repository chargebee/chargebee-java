package com.chargebee.v4.models.promotionalCredit.responses;

import com.chargebee.v4.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalCreditAdd operation. Contains the response data from the
 * API.
 */
public final class PromotionalCreditAddResponse extends BaseResponse {
  private final Customer customer;

  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditAddResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.promotionalCredit = builder.promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditAddResponse object. */
  public static PromotionalCreditAddResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalCreditAddResponse object with HTTP response. */
  public static PromotionalCreditAddResponse fromJson(String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder promotionalCredit(PromotionalCredit promotionalCredit) {
      this.promotionalCredit = promotionalCredit;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "PromotionalCreditAddResponse{"
        + "customer="
        + customer
        + ", promotionalCredit="
        + promotionalCredit
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PromotionalCreditAddResponse that = (PromotionalCreditAddResponse) o;
    return java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(promotionalCredit, that.promotionalCredit);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(customer, promotionalCredit);
  }
}
