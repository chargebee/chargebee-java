package com.chargebee.v4.models.webhookEndpoint.responses;

import com.chargebee.v4.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteWebhookEndpoint operation. Contains the response data from
 * the API.
 */
public final class DeleteWebhookEndpointResponse extends BaseResponse {
  private final WebhookEndpoint webhookEndpoint;

  private DeleteWebhookEndpointResponse(Builder builder) {
    super(builder.httpResponse);

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into DeleteWebhookEndpointResponse object. */
  public static DeleteWebhookEndpointResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteWebhookEndpointResponse object with HTTP response. */
  public static DeleteWebhookEndpointResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteWebhookEndpointResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteWebhookEndpointResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteWebhookEndpointResponse. */
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

    public DeleteWebhookEndpointResponse build() {
      return new DeleteWebhookEndpointResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }
}
