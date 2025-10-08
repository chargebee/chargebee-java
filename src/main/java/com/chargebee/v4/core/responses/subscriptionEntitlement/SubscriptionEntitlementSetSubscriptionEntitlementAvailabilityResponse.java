package com.chargebee.v4.core.responses.subscriptionEntitlement;

import com.chargebee.v4.core.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionEntitlementSetSubscriptionEntitlementAvailability
 * operation. Contains the response data from the API.
 */
public final class SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse {

  private final SubscriptionEntitlement subscriptionEntitlement;

  private final Response httpResponse;

  private SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(Builder builder) {

    this.subscriptionEntitlement = builder.subscriptionEntitlement;

    this.httpResponse = builder.httpResponse;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
   * object.
   */
  public static SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse fromJson(
      String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
   * object with HTTP response.
   */
  public static SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionEntitlementJson = JsonUtil.getObject(json, "subscription_entitlement");
      if (__subscriptionEntitlementJson != null) {
        builder.subscriptionEntitlement(
            SubscriptionEntitlement.fromJson(__subscriptionEntitlementJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse from JSON",
          e);
    }
  }

  /**
   * Create a new builder for SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse.
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse. */
  public static class Builder {

    private SubscriptionEntitlement subscriptionEntitlement;

    private Response httpResponse;

    private Builder() {}

    public Builder subscriptionEntitlement(SubscriptionEntitlement subscriptionEntitlement) {
      this.subscriptionEntitlement = subscriptionEntitlement;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse build() {
      return new SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(this);
    }
  }

  /** Get the subscriptionEntitlement from the response. */
  public SubscriptionEntitlement getSubscriptionEntitlement() {
    return subscriptionEntitlement;
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
