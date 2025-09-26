package com.chargebee.core.responses.inAppSubscription;

import com.chargebee.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InAppSubscriptionImportSubscription operation. Contains the
 * response data from the API.
 */
public final class InAppSubscriptionImportSubscriptionResponse {

  private final InAppSubscription inAppSubscription;

  private InAppSubscriptionImportSubscriptionResponse(Builder builder) {

    this.inAppSubscription = builder.inAppSubscription;
  }

  /** Parse JSON response into InAppSubscriptionImportSubscriptionResponse object. */
  public static InAppSubscriptionImportSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __inAppSubscriptionJson = JsonUtil.getObject(json, "in_app_subscription");
      if (__inAppSubscriptionJson != null) {
        builder.inAppSubscription(InAppSubscription.fromJson(__inAppSubscriptionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InAppSubscriptionImportSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionImportSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionImportSubscriptionResponse. */
  public static class Builder {

    private InAppSubscription inAppSubscription;

    private Builder() {}

    public Builder inAppSubscription(InAppSubscription inAppSubscription) {
      this.inAppSubscription = inAppSubscription;
      return this;
    }

    public InAppSubscriptionImportSubscriptionResponse build() {
      return new InAppSubscriptionImportSubscriptionResponse(this);
    }
  }

  /** Get the inAppSubscription from the response. */
  public InAppSubscription getInAppSubscription() {
    return inAppSubscription;
  }
}
