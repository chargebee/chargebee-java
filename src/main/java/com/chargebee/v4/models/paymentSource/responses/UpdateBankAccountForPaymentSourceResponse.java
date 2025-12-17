package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.paymentSource.PaymentSource;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpdateBankAccountForPaymentSource operation. Contains the response
 * data from the API.
 */
public final class UpdateBankAccountForPaymentSourceResponse extends BaseResponse {
  private final Customer customer;

  private final PaymentSource paymentSource;

  private UpdateBankAccountForPaymentSourceResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into UpdateBankAccountForPaymentSourceResponse object. */
  public static UpdateBankAccountForPaymentSourceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into UpdateBankAccountForPaymentSourceResponse object with HTTP response.
   */
  public static UpdateBankAccountForPaymentSourceResponse fromJson(
      String json, Response httpResponse) {
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
          "Failed to parse UpdateBankAccountForPaymentSourceResponse from JSON", e);
    }
  }

  /** Create a new builder for UpdateBankAccountForPaymentSourceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpdateBankAccountForPaymentSourceResponse. */
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

    public UpdateBankAccountForPaymentSourceResponse build() {
      return new UpdateBankAccountForPaymentSourceResponse(this);
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
}
