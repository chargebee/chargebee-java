package com.chargebee.v4.core.responses.inAppSubscription;

import java.util.List;

import com.chargebee.v4.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InAppSubscriptionImportReceipt operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionImportReceiptResponse {

  private final List<InAppSubscription> inAppSubscriptions;

  private InAppSubscriptionImportReceiptResponse(Builder builder) {

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into InAppSubscriptionImportReceiptResponse object. */
  public static InAppSubscriptionImportReceiptResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.inAppSubscriptions(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "in_app_subscriptions")).stream()
              .map(InAppSubscription::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InAppSubscriptionImportReceiptResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionImportReceiptResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionImportReceiptResponse. */
  public static class Builder {

    private List<InAppSubscription> inAppSubscriptions;

    private Builder() {}

    public Builder inAppSubscriptions(List<InAppSubscription> inAppSubscriptions) {
      this.inAppSubscriptions = inAppSubscriptions;
      return this;
    }

    public InAppSubscriptionImportReceiptResponse build() {
      return new InAppSubscriptionImportReceiptResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }
}
