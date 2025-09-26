package com.chargebee.core.responses.omnichannelSubscription;

import com.chargebee.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for OmnichannelSubscriptionMove operation. Contains the response data
 * from the API.
 */
public final class OmnichannelSubscriptionMoveResponse {

  private final OmnichannelSubscription omnichannelSubscription;

  private OmnichannelSubscriptionMoveResponse(Builder builder) {

    this.omnichannelSubscription = builder.omnichannelSubscription;
  }

  /** Parse JSON response into OmnichannelSubscriptionMoveResponse object. */
  public static OmnichannelSubscriptionMoveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __omnichannelSubscriptionJson = JsonUtil.getObject(json, "omnichannel_subscription");
      if (__omnichannelSubscriptionJson != null) {
        builder.omnichannelSubscription(
            OmnichannelSubscription.fromJson(__omnichannelSubscriptionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionMoveResponse from JSON", e);
    }
  }

  /** Create a new builder for OmnichannelSubscriptionMoveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OmnichannelSubscriptionMoveResponse. */
  public static class Builder {

    private OmnichannelSubscription omnichannelSubscription;

    private Builder() {}

    public Builder omnichannelSubscription(OmnichannelSubscription omnichannelSubscription) {
      this.omnichannelSubscription = omnichannelSubscription;
      return this;
    }

    public OmnichannelSubscriptionMoveResponse build() {
      return new OmnichannelSubscriptionMoveResponse(this);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
  }
}
