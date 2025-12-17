package com.chargebee.v4.models.offerFulfillment.responses;

import com.chargebee.v4.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillmentRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class OfferFulfillmentRetrieveResponse extends BaseResponse {
  private final OfferFulfillment offerFulfillment;

  private OfferFulfillmentRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.offerFulfillment = builder.offerFulfillment;
  }

  /** Parse JSON response into OfferFulfillmentRetrieveResponse object. */
  public static OfferFulfillmentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferFulfillmentRetrieveResponse object with HTTP response. */
  public static OfferFulfillmentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __offerFulfillmentJson = JsonUtil.getObject(json, "offer_fulfillment");
      if (__offerFulfillmentJson != null) {
        builder.offerFulfillment(OfferFulfillment.fromJson(__offerFulfillmentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferFulfillmentRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferFulfillmentRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferFulfillmentRetrieveResponse. */
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

    public OfferFulfillmentRetrieveResponse build() {
      return new OfferFulfillmentRetrieveResponse(this);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }
}
