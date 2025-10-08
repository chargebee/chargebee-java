package com.chargebee.v4.core.responses.omnichannelSubscription;

import com.chargebee.v4.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelSubscriptionMove operation. Contains the response data
 * from the API.
 */
public final class OmnichannelSubscriptionMoveResponse {

  private final OmnichannelSubscription omnichannelSubscription;

  private final Response httpResponse;

  private OmnichannelSubscriptionMoveResponse(Builder builder) {

    this.omnichannelSubscription = builder.omnichannelSubscription;

    this.httpResponse = builder.httpResponse;
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
