package com.chargebee.v4.models.omnichannelSubscription.responses;

import com.chargebee.v4.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelSubscriptionRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelSubscriptionRetrieveResponse extends BaseResponse {
  private final OmnichannelSubscription omnichannelSubscription;

  private OmnichannelSubscriptionRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.omnichannelSubscription = builder.omnichannelSubscription;
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object with HTTP response. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(
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
          "Failed to parse OmnichannelSubscriptionRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for OmnichannelSubscriptionRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OmnichannelSubscriptionRetrieveResponse. */
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

    public OmnichannelSubscriptionRetrieveResponse build() {
      return new OmnichannelSubscriptionRetrieveResponse(this);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
  }

  @Override
  public String toString() {
    return "OmnichannelSubscriptionRetrieveResponse{"
        + "omnichannelSubscription="
        + omnichannelSubscription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OmnichannelSubscriptionRetrieveResponse that = (OmnichannelSubscriptionRetrieveResponse) o;
    return java.util.Objects.equals(omnichannelSubscription, that.omnichannelSubscription);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(omnichannelSubscription);
  }
}
