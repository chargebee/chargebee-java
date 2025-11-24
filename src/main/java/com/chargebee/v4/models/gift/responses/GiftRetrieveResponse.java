package com.chargebee.v4.models.gift.responses;

import com.chargebee.v4.models.gift.Gift;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for GiftRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class GiftRetrieveResponse extends BaseResponse {
  private final Gift gift;

  private final Subscription subscription;

  private GiftRetrieveResponse(Gift gift, Subscription subscription, Response httpResponse) {
    super(httpResponse);

    this.gift = gift;

    this.subscription = subscription;
  }

  /** Parse JSON response into GiftRetrieveResponse object. */
  public static GiftRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into GiftRetrieveResponse object with HTTP response. */
  public static GiftRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Gift gift = Gift.fromJson(JsonUtil.getObject(json, "gift"));

      Subscription subscription = Subscription.fromJson(JsonUtil.getObject(json, "subscription"));

      return new GiftRetrieveResponse(gift, subscription, httpResponse);
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
