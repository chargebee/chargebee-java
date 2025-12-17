package com.chargebee.v4.models.paymentScheduleScheme.responses;

import com.chargebee.v4.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PaymentScheduleSchemeRetrieveResponse extends BaseResponse {
  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentScheduleScheme = builder.paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object with HTTP response. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json, Response httpResponse) {
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
          "Failed to parse PaymentScheduleSchemeRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeRetrieveResponse. */
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

    public PaymentScheduleSchemeRetrieveResponse build() {
      return new PaymentScheduleSchemeRetrieveResponse(this);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }
}
