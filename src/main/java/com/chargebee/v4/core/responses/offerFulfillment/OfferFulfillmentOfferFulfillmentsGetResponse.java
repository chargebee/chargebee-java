package com.chargebee.v4.core.responses.offerFulfillment;

import com.chargebee.v4.core.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for OfferFulfillmentOfferFulfillmentsGet operation. Contains the
 * response data from a single resource get operation.
 */
public final class OfferFulfillmentOfferFulfillmentsGetResponse {

  private final OfferFulfillment offerFulfillment;

  private OfferFulfillmentOfferFulfillmentsGetResponse(OfferFulfillment offerFulfillment) {

    this.offerFulfillment = offerFulfillment;
  }

  /** Parse JSON response into OfferFulfillmentOfferFulfillmentsGetResponse object. */
  public static OfferFulfillmentOfferFulfillmentsGetResponse fromJson(String json) {
    try {

      OfferFulfillment offerFulfillment =
          OfferFulfillment.fromJson(JsonUtil.getObject(json, "offer_fulfillment"));

      return new OfferFulfillmentOfferFulfillmentsGetResponse(offerFulfillment);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OfferFulfillmentOfferFulfillmentsGetResponse from JSON", e);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }
}
