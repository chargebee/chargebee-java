package com.chargebee.core.responses.recordedPurchase;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.recordedPurchase.RecordedPurchase;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for RecordedPurchaseCreate operation. Contains the response data from
 * the API.
 */
public final class RecordedPurchaseCreateResponse {

  private final RecordedPurchase recordedPurchase;

  private final Customer customer;

  private RecordedPurchaseCreateResponse(Builder builder) {

    this.recordedPurchase = builder.recordedPurchase;

    this.customer = builder.customer;
  }

  /** Parse JSON response into RecordedPurchaseCreateResponse object. */
  public static RecordedPurchaseCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __recordedPurchaseJson = JsonUtil.getObject(json, "recorded_purchase");
      if (__recordedPurchaseJson != null) {
        builder.recordedPurchase(RecordedPurchase.fromJson(__recordedPurchaseJson));
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordedPurchaseCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for RecordedPurchaseCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RecordedPurchaseCreateResponse. */
  public static class Builder {

    private RecordedPurchase recordedPurchase;

    private Customer customer;

    private Builder() {}

    public Builder recordedPurchase(RecordedPurchase recordedPurchase) {
      this.recordedPurchase = recordedPurchase;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public RecordedPurchaseCreateResponse build() {
      return new RecordedPurchaseCreateResponse(this);
    }
  }

  /** Get the recordedPurchase from the response. */
  public RecordedPurchase getRecordedPurchase() {
    return recordedPurchase;
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
