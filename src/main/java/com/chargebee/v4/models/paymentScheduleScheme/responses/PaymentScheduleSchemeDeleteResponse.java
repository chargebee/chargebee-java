package com.chargebee.v4.models.paymentScheduleScheme.responses;

import com.chargebee.v4.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeDelete operation. Contains the response data
 * from the API.
 */
public final class PaymentScheduleSchemeDeleteResponse extends BaseResponse {
  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentScheduleScheme = builder.paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeDeleteResponse object. */
  public static PaymentScheduleSchemeDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeDeleteResponse object with HTTP response. */
  public static PaymentScheduleSchemeDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentScheduleSchemeJson = JsonUtil.getObject(json, "payment_schedule_scheme");
      if (__paymentScheduleSchemeJson != null) {
        builder.paymentScheduleScheme(PaymentScheduleScheme.fromJson(__paymentScheduleSchemeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeDeleteResponse. */
  public static class Builder {

    private PaymentScheduleScheme paymentScheduleScheme;

    private Response httpResponse;

    private Builder() {}

    public Builder paymentScheduleScheme(PaymentScheduleScheme paymentScheduleScheme) {
      this.paymentScheduleScheme = paymentScheduleScheme;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentScheduleSchemeDeleteResponse build() {
      return new PaymentScheduleSchemeDeleteResponse(this);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }
}
