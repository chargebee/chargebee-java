package com.chargebee.v4.core.responses.webhookEndpoint;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class WebhookEndpointRetrieveResponse extends BaseResponse {
  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointRetrieveResponse(WebhookEndpoint webhookEndpoint, Response httpResponse) {
    super(httpResponse);

    this.webhookEndpoint = webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointRetrieveResponse object. */
  public static WebhookEndpointRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into WebhookEndpointRetrieveResponse object with HTTP response. */
  public static WebhookEndpointRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      WebhookEndpoint webhookEndpoint =
          WebhookEndpoint.fromJson(JsonUtil.getObject(json, "webhook_endpoint"));

      return new WebhookEndpointRetrieveResponse(webhookEndpoint, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointRetrieveResponse from JSON", e);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
