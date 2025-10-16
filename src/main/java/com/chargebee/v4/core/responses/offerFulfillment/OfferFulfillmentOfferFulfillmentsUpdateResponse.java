package com.chargebee.v4.core.responses.offerFulfillment;

import com.chargebee.v4.core.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillmentOfferFulfillmentsUpdate operation. Contains the
 * response data from the API.
 */
public final class OfferFulfillmentOfferFulfillmentsUpdateResponse extends BaseResponse {
  private final OfferFulfillment offerFulfillment;

  private OfferFulfillmentOfferFulfillmentsUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.offerFulfillment = builder.offerFulfillment;
  }

  /** Parse JSON response into OfferFulfillmentOfferFulfillmentsUpdateResponse object. */
  public static OfferFulfillmentOfferFulfillmentsUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into OfferFulfillmentOfferFulfillmentsUpdateResponse object with HTTP
   * response.
   */
  public static OfferFulfillmentOfferFulfillmentsUpdateResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __offerFulfillmentJson = JsonUtil.getObject(json, "offer_fulfillment");
      if (__offerFulfillmentJson != null) {
        builder.offerFulfillment(OfferFulfillment.fromJson(__offerFulfillmentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OfferFulfillmentOfferFulfillmentsUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferFulfillmentOfferFulfillmentsUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferFulfillmentOfferFulfillmentsUpdateResponse. */
  public static class Builder {

    private OfferFulfillment offerFulfillment;

    private Response httpResponse;

    private Builder() {}

    public Builder offerFulfillment(OfferFulfillment offerFulfillment) {
      this.offerFulfillment = offerFulfillment;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OfferFulfillmentOfferFulfillmentsUpdateResponse build() {
      return new OfferFulfillmentOfferFulfillmentsUpdateResponse(this);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }
}
