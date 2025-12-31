package com.chargebee.v4.models.gift.responses;

import com.chargebee.v4.models.gift.Gift;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for UpdateGift operation. Contains the response data from the API. */
public final class UpdateGiftResponse extends BaseResponse {
  private final Gift gift;

  private final Subscription subscription;

  private UpdateGiftResponse(Builder builder) {
    super(builder.httpResponse);

    this.gift = builder.gift;

    this.subscription = builder.subscription;
  }

  /** Parse JSON response into UpdateGiftResponse object. */
  public static UpdateGiftResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UpdateGiftResponse object with HTTP response. */
  public static UpdateGiftResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse UpdateGiftResponse from JSON", e);
    }
  }

  /** Create a new builder for UpdateGiftResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpdateGiftResponse. */
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

    public UpdateGiftResponse build() {
      return new UpdateGiftResponse(this);
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

  @Override
  public String toString() {
    return "UpdateGiftResponse{" + "gift=" + gift + ", subscription=" + subscription + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UpdateGiftResponse that = (UpdateGiftResponse) o;
    return java.util.Objects.equals(gift, that.gift)
        && java.util.Objects.equals(subscription, that.subscription);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(gift, subscription);
  }
}
