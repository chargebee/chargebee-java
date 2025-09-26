package com.chargebee.core.responses.webhookEndpoint;

import com.chargebee.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for WebhookEndpointRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class WebhookEndpointRetrieveResponse {

  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointRetrieveResponse(WebhookEndpoint webhookEndpoint) {

    this.webhookEndpoint = webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointRetrieveResponse object. */
  public static WebhookEndpointRetrieveResponse fromJson(String json) {
    try {

      WebhookEndpoint webhookEndpoint =
          WebhookEndpoint.fromJson(JsonUtil.getObject(json, "webhook_endpoint"));

      return new WebhookEndpointRetrieveResponse(webhookEndpoint);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointRetrieveResponse from JSON", e);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
