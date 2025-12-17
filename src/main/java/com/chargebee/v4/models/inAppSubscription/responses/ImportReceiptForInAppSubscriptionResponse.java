package com.chargebee.v4.models.inAppSubscription.responses;

import java.util.List;

import com.chargebee.v4.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ImportReceiptForInAppSubscription operation. Contains the response
 * data from the API.
 */
public final class ImportReceiptForInAppSubscriptionResponse extends BaseResponse {
  private final List<InAppSubscription> inAppSubscriptions;

  private ImportReceiptForInAppSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.inAppSubscriptions = builder.inAppSubscriptions;
  }

  /** Parse JSON response into ImportReceiptForInAppSubscriptionResponse object. */
  public static ImportReceiptForInAppSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ImportReceiptForInAppSubscriptionResponse object with HTTP response.
   */
  public static ImportReceiptForInAppSubscriptionResponse fromJson(
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
          "Failed to parse ImportReceiptForInAppSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for ImportReceiptForInAppSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ImportReceiptForInAppSubscriptionResponse. */
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

    public ImportReceiptForInAppSubscriptionResponse build() {
      return new ImportReceiptForInAppSubscriptionResponse(this);
    }
  }

  /** Get the inAppSubscriptions from the response. */
  public List<InAppSubscription> getInAppSubscriptions() {
    return inAppSubscriptions;
  }
}
