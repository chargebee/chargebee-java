package com.chargebee.v4.core.responses.webhookEndpoint;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointCreate operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointCreateResponse extends BaseResponse {
  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointCreateResponse object. */
  public static WebhookEndpointCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into WebhookEndpointCreateResponse object with HTTP response. */
  public static WebhookEndpointCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      builder.httpResponse(httpResponse);
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

    public WebhookEndpointCreateResponse build() {
      return new WebhookEndpointCreateResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
