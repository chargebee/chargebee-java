package com.chargebee.v4.core.responses.purchase;

import com.chargebee.v4.core.models.purchase.Purchase;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PurchaseCreate operation. Contains the response data from the API.
 */
public final class PurchaseCreateResponse {

  private final Purchase purchase;

  private PurchaseCreateResponse(Builder builder) {

    this.purchase = builder.purchase;
  }

  /** Parse JSON response into PurchaseCreateResponse object. */
  public static PurchaseCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __purchaseJson = JsonUtil.getObject(json, "purchase");
      if (__purchaseJson != null) {
        builder.purchase(Purchase.fromJson(__purchaseJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PurchaseCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PurchaseCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PurchaseCreateResponse. */
  public static class Builder {

    private Purchase purchase;

    private Builder() {}

    public Builder purchase(Purchase purchase) {
      this.purchase = purchase;
      return this;
    }

    public PurchaseCreateResponse build() {
      return new PurchaseCreateResponse(this);
    }
  }

  /** Get the purchase from the response. */
  public Purchase getPurchase() {
    return purchase;
  }
}
