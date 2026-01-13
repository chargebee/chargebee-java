package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionCreateForCustomer operation. Contains the response data
 * from the API.
 */
public final class SubscriptionCreateForCustomerResponse extends BaseResponse {
  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Invoice invoice;

  private final List<UnbilledCharge> unbilledCharges;

  private SubscriptionCreateForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.invoice = builder.invoice;

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into SubscriptionCreateForCustomerResponse object. */
  public static SubscriptionCreateForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionCreateForCustomerResponse object with HTTP response. */
  public static SubscriptionCreateForCustomerResponse fromJson(String json, Response httpResponse) {
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

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionCreateForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionCreateForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionCreateForCustomerResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private Invoice invoice;

    private List<UnbilledCharge> unbilledCharges;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionCreateForCustomerResponse build() {
      return new SubscriptionCreateForCustomerResponse(this);
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

  @Override
  public String toString() {
    return "SubscriptionCreateForCustomerResponse{"
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
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionCreateForCustomerResponse that = (SubscriptionCreateForCustomerResponse) o;
    return java.util.Objects.equals(subscription, that.subscription)
        && java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card)
        && java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(unbilledCharges, that.unbilledCharges);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(subscription, customer, card, invoice, unbilledCharges);
  }
}
