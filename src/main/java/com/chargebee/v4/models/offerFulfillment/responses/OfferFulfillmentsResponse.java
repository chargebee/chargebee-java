package com.chargebee.v4.models.offerFulfillment.responses;

import com.chargebee.v4.models.offerFulfillment.OfferFulfillment;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferFulfillments operation. Contains the response data from the
 * API.
 */
public final class OfferFulfillmentsResponse extends BaseResponse {
  private final OfferFulfillment offerFulfillment;

  private final HostedPage hostedPage;

  private OfferFulfillmentsResponse(Builder builder) {
    super(builder.httpResponse);

    this.offerFulfillment = builder.offerFulfillment;

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into OfferFulfillmentsResponse object. */
  public static OfferFulfillmentsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferFulfillmentsResponse object with HTTP response. */
  public static OfferFulfillmentsResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse OfferFulfillmentsResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferFulfillmentsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferFulfillmentsResponse. */
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

    public OfferFulfillmentsResponse build() {
      return new OfferFulfillmentsResponse(this);
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
