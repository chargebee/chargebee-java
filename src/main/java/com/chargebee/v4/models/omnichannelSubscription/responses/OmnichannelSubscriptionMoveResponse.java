package com.chargebee.v4.models.omnichannelSubscription.responses;

import com.chargebee.v4.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelSubscriptionMove operation. Contains the response data
 * from the API.
 */
public final class OmnichannelSubscriptionMoveResponse extends BaseResponse {
  private final OmnichannelSubscription omnichannelSubscription;

  private OmnichannelSubscriptionMoveResponse(Builder builder) {
    super(builder.httpResponse);

    this.omnichannelSubscription = builder.omnichannelSubscription;
  }

  /** Parse JSON response into OmnichannelSubscriptionMoveResponse object. */
  public static OmnichannelSubscriptionMoveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelSubscriptionMoveResponse object with HTTP response. */
  public static OmnichannelSubscriptionMoveResponse fromJson(String json, Response httpResponse) {
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

    public OmnichannelSubscriptionMoveResponse build() {
      return new OmnichannelSubscriptionMoveResponse(this);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
  }

  @Override
  public String toString() {
    return "OmnichannelSubscriptionMoveResponse{"
        + "omnichannelSubscription="
        + omnichannelSubscription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OmnichannelSubscriptionMoveResponse that = (OmnichannelSubscriptionMoveResponse) o;
    return java.util.Objects.equals(omnichannelSubscription, that.omnichannelSubscription);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(omnichannelSubscription);
  }
}
