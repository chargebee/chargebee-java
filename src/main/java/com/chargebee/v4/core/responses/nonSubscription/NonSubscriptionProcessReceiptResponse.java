package com.chargebee.v4.core.responses.nonSubscription;

import com.chargebee.v4.core.models.nonSubscription.NonSubscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for NonSubscriptionProcessReceipt operation. Contains the response data
 * from the API.
 */
public final class NonSubscriptionProcessReceiptResponse {

  private final NonSubscription nonSubscription;

  private final Response httpResponse;

  private NonSubscriptionProcessReceiptResponse(Builder builder) {

    this.nonSubscription = builder.nonSubscription;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into NonSubscriptionProcessReceiptResponse object. */
  public static NonSubscriptionProcessReceiptResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into NonSubscriptionProcessReceiptResponse object with HTTP response. */
  public static NonSubscriptionProcessReceiptResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __nonSubscriptionJson = JsonUtil.getObject(json, "non_subscription");
      if (__nonSubscriptionJson != null) {
        builder.nonSubscription(NonSubscription.fromJson(__nonSubscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse NonSubscriptionProcessReceiptResponse from JSON", e);
    }
  }

  /** Create a new builder for NonSubscriptionProcessReceiptResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for NonSubscriptionProcessReceiptResponse. */
  public static class Builder {

    private NonSubscription nonSubscription;

    private Response httpResponse;

    private Builder() {}

    public Builder nonSubscription(NonSubscription nonSubscription) {
      this.nonSubscription = nonSubscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public NonSubscriptionProcessReceiptResponse build() {
      return new NonSubscriptionProcessReceiptResponse(this);
    }
  }

  /** Get the nonSubscription from the response. */
  public NonSubscription getNonSubscription() {
    return nonSubscription;
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
