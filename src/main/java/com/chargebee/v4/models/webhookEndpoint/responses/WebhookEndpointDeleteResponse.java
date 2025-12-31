package com.chargebee.v4.models.webhookEndpoint.responses;

import com.chargebee.v4.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointDelete operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointDeleteResponse extends BaseResponse {
  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.webhookEndpoint = builder.webhookEndpoint;
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

  @Override
  public String toString() {
    return "WebhookEndpointDeleteResponse{" + "webhookEndpoint=" + webhookEndpoint + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WebhookEndpointDeleteResponse that = (WebhookEndpointDeleteResponse) o;
    return java.util.Objects.equals(webhookEndpoint, that.webhookEndpoint);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(webhookEndpoint);
  }
}
