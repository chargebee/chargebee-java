package com.chargebee.v4.core.responses.purchase;

import com.chargebee.v4.core.models.purchase.Purchase;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PurchaseCreate operation. Contains the response data from the API.
 */
public final class PurchaseCreateResponse {

  private final Purchase purchase;

  private final Response httpResponse;

  private PurchaseCreateResponse(Builder builder) {

    this.purchase = builder.purchase;

    this.httpResponse = builder.httpResponse;
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
