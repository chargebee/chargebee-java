package com.chargebee.v4.core.responses.webhookEndpoint;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointDelete operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointDeleteResponse {

  private final WebhookEndpoint webhookEndpoint;

  private final Response httpResponse;

  private WebhookEndpointDeleteResponse(Builder builder) {

    this.webhookEndpoint = builder.webhookEndpoint;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into WebhookEndpointDeleteResponse object. */
  public static WebhookEndpointDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into WebhookEndpointDeleteResponse object with HTTP response. */
  public static WebhookEndpointDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder webhookEndpoint(WebhookEndpoint webhookEndpoint) {
      this.webhookEndpoint = webhookEndpoint;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
