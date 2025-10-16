package com.chargebee.v4.core.responses.offerFulfillment;

import com.chargebee.v4.core.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillmentOfferFulfillments operation. Contains the response
 * data from the API.
 */
public final class OfferFulfillmentOfferFulfillmentsResponse extends BaseResponse {
  private final OfferFulfillment offerFulfillment;

  private final HostedPage hostedPage;

  private OfferFulfillmentOfferFulfillmentsResponse(Builder builder) {
    super(builder.httpResponse);

    this.offerFulfillment = builder.offerFulfillment;

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into OfferFulfillmentOfferFulfillmentsResponse object. */
  public static OfferFulfillmentOfferFulfillmentsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into OfferFulfillmentOfferFulfillmentsResponse object with HTTP response.
   */
  public static OfferFulfillmentOfferFulfillmentsResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __offerFulfillmentJson = JsonUtil.getObject(json, "offer_fulfillment");
      if (__offerFulfillmentJson != null) {
        builder.offerFulfillment(OfferFulfillment.fromJson(__offerFulfillmentJson));
      }

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OfferFulfillmentOfferFulfillmentsResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferFulfillmentOfferFulfillmentsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferFulfillmentOfferFulfillmentsResponse. */
  public static class Builder {

    private OfferFulfillment offerFulfillment;

    private HostedPage hostedPage;

    private Response httpResponse;

    private Builder() {}

    public Builder offerFulfillment(OfferFulfillment offerFulfillment) {
      this.offerFulfillment = offerFulfillment;
      return this;
    }

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OfferFulfillmentOfferFulfillmentsResponse build() {
      return new OfferFulfillmentOfferFulfillmentsResponse(this);
    }
  }

  /** Get the offerFulfillment from the response. */
  public OfferFulfillment getOfferFulfillment() {
    return offerFulfillment;
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
