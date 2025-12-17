package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportPaymentSourceForPaymentSource operation. Contains the
 * response data from the API.
 */
public final class ExportPaymentSourceForPaymentSourceResponse extends BaseResponse {
  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private ExportPaymentSourceForPaymentSourceResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into ExportPaymentSourceForPaymentSourceResponse object. */
  public static ExportPaymentSourceForPaymentSourceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ExportPaymentSourceForPaymentSourceResponse object with HTTP response.
   */
  public static ExportPaymentSourceForPaymentSourceResponse fromJson(
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
          "Failed to parse ExportPaymentSourceForPaymentSourceResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportPaymentSourceForPaymentSourceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportPaymentSourceForPaymentSourceResponse. */
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

    public ExportPaymentSourceForPaymentSourceResponse build() {
      return new ExportPaymentSourceForPaymentSourceResponse(this);
    }
  }

  /** Get the thirdPartyPaymentMethod from the response. */
  public ThirdPartyPaymentMethod getThirdPartyPaymentMethod() {
    return thirdPartyPaymentMethod;
  }
}
