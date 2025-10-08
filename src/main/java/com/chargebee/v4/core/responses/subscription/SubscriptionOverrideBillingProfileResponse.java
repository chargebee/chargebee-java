package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionOverrideBillingProfile operation. Contains the response
 * data from the API.
 */
public final class SubscriptionOverrideBillingProfileResponse {

  private final Subscription subscription;

  private final PaymentSource paymentSource;

  private final Response httpResponse;

  private SubscriptionOverrideBillingProfileResponse(Builder builder) {

    this.subscription = builder.subscription;

    this.paymentSource = builder.paymentSource;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into SubscriptionOverrideBillingProfileResponse object. */
  public static SubscriptionOverrideBillingProfileResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionOverrideBillingProfileResponse object with HTTP response.
   */
  public static SubscriptionOverrideBillingProfileResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionOverrideBillingProfileResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionOverrideBillingProfileResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionOverrideBillingProfileResponse. */
  public static class Builder {

    private Subscription subscription;

    private PaymentSource paymentSource;

    private Response httpResponse;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionOverrideBillingProfileResponse build() {
      return new SubscriptionOverrideBillingProfileResponse(this);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
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
