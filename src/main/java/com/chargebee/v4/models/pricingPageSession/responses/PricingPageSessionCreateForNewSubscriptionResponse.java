package com.chargebee.v4.models.pricingPageSession.responses;

import com.chargebee.v4.models.pricingPageSession.PricingPageSession;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PricingPageSessionCreateForNewSubscription operation. Contains the
 * response data from the API.
 */
public final class PricingPageSessionCreateForNewSubscriptionResponse extends BaseResponse {
  private final PricingPageSession pricingPageSession;

  private PricingPageSessionCreateForNewSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.pricingPageSession = builder.pricingPageSession;
  }

  /** Parse JSON response into PricingPageSessionCreateForNewSubscriptionResponse object. */
  public static PricingPageSessionCreateForNewSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into PricingPageSessionCreateForNewSubscriptionResponse object with HTTP
   * response.
   */
  public static PricingPageSessionCreateForNewSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __pricingPageSessionJson = JsonUtil.getObject(json, "pricing_page_session");
      if (__pricingPageSessionJson != null) {
        builder.pricingPageSession(PricingPageSession.fromJson(__pricingPageSessionJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder pricingPageSession(PricingPageSession pricingPageSession) {
      this.pricingPageSession = pricingPageSession;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "PricingPageSessionCreateForNewSubscriptionResponse{"
        + "pricingPageSession="
        + pricingPageSession
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PricingPageSessionCreateForNewSubscriptionResponse that =
        (PricingPageSessionCreateForNewSubscriptionResponse) o;
    return java.util.Objects.equals(pricingPageSession, that.pricingPageSession);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(pricingPageSession);
  }
}
