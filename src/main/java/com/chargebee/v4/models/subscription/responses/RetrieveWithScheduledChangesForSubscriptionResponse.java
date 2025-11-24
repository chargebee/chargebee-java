package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RetrieveWithScheduledChangesForSubscription operation. Contains the
 * response data from a single resource get operation.
 */
public final class RetrieveWithScheduledChangesForSubscriptionResponse extends BaseResponse {
  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private RetrieveWithScheduledChangesForSubscriptionResponse(
      Subscription subscription, Customer customer, Card card, Response httpResponse) {
    super(httpResponse);

    this.subscription = subscription;

    this.customer = customer;

    this.card = card;
  }

  /** Parse JSON response into RetrieveWithScheduledChangesForSubscriptionResponse object. */
  public static RetrieveWithScheduledChangesForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into RetrieveWithScheduledChangesForSubscriptionResponse object with HTTP
   * response.
   */
  public static RetrieveWithScheduledChangesForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {

      Subscription subscription = Subscription.fromJson(JsonUtil.getObject(json, "subscription"));

      Customer customer = Customer.fromJson(JsonUtil.getObject(json, "customer"));

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new RetrieveWithScheduledChangesForSubscriptionResponse(
          subscription, customer, card, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveWithScheduledChangesForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
  }
}
