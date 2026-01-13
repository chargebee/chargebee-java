package com.chargebee.v4.models.purchase.responses;

import com.chargebee.v4.models.purchase.Purchase;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PurchaseCreate operation. Contains the response data from the API.
 */
public final class PurchaseCreateResponse extends BaseResponse {
  private final Purchase purchase;

  private PurchaseCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.purchase = builder.purchase;
  }

  /** Parse JSON response into PurchaseCreateResponse object. */
  public static PurchaseCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PurchaseCreateResponse object with HTTP response. */
  public static PurchaseCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __purchaseJson = JsonUtil.getObject(json, "purchase");
      if (__purchaseJson != null) {
        builder.purchase(Purchase.fromJson(__purchaseJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder purchase(Purchase purchase) {
      this.purchase = purchase;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "PurchaseCreateResponse{" + "purchase=" + purchase + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PurchaseCreateResponse that = (PurchaseCreateResponse) o;
    return java.util.Objects.equals(purchase, that.purchase);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(purchase);
  }
}
