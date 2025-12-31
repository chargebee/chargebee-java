package com.chargebee.v4.models.gift.responses;

import com.chargebee.v4.models.gift.Gift;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for GiftCreate operation. Contains the response data from the API. */
public final class GiftCreateResponse extends BaseResponse {
  private final Gift gift;

  private final Subscription subscription;

  private final Invoice invoice;

  private GiftCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.gift = builder.gift;

    this.subscription = builder.subscription;

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into GiftCreateResponse object. */
  public static GiftCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into GiftCreateResponse object with HTTP response. */
  public static GiftCreateResponse fromJson(String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
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

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "GiftCreateResponse{"
        + "gift="
        + gift
        + ", subscription="
        + subscription
        + ", invoice="
        + invoice
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GiftCreateResponse that = (GiftCreateResponse) o;
    return java.util.Objects.equals(gift, that.gift)
        && java.util.Objects.equals(subscription, that.subscription)
        && java.util.Objects.equals(invoice, that.invoice);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(gift, subscription, invoice);
  }
}
