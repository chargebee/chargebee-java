package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.paymentSource.PaymentSource;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceCreateUsingToken operation. Contains the response data
 * from the API.
 */
public final class PaymentSourceCreateUsingTokenResponse extends BaseResponse {
  private final Customer customer;

  private final PaymentSource paymentSource;

  private PaymentSourceCreateUsingTokenResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceCreateUsingTokenResponse object. */
  public static PaymentSourceCreateUsingTokenResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSourceCreateUsingTokenResponse object with HTTP response. */
  public static PaymentSourceCreateUsingTokenResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentSourceCreateUsingTokenResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceCreateUsingTokenResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceCreateUsingTokenResponse. */
  public static class Builder {

    private Customer customer;

    private PaymentSource paymentSource;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentSourceCreateUsingTokenResponse build() {
      return new PaymentSourceCreateUsingTokenResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }

  @Override
  public String toString() {
    return "PaymentSourceCreateUsingTokenResponse{"
        + "customer="
        + customer
        + ", paymentSource="
        + paymentSource
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaymentSourceCreateUsingTokenResponse that = (PaymentSourceCreateUsingTokenResponse) o;
    return java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(paymentSource, that.paymentSource);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(customer, paymentSource);
  }
}
