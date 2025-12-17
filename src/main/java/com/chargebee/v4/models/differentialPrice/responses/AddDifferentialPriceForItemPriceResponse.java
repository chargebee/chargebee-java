package com.chargebee.v4.models.differentialPrice.responses;

import com.chargebee.v4.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddDifferentialPriceForItemPrice operation. Contains the response
 * data from the API.
 */
public final class AddDifferentialPriceForItemPriceResponse extends BaseResponse {
  private final DifferentialPrice differentialPrice;

  private AddDifferentialPriceForItemPriceResponse(Builder builder) {
    super(builder.httpResponse);

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into AddDifferentialPriceForItemPriceResponse object. */
  public static AddDifferentialPriceForItemPriceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into AddDifferentialPriceForItemPriceResponse object with HTTP response.
   */
  public static AddDifferentialPriceForItemPriceResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AddDifferentialPriceForItemPriceResponse from JSON", e);
    }
  }

  /** Create a new builder for AddDifferentialPriceForItemPriceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddDifferentialPriceForItemPriceResponse. */
  public static class Builder {

    private DifferentialPrice differentialPrice;

    private Response httpResponse;

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AddDifferentialPriceForItemPriceResponse build() {
      return new AddDifferentialPriceForItemPriceResponse(this);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
