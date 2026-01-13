package com.chargebee.v4.models.inAppSubscription.responses;

import com.chargebee.v4.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionImportSubscription operation. Contains the
 * response data from the API.
 */
public final class InAppSubscriptionImportSubscriptionResponse extends BaseResponse {
  private final InAppSubscription inAppSubscription;

  private InAppSubscriptionImportSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscription = builder.inAppSubscription;
  }

  /** Parse JSON response into InAppSubscriptionImportSubscriptionResponse object. */
  public static InAppSubscriptionImportSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into InAppSubscriptionImportSubscriptionResponse object with HTTP response.
   */
  public static InAppSubscriptionImportSubscriptionResponse fromJson(
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

    public InAppSubscriptionImportSubscriptionResponse build() {
      return new InAppSubscriptionImportSubscriptionResponse(this);
    }
  }

  /** Get the inAppSubscription from the response. */
  public InAppSubscription getInAppSubscription() {
    return inAppSubscription;
  }

  @Override
  public String toString() {
    return "InAppSubscriptionImportSubscriptionResponse{"
        + "inAppSubscription="
        + inAppSubscription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InAppSubscriptionImportSubscriptionResponse that =
        (InAppSubscriptionImportSubscriptionResponse) o;
    return java.util.Objects.equals(inAppSubscription, that.inAppSubscription);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(inAppSubscription);
  }
}
