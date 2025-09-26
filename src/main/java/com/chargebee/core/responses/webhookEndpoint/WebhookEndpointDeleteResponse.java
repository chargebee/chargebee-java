package com.chargebee.core.responses.webhookEndpoint;

import com.chargebee.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for WebhookEndpointDelete operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointDeleteResponse {

  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointDeleteResponse(Builder builder) {

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointDeleteResponse object. */
  public static WebhookEndpointDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for WebhookEndpointDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for WebhookEndpointDeleteResponse. */
  public static class Builder {

    private WebhookEndpoint webhookEndpoint;

    private Builder() {}

    public Builder webhookEndpoint(WebhookEndpoint webhookEndpoint) {
      this.webhookEndpoint = webhookEndpoint;
      return this;
    }

    public WebhookEndpointDeleteResponse build() {
      return new WebhookEndpointDeleteResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
