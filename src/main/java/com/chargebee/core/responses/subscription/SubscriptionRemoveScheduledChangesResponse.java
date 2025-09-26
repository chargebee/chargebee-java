package com.chargebee.core.responses.subscription;

import java.util.List;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.core.models.card.Card;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionRemoveScheduledChanges operation. Contains the response
 * data from the API.
 */
public final class SubscriptionRemoveScheduledChangesResponse {

  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final List<CreditNote> creditNotes;

  private SubscriptionRemoveScheduledChangesResponse(Builder builder) {

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.creditNotes = builder.creditNotes;
  }

  /** Parse JSON response into SubscriptionRemoveScheduledChangesResponse object. */
  public static SubscriptionRemoveScheduledChangesResponse fromJson(String json) {
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

      builder.creditNotes(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "credit_notes")).stream()
              .map(CreditNote::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRemoveScheduledChangesResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRemoveScheduledChangesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRemoveScheduledChangesResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private List<CreditNote> creditNotes;

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

    public Builder creditNotes(List<CreditNote> creditNotes) {
      this.creditNotes = creditNotes;
      return this;
    }

    public SubscriptionRemoveScheduledChangesResponse build() {
      return new SubscriptionRemoveScheduledChangesResponse(this);
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

  /** Get the creditNotes from the response. */
  public List<CreditNote> getCreditNotes() {
    return creditNotes;
  }
}
