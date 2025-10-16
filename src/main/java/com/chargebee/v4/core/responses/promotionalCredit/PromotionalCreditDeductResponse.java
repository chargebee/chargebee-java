package com.chargebee.v4.core.responses.promotionalCredit;

import com.chargebee.v4.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalCreditDeduct operation. Contains the response data from
 * the API.
 */
public final class PromotionalCreditDeductResponse extends BaseResponse {
  private final Customer customer;

  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditDeductResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.promotionalCredit = builder.promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditDeductResponse object. */
  public static PromotionalCreditDeductResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalCreditDeductResponse object with HTTP response. */
  public static PromotionalCreditDeductResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse PromotionalCreditDeductResponse from JSON", e);
    }
  }

  /** Create a new builder for PromotionalCreditDeductResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PromotionalCreditDeductResponse. */
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

    public PromotionalCreditDeductResponse build() {
      return new PromotionalCreditDeductResponse(this);
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
