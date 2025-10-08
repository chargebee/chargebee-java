package com.chargebee.v4.core.responses.gift;

import com.chargebee.v4.core.models.gift.Gift;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for GiftRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class GiftRetrieveResponse {

  private final Gift gift;

  private final Subscription subscription;

  private final Response httpResponse;

  private GiftRetrieveResponse(Gift gift, Subscription subscription, Response httpResponse) {

    this.gift = gift;

    this.subscription = subscription;

    this.httpResponse = httpResponse;
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
