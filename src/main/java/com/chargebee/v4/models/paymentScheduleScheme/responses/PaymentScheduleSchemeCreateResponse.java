package com.chargebee.v4.models.paymentScheduleScheme.responses;

import com.chargebee.v4.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeCreate operation. Contains the response data
 * from the API.
 */
public final class PaymentScheduleSchemeCreateResponse extends BaseResponse {
  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentScheduleScheme = builder.paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeCreateResponse object. */
  public static PaymentScheduleSchemeCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeCreateResponse object with HTTP response. */
  public static PaymentScheduleSchemeCreateResponse fromJson(String json, Response httpResponse) {
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
          "Failed to parse PaymentScheduleSchemeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeCreateResponse. */
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

    public PaymentScheduleSchemeCreateResponse build() {
      return new PaymentScheduleSchemeCreateResponse(this);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }

  @Override
  public String toString() {
    return "PaymentScheduleSchemeCreateResponse{"
        + "paymentScheduleScheme="
        + paymentScheduleScheme
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaymentScheduleSchemeCreateResponse that = (PaymentScheduleSchemeCreateResponse) o;
    return java.util.Objects.equals(paymentScheduleScheme, that.paymentScheduleScheme);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(paymentScheduleScheme);
  }
}
