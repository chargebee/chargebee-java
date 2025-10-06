package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceExportPaymentSource operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceExportPaymentSourceResponse {

  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private PaymentSourceExportPaymentSourceResponse(Builder builder) {

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into PaymentSourceExportPaymentSourceResponse object. */
  public static PaymentSourceExportPaymentSourceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __thirdPartyPaymentMethodJson = JsonUtil.getObject(json, "third_party_payment_method");
      if (__thirdPartyPaymentMethodJson != null) {
        builder.thirdPartyPaymentMethod(
            ThirdPartyPaymentMethod.fromJson(__thirdPartyPaymentMethodJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentSourceExportPaymentSourceResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceExportPaymentSourceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceExportPaymentSourceResponse. */
  public static class Builder {

    private ThirdPartyPaymentMethod thirdPartyPaymentMethod;

    private Builder() {}

    public Builder thirdPartyPaymentMethod(ThirdPartyPaymentMethod thirdPartyPaymentMethod) {
      this.thirdPartyPaymentMethod = thirdPartyPaymentMethod;
      return this;
    }

    public PaymentSourceExportPaymentSourceResponse build() {
      return new PaymentSourceExportPaymentSourceResponse(this);
    }
  }

  /** Get the thirdPartyPaymentMethod from the response. */
  public ThirdPartyPaymentMethod getThirdPartyPaymentMethod() {
    return thirdPartyPaymentMethod;
  }
}
