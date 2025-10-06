package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionImportSubscription operation. Contains the response
 * data from the API.
 */
public final class SubscriptionImportSubscriptionResponse {

  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Invoice invoice;

  private SubscriptionImportSubscriptionResponse(Builder builder) {

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into SubscriptionImportSubscriptionResponse object. */
  public static SubscriptionImportSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        builder.card(Card.fromJson(__cardJson));
      }

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionImportSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionImportSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionImportSubscriptionResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private Invoice invoice;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder card(Card card) {
      this.card = card;
      return this;
    }

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public SubscriptionImportSubscriptionResponse build() {
      return new SubscriptionImportSubscriptionResponse(this);
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

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
