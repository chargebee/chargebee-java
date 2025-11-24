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

  private OfferFulfillmentRetrieveResponse(
      OfferFulfillment offerFulfillment, Response httpResponse) {
    super(httpResponse);

    this.offerFulfillment = offerFulfillment;
  }

  /** Parse JSON response into OfferFulfillmentRetrieveResponse object. */
  public static OfferFulfillmentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferFulfillmentRetrieveResponse object with HTTP response. */
  public static OfferFulfillmentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      OfferFulfillment offerFulfillment =
          OfferFulfillment.fromJson(JsonUtil.getObject(json, "offer_fulfillment"));

      return new OfferFulfillmentRetrieveResponse(offerFulfillment, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferFulfillmentRetrieveResponse from JSON", e);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }
}
