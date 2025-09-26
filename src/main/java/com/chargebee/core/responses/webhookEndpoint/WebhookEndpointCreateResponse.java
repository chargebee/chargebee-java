package com.chargebee.core.responses.webhookEndpoint;

import com.chargebee.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for WebhookEndpointCreate operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointCreateResponse {

  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointCreateResponse(Builder builder) {

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointCreateResponse object. */
  public static WebhookEndpointCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for WebhookEndpointCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for WebhookEndpointCreateResponse. */
  public static class Builder {

    private WebhookEndpoint webhookEndpoint;

    private Builder() {}

    public Builder webhookEndpoint(WebhookEndpoint webhookEndpoint) {
      this.webhookEndpoint = webhookEndpoint;
      return this;
    }

    public WebhookEndpointCreateResponse build() {
      return new WebhookEndpointCreateResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
