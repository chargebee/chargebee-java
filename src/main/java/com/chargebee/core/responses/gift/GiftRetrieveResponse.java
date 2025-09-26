package com.chargebee.core.responses.gift;

import com.chargebee.core.models.gift.Gift;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for GiftRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class GiftRetrieveResponse {

  private final Gift gift;

  private final Subscription subscription;

  private GiftRetrieveResponse(Gift gift, Subscription subscription) {

    this.gift = gift;

    this.subscription = subscription;
  }

  /** Parse JSON response into GiftRetrieveResponse object. */
  public static GiftRetrieveResponse fromJson(String json) {
    try {

      Gift gift = Gift.fromJson(JsonUtil.getObject(json, "gift"));

      Subscription subscription = Subscription.fromJson(JsonUtil.getObject(json, "subscription"));

      return new GiftRetrieveResponse(gift, subscription);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftRetrieveResponse from JSON", e);
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
