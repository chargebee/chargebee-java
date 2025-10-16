package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DifferentialPriceUpdate operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceUpdateResponse extends BaseResponse {
  private final DifferentialPrice differentialPrice;

  private DifferentialPriceUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceUpdateResponse object. */
  public static DifferentialPriceUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DifferentialPriceUpdateResponse object with HTTP response. */
  public static DifferentialPriceUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for DifferentialPriceUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DifferentialPriceUpdateResponse. */
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

    public DifferentialPriceUpdateResponse build() {
      return new DifferentialPriceUpdateResponse(this);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
