package com.chargebee.core.responses.inAppSubscription;

import java.util.List;

import com.chargebee.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InAppSubscriptionRetrieveStoreSubs operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionRetrieveStoreSubsResponse {

  private final List<InAppSubscription> inAppSubscriptions;

  private InAppSubscriptionRetrieveStoreSubsResponse(Builder builder) {

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into InAppSubscriptionRetrieveStoreSubsResponse object. */
  public static InAppSubscriptionRetrieveStoreSubsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.inAppSubscriptions(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "in_app_subscriptions")).stream()
              .map(InAppSubscription::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InAppSubscriptionRetrieveStoreSubsResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionRetrieveStoreSubsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionRetrieveStoreSubsResponse. */
  public static class Builder {

    private List<InAppSubscription> inAppSubscriptions;

    private Builder() {}

    public Builder inAppSubscriptions(List<InAppSubscription> inAppSubscriptions) {
      this.inAppSubscriptions = inAppSubscriptions;
      return this;
    }

    public InAppSubscriptionRetrieveStoreSubsResponse build() {
      return new InAppSubscriptionRetrieveStoreSubsResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }
}
