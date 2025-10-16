package com.chargebee.v4.core.responses.inAppSubscription;

import com.chargebee.v4.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionProcessReceipt operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionProcessReceiptResponse extends BaseResponse {
  private final InAppSubscription inAppSubscription;

  private InAppSubscriptionProcessReceiptResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscription = builder.inAppSubscription;
  }

  /** Parse JSON response into InAppSubscriptionProcessReceiptResponse object. */
  public static InAppSubscriptionProcessReceiptResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InAppSubscriptionProcessReceiptResponse object with HTTP response. */
  public static InAppSubscriptionProcessReceiptResponse fromJson(
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
          "Failed to parse InAppSubscriptionProcessReceiptResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionProcessReceiptResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionProcessReceiptResponse. */
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

    public InAppSubscriptionProcessReceiptResponse build() {
      return new InAppSubscriptionProcessReceiptResponse(this);
    }
  }

  /** Get the inAppSubscription from the response. */
  public InAppSubscription getInAppSubscription() {
    return inAppSubscription;
  }
}
