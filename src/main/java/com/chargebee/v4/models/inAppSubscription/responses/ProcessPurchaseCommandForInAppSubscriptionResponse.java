package com.chargebee.v4.models.inAppSubscription.responses;

import com.chargebee.v4.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProcessPurchaseCommandForInAppSubscription operation. Contains the
 * response data from the API.
 */
public final class ProcessPurchaseCommandForInAppSubscriptionResponse extends BaseResponse {
  private final InAppSubscription inAppSubscription;

  private ProcessPurchaseCommandForInAppSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscription = builder.inAppSubscription;
  }

  /** Parse JSON response into ProcessPurchaseCommandForInAppSubscriptionResponse object. */
  public static ProcessPurchaseCommandForInAppSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ProcessPurchaseCommandForInAppSubscriptionResponse object with HTTP
   * response.
   */
  public static ProcessPurchaseCommandForInAppSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __inAppSubscriptionJson = JsonUtil.getObject(json, "in_app_subscription");
      if (__inAppSubscriptionJson != null) {
        builder.inAppSubscription(InAppSubscription.fromJson(__inAppSubscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ProcessPurchaseCommandForInAppSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for ProcessPurchaseCommandForInAppSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProcessPurchaseCommandForInAppSubscriptionResponse. */
  public static class Builder {

    private InAppSubscription inAppSubscription;

    private Response httpResponse;

    private Builder() {}

    public Builder inAppSubscription(InAppSubscription inAppSubscription) {
      this.inAppSubscription = inAppSubscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ProcessPurchaseCommandForInAppSubscriptionResponse build() {
      return new ProcessPurchaseCommandForInAppSubscriptionResponse(this);
    }
  }

  /** Get the inAppSubscription from the response. */
  public InAppSubscription getInAppSubscription() {
    return inAppSubscription;
  }
}
