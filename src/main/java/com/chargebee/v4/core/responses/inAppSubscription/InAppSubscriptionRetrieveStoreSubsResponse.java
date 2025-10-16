package com.chargebee.v4.core.responses.inAppSubscription;

import java.util.List;

import com.chargebee.v4.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionRetrieveStoreSubs operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionRetrieveStoreSubsResponse extends BaseResponse {
  private final List<InAppSubscription> inAppSubscriptions;

  private InAppSubscriptionRetrieveStoreSubsResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into InAppSubscriptionRetrieveStoreSubsResponse object. */
  public static InAppSubscriptionRetrieveStoreSubsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into InAppSubscriptionRetrieveStoreSubsResponse object with HTTP response.
   */
  public static InAppSubscriptionRetrieveStoreSubsResponse fromJson(
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

    public InAppSubscriptionRetrieveStoreSubsResponse build() {
      return new InAppSubscriptionRetrieveStoreSubsResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }
}
