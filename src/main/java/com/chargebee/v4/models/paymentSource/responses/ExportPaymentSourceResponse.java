package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportPaymentSource operation. Contains the response data from the
 * API.
 */
public final class ExportPaymentSourceResponse extends BaseResponse {
  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private ExportPaymentSourceResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into ExportPaymentSourceResponse object. */
  public static ExportPaymentSourceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportPaymentSourceResponse object with HTTP response. */
  public static ExportPaymentSourceResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse ExportPaymentSourceResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportPaymentSourceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportPaymentSourceResponse. */
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

    public ExportPaymentSourceResponse build() {
      return new ExportPaymentSourceResponse(this);
    }
  }

  /** Get the thirdPartyPaymentMethod from the response. */
  public ThirdPartyPaymentMethod getThirdPartyPaymentMethod() {
    return thirdPartyPaymentMethod;
  }

  @Override
  public String toString() {
    return "ExportPaymentSourceResponse{"
        + "thirdPartyPaymentMethod="
        + thirdPartyPaymentMethod
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExportPaymentSourceResponse that = (ExportPaymentSourceResponse) o;
    return java.util.Objects.equals(thirdPartyPaymentMethod, that.thirdPartyPaymentMethod);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(thirdPartyPaymentMethod);
  }
}
