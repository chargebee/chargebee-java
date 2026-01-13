package com.chargebee.v4.models.inAppSubscription.responses;

import java.util.List;

import com.chargebee.v4.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionImportReceipt operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionImportReceiptResponse extends BaseResponse {
  private final List<InAppSubscription> inAppSubscriptions;

  private InAppSubscriptionImportReceiptResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into InAppSubscriptionImportReceiptResponse object. */
  public static InAppSubscriptionImportReceiptResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InAppSubscriptionImportReceiptResponse object with HTTP response. */
  public static InAppSubscriptionImportReceiptResponse fromJson(
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
          "Failed to parse InAppSubscriptionImportReceiptResponse from JSON", e);
    }
  }

  /** Create a new builder for InAppSubscriptionImportReceiptResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InAppSubscriptionImportReceiptResponse. */
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

    public InAppSubscriptionImportReceiptResponse build() {
      return new InAppSubscriptionImportReceiptResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }

  @Override
  public String toString() {
    return "InAppSubscriptionImportReceiptResponse{"
        + "inAppSubscriptions="
        + inAppSubscriptions
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InAppSubscriptionImportReceiptResponse that = (InAppSubscriptionImportReceiptResponse) o;
    return java.util.Objects.equals(inAppSubscriptions, that.inAppSubscriptions);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(inAppSubscriptions);
  }
}
