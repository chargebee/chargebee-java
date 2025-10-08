package com.chargebee.v4.core.responses.quote;

import java.util.List;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.core.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteConvert operation. Contains the response data from the API.
 */
public final class QuoteConvertResponse {

  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedCharge quotedCharge;

  private final QuotedRamp quotedRamp;

  private final Customer customer;

  private final Subscription subscription;

  private final Invoice invoice;

  private final CreditNote creditNote;

  private final List<UnbilledCharge> unbilledCharges;

  private final Response httpResponse;

  private QuoteConvertResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedCharge = builder.quotedCharge;

    this.quotedRamp = builder.quotedRamp;

    this.customer = builder.customer;

    this.subscription = builder.subscription;

    this.invoice = builder.invoice;

    this.creditNote = builder.creditNote;

    this.unbilledCharges = builder.unbilledCharges;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into QuoteConvertResponse object. */
  public static QuoteConvertResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteConvertResponse object with HTTP response. */
  public static QuoteConvertResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        builder.quote(Quote.fromJson(__quoteJson));
      }

      String __quotedSubscriptionJson = JsonUtil.getObject(json, "quoted_subscription");
      if (__quotedSubscriptionJson != null) {
        builder.quotedSubscription(QuotedSubscription.fromJson(__quotedSubscriptionJson));
      }

      String __quotedChargeJson = JsonUtil.getObject(json, "quoted_charge");
      if (__quotedChargeJson != null) {
        builder.quotedCharge(QuotedCharge.fromJson(__quotedChargeJson));
      }

      String __quotedRampJson = JsonUtil.getObject(json, "quoted_ramp");
      if (__quotedRampJson != null) {
        builder.quotedRamp(QuotedRamp.fromJson(__quotedRampJson));
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteConvertResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteConvertResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteConvertResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedCharge quotedCharge;

    private QuotedRamp quotedRamp;

    private Customer customer;

    private Subscription subscription;

    private Invoice invoice;

    private CreditNote creditNote;

    private List<UnbilledCharge> unbilledCharges;

    private Response httpResponse;

    private Builder() {}

    public Builder quote(Quote quote) {
      this.quote = quote;
      return this;
    }

    public Builder quotedSubscription(QuotedSubscription quotedSubscription) {
      this.quotedSubscription = quotedSubscription;
      return this;
    }

    public Builder quotedCharge(QuotedCharge quotedCharge) {
      this.quotedCharge = quotedCharge;
      return this;
    }

    public Builder quotedRamp(QuotedRamp quotedRamp) {
      this.quotedRamp = quotedRamp;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
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

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
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

    public QuoteConvertResponse build() {
      return new QuoteConvertResponse(this);
    }
  }

  /** Get the quote from the response. */
  public Quote getQuote() {
    return quote;
  }

  /** Get the quotedSubscription from the response. */
  public QuotedSubscription getQuotedSubscription() {
    return quotedSubscription;
  }

  /** Get the quotedCharge from the response. */
  public QuotedCharge getQuotedCharge() {
    return quotedCharge;
  }

  /** Get the quotedRamp from the response. */
  public QuotedRamp getQuotedRamp() {
    return quotedRamp;
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
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
