package com.chargebee.v4.core.responses.webhookEndpoint;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class WebhookEndpointRetrieveResponse {

  private final WebhookEndpoint webhookEndpoint;

  private final Response httpResponse;

  private WebhookEndpointRetrieveResponse(WebhookEndpoint webhookEndpoint, Response httpResponse) {

    this.webhookEndpoint = webhookEndpoint;

    this.httpResponse = httpResponse;
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
