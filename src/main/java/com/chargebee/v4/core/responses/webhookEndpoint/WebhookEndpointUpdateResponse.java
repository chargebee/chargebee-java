package com.chargebee.v4.core.responses.webhookEndpoint;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for WebhookEndpointUpdate operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointUpdateResponse {

  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointUpdateResponse(Builder builder) {

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointUpdateResponse object. */
  public static WebhookEndpointUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for WebhookEndpointUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for WebhookEndpointUpdateResponse. */
  public static class Builder {

    private WebhookEndpoint webhookEndpoint;

    private Builder() {}

    public Builder webhookEndpoint(WebhookEndpoint webhookEndpoint) {
      this.webhookEndpoint = webhookEndpoint;
      return this;
    }

    public WebhookEndpointUpdateResponse build() {
      return new WebhookEndpointUpdateResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
