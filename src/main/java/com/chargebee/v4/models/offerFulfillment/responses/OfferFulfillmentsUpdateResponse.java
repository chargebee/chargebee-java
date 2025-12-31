package com.chargebee.v4.models.offerFulfillment.responses;

import com.chargebee.v4.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillmentsUpdate operation. Contains the response data from
 * the API.
 */
public final class OfferFulfillmentsUpdateResponse extends BaseResponse {
  private final OfferFulfillment offerFulfillment;

  private OfferFulfillmentsUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.offerFulfillment = builder.offerFulfillment;
  }

  /** Parse JSON response into OfferFulfillmentsUpdateResponse object. */
  public static OfferFulfillmentsUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferFulfillmentsUpdateResponse object with HTTP response. */
  public static OfferFulfillmentsUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __offerFulfillmentJson = JsonUtil.getObject(json, "offer_fulfillment");
      if (__offerFulfillmentJson != null) {
        builder.offerFulfillment(OfferFulfillment.fromJson(__offerFulfillmentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferFulfillmentsUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferFulfillmentsUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferFulfillmentsUpdateResponse. */
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

    public OfferFulfillmentsUpdateResponse build() {
      return new OfferFulfillmentsUpdateResponse(this);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }

  @Override
  public String toString() {
    return "OfferFulfillmentsUpdateResponse{" + "offerFulfillment=" + offerFulfillment + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OfferFulfillmentsUpdateResponse that = (OfferFulfillmentsUpdateResponse) o;
    return java.util.Objects.equals(offerFulfillment, that.offerFulfillment);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(offerFulfillment);
  }
}
