package com.chargebee.core.responses.subscription;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.card.Card;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionRetrieveWithScheduledChanges operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveWithScheduledChangesResponse {

  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private SubscriptionRetrieveWithScheduledChangesResponse(
      Subscription subscription, Customer customer, Card card) {

    this.subscription = subscription;

    this.customer = customer;

    this.card = card;
  }

  /** Parse JSON response into SubscriptionRetrieveWithScheduledChangesResponse object. */
  public static SubscriptionRetrieveWithScheduledChangesResponse fromJson(String json) {
    try {

      Subscription subscription = Subscription.fromJson(JsonUtil.getObject(json, "subscription"));

      Customer customer = Customer.fromJson(JsonUtil.getObject(json, "customer"));

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new SubscriptionRetrieveWithScheduledChangesResponse(subscription, customer, card);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRetrieveWithScheduledChangesResponse from JSON", e);
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
