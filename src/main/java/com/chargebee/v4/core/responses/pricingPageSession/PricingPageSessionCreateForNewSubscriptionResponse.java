package com.chargebee.v4.core.responses.pricingPageSession;

import com.chargebee.v4.core.models.pricingPageSession.PricingPageSession;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PricingPageSessionCreateForNewSubscription operation. Contains the
 * response data from the API.
 */
public final class PricingPageSessionCreateForNewSubscriptionResponse {

  private final PricingPageSession pricingPageSession;

  private PricingPageSessionCreateForNewSubscriptionResponse(Builder builder) {

    this.pricingPageSession = builder.pricingPageSession;
  }

  /** Parse JSON response into PricingPageSessionCreateForNewSubscriptionResponse object. */
  public static PricingPageSessionCreateForNewSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __pricingPageSessionJson = JsonUtil.getObject(json, "pricing_page_session");
      if (__pricingPageSessionJson != null) {
        builder.pricingPageSession(PricingPageSession.fromJson(__pricingPageSessionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PricingPageSessionCreateForNewSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for PricingPageSessionCreateForNewSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PricingPageSessionCreateForNewSubscriptionResponse. */
  public static class Builder {

    private PricingPageSession pricingPageSession;

    private Builder() {}

    public Builder pricingPageSession(PricingPageSession pricingPageSession) {
      this.pricingPageSession = pricingPageSession;
      return this;
    }

    public PricingPageSessionCreateForNewSubscriptionResponse build() {
      return new PricingPageSessionCreateForNewSubscriptionResponse(this);
    }
  }

  /** Get the pricingPageSession from the response. */
  public PricingPageSession getPricingPageSession() {
    return pricingPageSession;
  }
}
