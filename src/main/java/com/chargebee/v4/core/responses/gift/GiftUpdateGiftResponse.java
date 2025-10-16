package com.chargebee.v4.core.responses.gift;

import com.chargebee.v4.core.models.gift.Gift;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for GiftUpdateGift operation. Contains the response data from the API.
 */
public final class GiftUpdateGiftResponse extends BaseResponse {
  private final Gift gift;

  private final Subscription subscription;

  private GiftUpdateGiftResponse(Builder builder) {
    super(builder.httpResponse);

    this.gift = builder.gift;

    this.subscription = builder.subscription;
  }

  /** Parse JSON response into GiftUpdateGiftResponse object. */
  public static GiftUpdateGiftResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into GiftUpdateGiftResponse object with HTTP response. */
  public static GiftUpdateGiftResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __giftJson = JsonUtil.getObject(json, "gift");
      if (__giftJson != null) {
        builder.gift(Gift.fromJson(__giftJson));
      }

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftUpdateGiftResponse from JSON", e);
    }
  }

  /** Create a new builder for GiftUpdateGiftResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for GiftUpdateGiftResponse. */
  public static class Builder {

    private Gift gift;

    private Subscription subscription;

    private Response httpResponse;

    private Builder() {}

    public Builder gift(Gift gift) {
      this.gift = gift;
      return this;
    }

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public GiftUpdateGiftResponse build() {
      return new GiftUpdateGiftResponse(this);
    }
  }

  /** Get the gift from the response. */
  public Gift getGift() {
    return gift;
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }
}
