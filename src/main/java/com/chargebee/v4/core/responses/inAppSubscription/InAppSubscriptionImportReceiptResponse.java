package com.chargebee.v4.core.responses.inAppSubscription;

import java.util.List;

import com.chargebee.v4.core.models.inAppSubscription.InAppSubscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InAppSubscriptionImportReceipt operation. Contains the response
 * data from the API.
 */
public final class InAppSubscriptionImportReceiptResponse {

  private final List<InAppSubscription> inAppSubscriptions;

  private final Response httpResponse;

  private InAppSubscriptionImportReceiptResponse(Builder builder) {

    this.inAppSubscriptions = builder.inAppSubscriptions;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
