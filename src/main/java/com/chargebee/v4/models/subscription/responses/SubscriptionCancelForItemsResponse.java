package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionCancelForItems operation. Contains the response data
 * from the API.
 */
public final class SubscriptionCancelForItemsResponse extends BaseResponse {
  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Invoice invoice;

  private final List<UnbilledCharge> unbilledCharges;

  private final List<CreditNote> creditNotes;

  private SubscriptionCancelForItemsResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.invoice = builder.invoice;

    this.unbilledCharges = builder.unbilledCharges;

    this.creditNotes = builder.creditNotes;
  }

  /** Parse JSON response into SubscriptionCancelForItemsResponse object. */
  public static SubscriptionCancelForItemsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionCancelForItemsResponse object with HTTP response. */
  public static SubscriptionCancelForItemsResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __subscriptionObj = JsonUtil.getJsonObject(jsonObj, "subscription");
      if (__subscriptionObj != null) {
        builder.subscription(Subscription.fromJson(__subscriptionObj));
      }

      JsonObject __customerObj = JsonUtil.getJsonObject(jsonObj, "customer");
      if (__customerObj != null) {
        builder.customer(Customer.fromJson(__customerObj));
      }

      JsonObject __cardObj = JsonUtil.getJsonObject(jsonObj, "card");
      if (__cardObj != null) {
        builder.card(Card.fromJson(__cardObj));
      }

      JsonObject __invoiceObj = JsonUtil.getJsonObject(jsonObj, "invoice");
      if (__invoiceObj != null) {
        builder.invoice(Invoice.fromJson(__invoiceObj));
      }

      builder.unbilledCharges(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "unbilled_charges"), UnbilledCharge::fromJson));

      builder.creditNotes(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "credit_notes"), CreditNote::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionCancelForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionCancelForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionCancelForItemsResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private Invoice invoice;

    private List<UnbilledCharge> unbilledCharges;

    private List<CreditNote> creditNotes;

    private Response httpResponse;

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

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public Builder creditNotes(List<CreditNote> creditNotes) {
      this.creditNotes = creditNotes;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionCancelForItemsResponse build() {
      return new SubscriptionCancelForItemsResponse(this);
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

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }

  /** Get the creditNotes from the response. */
  public List<CreditNote> getCreditNotes() {
    return creditNotes;
  }

  @Override
  public String toString() {
    return "SubscriptionCancelForItemsResponse{"
        + "subscription="
        + subscription
        + ", customer="
        + customer
        + ", card="
        + card
        + ", invoice="
        + invoice
        + ", unbilledCharges="
        + unbilledCharges
        + ", creditNotes="
        + creditNotes
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionCancelForItemsResponse that = (SubscriptionCancelForItemsResponse) o;
    return java.util.Objects.equals(subscription, that.subscription)
        && java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card)
        && java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(unbilledCharges, that.unbilledCharges)
        && java.util.Objects.equals(creditNotes, that.creditNotes);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(
        subscription, customer, card, invoice, unbilledCharges, creditNotes);
  }
}
