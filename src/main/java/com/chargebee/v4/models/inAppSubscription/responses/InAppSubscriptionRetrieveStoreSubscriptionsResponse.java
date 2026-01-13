package com.chargebee.v4.models.inAppSubscription.responses;

import java.util.List;

import com.chargebee.v4.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionRetrieveStoreSubscriptions operation. Contains the
 * response data from the API.
 */
public final class InAppSubscriptionRetrieveStoreSubscriptionsResponse extends BaseResponse {
  private final List<InAppSubscription> inAppSubscriptions;

  private InAppSubscriptionRetrieveStoreSubscriptionsResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into InAppSubscriptionRetrieveStoreSubscriptionsResponse object. */
  public static InAppSubscriptionRetrieveStoreSubscriptionsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into InAppSubscriptionRetrieveStoreSubscriptionsResponse object with HTTP
   * response.
   */
  public static InAppSubscriptionRetrieveStoreSubscriptionsResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.inAppSubscriptions(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "in_app_subscriptions")).stream()
              .map(InAppSubscription::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InAppSubscriptionRetrieveStoreSubscriptionsResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionRetrieveStoreSubscriptionsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionRetrieveStoreSubscriptionsResponse. */
  public static class Builder {

    private List<InAppSubscription> inAppSubscriptions;

    private Response httpResponse;

    private Builder() {}

    public Builder inAppSubscriptions(List<InAppSubscription> inAppSubscriptions) {
      this.inAppSubscriptions = inAppSubscriptions;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InAppSubscriptionRetrieveStoreSubscriptionsResponse build() {
      return new InAppSubscriptionRetrieveStoreSubscriptionsResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }

  @Override
  public String toString() {
    return "InAppSubscriptionRetrieveStoreSubscriptionsResponse{"
        + "inAppSubscriptions="
        + inAppSubscriptions
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InAppSubscriptionRetrieveStoreSubscriptionsResponse that =
        (InAppSubscriptionRetrieveStoreSubscriptionsResponse) o;
    return java.util.Objects.equals(inAppSubscriptions, that.inAppSubscriptions);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(inAppSubscriptions);
  }
}
