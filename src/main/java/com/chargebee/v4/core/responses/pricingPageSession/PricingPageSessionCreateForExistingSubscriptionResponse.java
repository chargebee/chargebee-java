package com.chargebee.v4.core.responses.pricingPageSession;

import com.chargebee.v4.core.models.pricingPageSession.PricingPageSession;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PricingPageSessionCreateForExistingSubscription operation. Contains
 * the response data from the API.
 */
public final class PricingPageSessionCreateForExistingSubscriptionResponse {

  private final PricingPageSession pricingPageSession;

  private final Response httpResponse;

  private PricingPageSessionCreateForExistingSubscriptionResponse(Builder builder) {

    this.pricingPageSession = builder.pricingPageSession;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PricingPageSessionCreateForExistingSubscriptionResponse object. */
  public static PricingPageSessionCreateForExistingSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into PricingPageSessionCreateForExistingSubscriptionResponse object with
   * HTTP response.
   */
  public static PricingPageSessionCreateForExistingSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __pricingPageSessionJson = JsonUtil.getObject(json, "pricing_page_session");
      if (__pricingPageSessionJson != null) {
        builder.pricingPageSession(PricingPageSession.fromJson(__pricingPageSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PricingPageSessionCreateForExistingSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for PricingPageSessionCreateForExistingSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PricingPageSessionCreateForExistingSubscriptionResponse. */
  public static class Builder {

    private PricingPageSession pricingPageSession;

    private Response httpResponse;

    private Builder() {}

    public Builder pricingPageSession(PricingPageSession pricingPageSession) {
      this.pricingPageSession = pricingPageSession;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PricingPageSessionCreateForExistingSubscriptionResponse build() {
      return new PricingPageSessionCreateForExistingSubscriptionResponse(this);
    }
  }

  /** Get the pricingPageSession from the response. */
  public PricingPageSession getPricingPageSession() {
    return pricingPageSession;
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
