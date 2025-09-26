package com.chargebee.core.responses.gift;

import com.chargebee.core.models.gift.Gift;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for GiftCreate operation. Contains the response data from the API. */
public final class GiftCreateResponse {

  private final Gift gift;

  private final Subscription subscription;

  private final Invoice invoice;

  private GiftCreateResponse(Builder builder) {

    this.gift = builder.gift;

    this.subscription = builder.subscription;

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into GiftCreateResponse object. */
  public static GiftCreateResponse fromJson(String json) {
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

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for GiftCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for GiftCreateResponse. */
  public static class Builder {

    private Gift gift;

    private Subscription subscription;

    private Invoice invoice;

    private Builder() {}

    public Builder gift(Gift gift) {
      this.gift = gift;
      return this;
    }

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public GiftCreateResponse build() {
      return new GiftCreateResponse(this);
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

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
