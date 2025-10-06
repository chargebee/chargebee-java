package com.chargebee.v4.core.responses.nonSubscription;

import com.chargebee.v4.core.models.nonSubscription.NonSubscription;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for NonSubscriptionProcessReceipt operation. Contains the response data
 * from the API.
 */
public final class NonSubscriptionProcessReceiptResponse {

  private final NonSubscription nonSubscription;

  private NonSubscriptionProcessReceiptResponse(Builder builder) {

    this.nonSubscription = builder.nonSubscription;
  }

  /** Parse JSON response into NonSubscriptionProcessReceiptResponse object. */
  public static NonSubscriptionProcessReceiptResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __nonSubscriptionJson = JsonUtil.getObject(json, "non_subscription");
      if (__nonSubscriptionJson != null) {
        builder.nonSubscription(NonSubscription.fromJson(__nonSubscriptionJson));
      }

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

    private Builder() {}

    public Builder nonSubscription(NonSubscription nonSubscription) {
      this.nonSubscription = nonSubscription;
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
}
