package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionChargeFutureRenewals operation. Contains the response
 * data from the API.
 */
public final class SubscriptionChargeFutureRenewalsResponse extends BaseResponse {
  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Invoice invoice;

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionChargeFutureRenewalsResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.invoice = builder.invoice;

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
  }

  /** Parse JSON response into SubscriptionChargeFutureRenewalsResponse object. */
  public static SubscriptionChargeFutureRenewalsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionChargeFutureRenewalsResponse object with HTTP response.
   */
  public static SubscriptionChargeFutureRenewalsResponse fromJson(
      String json, Response httpResponse) {
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

      builder.advanceInvoiceSchedules(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionChargeFutureRenewalsResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionChargeFutureRenewalsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionChargeFutureRenewalsResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private Invoice invoice;

    private List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

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

    public Builder advanceInvoiceSchedules(List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {
      this.advanceInvoiceSchedules = advanceInvoiceSchedules;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionChargeFutureRenewalsResponse build() {
      return new SubscriptionChargeFutureRenewalsResponse(this);
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

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }

  @Override
  public String toString() {
    return "SubscriptionChargeFutureRenewalsResponse{"
        + "subscription="
        + subscription
        + ", customer="
        + customer
        + ", card="
        + card
        + ", invoice="
        + invoice
        + ", advanceInvoiceSchedules="
        + advanceInvoiceSchedules
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionChargeFutureRenewalsResponse that = (SubscriptionChargeFutureRenewalsResponse) o;
    return java.util.Objects.equals(subscription, that.subscription)
        && java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card)
        && java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(advanceInvoiceSchedules, that.advanceInvoiceSchedules);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(subscription, customer, card, invoice, advanceInvoiceSchedules);
  }
}
