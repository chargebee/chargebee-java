package com.chargebee.v4.models.omnichannelSubscription.responses;

import com.chargebee.v4.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MoveForOmnichannelSubscription operation. Contains the response
 * data from the API.
 */
public final class MoveForOmnichannelSubscriptionResponse extends BaseResponse {
  private final OmnichannelSubscription omnichannelSubscription;

  private MoveForOmnichannelSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.omnichannelSubscription = builder.omnichannelSubscription;
  }

  /** Parse JSON response into MoveForOmnichannelSubscriptionResponse object. */
  public static MoveForOmnichannelSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into MoveForOmnichannelSubscriptionResponse object with HTTP response. */
  public static MoveForOmnichannelSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __omnichannelSubscriptionJson = JsonUtil.getObject(json, "omnichannel_subscription");
      if (__omnichannelSubscriptionJson != null) {
        builder.omnichannelSubscription(
            OmnichannelSubscription.fromJson(__omnichannelSubscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse MoveForOmnichannelSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for MoveForOmnichannelSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for MoveForOmnichannelSubscriptionResponse. */
  public static class Builder {

    private OmnichannelSubscription omnichannelSubscription;

    private Response httpResponse;

    private Builder() {}

    public Builder omnichannelSubscription(OmnichannelSubscription omnichannelSubscription) {
      this.omnichannelSubscription = omnichannelSubscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public MoveForOmnichannelSubscriptionResponse build() {
      return new MoveForOmnichannelSubscriptionResponse(this);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
  }
}
