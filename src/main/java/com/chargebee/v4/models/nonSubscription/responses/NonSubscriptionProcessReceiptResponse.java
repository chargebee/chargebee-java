package com.chargebee.v4.models.nonSubscription.responses;

import com.chargebee.v4.models.nonSubscription.NonSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for NonSubscriptionProcessReceipt operation. Contains the response data
 * from the API.
 */
public final class NonSubscriptionProcessReceiptResponse extends BaseResponse {
  private final NonSubscription nonSubscription;

  private NonSubscriptionProcessReceiptResponse(Builder builder) {
    super(builder.httpResponse);

    this.nonSubscription = builder.nonSubscription;
  }

  /** Parse JSON response into NonSubscriptionProcessReceiptResponse object. */
  public static NonSubscriptionProcessReceiptResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into NonSubscriptionProcessReceiptResponse object with HTTP response. */
  public static NonSubscriptionProcessReceiptResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __nonSubscriptionJson = JsonUtil.getObject(json, "non_subscription");
      if (__nonSubscriptionJson != null) {
        builder.nonSubscription(NonSubscription.fromJson(__nonSubscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse NonSubscriptionProcessReceiptResponse from JSON", e);
    }
  }

  /** Create a new builder for NonSubscriptionProcessReceiptResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for NonSubscriptionProcessReceiptResponse. */
  public static class Builder {

    private NonSubscription nonSubscription;

    private Response httpResponse;

    private Builder() {}

    public Builder nonSubscription(NonSubscription nonSubscription) {
      this.nonSubscription = nonSubscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public NonSubscriptionProcessReceiptResponse build() {
      return new NonSubscriptionProcessReceiptResponse(this);
    }
  }

  /** Get the nonSubscription from the response. */
  public NonSubscription getNonSubscription() {
    return nonSubscription;
  }

  @Override
  public String toString() {
    return "NonSubscriptionProcessReceiptResponse{" + "nonSubscription=" + nonSubscription + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NonSubscriptionProcessReceiptResponse that = (NonSubscriptionProcessReceiptResponse) o;
    return java.util.Objects.equals(nonSubscription, that.nonSubscription);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(nonSubscription);
  }
}
