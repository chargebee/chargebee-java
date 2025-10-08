package com.chargebee.v4.core.responses.offerFulfillment;

import com.chargebee.v4.core.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillmentOfferFulfillmentsGet operation. Contains the
 * response data from a single resource get operation.
 */
public final class OfferFulfillmentOfferFulfillmentsGetResponse {

  private final OfferFulfillment offerFulfillment;

  private final Response httpResponse;

  private OfferFulfillmentOfferFulfillmentsGetResponse(
      OfferFulfillment offerFulfillment, Response httpResponse) {

    this.offerFulfillment = offerFulfillment;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into OfferFulfillmentOfferFulfillmentsGetResponse object. */
  public static OfferFulfillmentOfferFulfillmentsGetResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into OfferFulfillmentOfferFulfillmentsGetResponse object with HTTP
   * response.
   */
  public static OfferFulfillmentOfferFulfillmentsGetResponse fromJson(
      String json, Response httpResponse) {
    try {

      OfferFulfillment offerFulfillment =
          OfferFulfillment.fromJson(JsonUtil.getObject(json, "offer_fulfillment"));

      return new OfferFulfillmentOfferFulfillmentsGetResponse(offerFulfillment, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OfferFulfillmentOfferFulfillmentsGetResponse from JSON", e);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
