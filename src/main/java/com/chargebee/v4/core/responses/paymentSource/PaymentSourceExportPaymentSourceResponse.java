package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceExportPaymentSource operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceExportPaymentSourceResponse extends BaseResponse {
  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private PaymentSourceExportPaymentSourceResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into PaymentSourceExportPaymentSourceResponse object. */
  public static PaymentSourceExportPaymentSourceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into PaymentSourceExportPaymentSourceResponse object with HTTP response.
   */
  public static PaymentSourceExportPaymentSourceResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __thirdPartyPaymentMethodJson = JsonUtil.getObject(json, "third_party_payment_method");
      if (__thirdPartyPaymentMethodJson != null) {
        builder.thirdPartyPaymentMethod(
            ThirdPartyPaymentMethod.fromJson(__thirdPartyPaymentMethodJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartyPaymentMethod(ThirdPartyPaymentMethod thirdPartyPaymentMethod) {
      this.thirdPartyPaymentMethod = thirdPartyPaymentMethod;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
