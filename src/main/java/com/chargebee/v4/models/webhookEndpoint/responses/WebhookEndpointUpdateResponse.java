package com.chargebee.v4.models.webhookEndpoint.responses;

import com.chargebee.v4.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for WebhookEndpointUpdate operation. Contains the response data from
 * the API.
 */
public final class WebhookEndpointUpdateResponse extends BaseResponse {
  private final WebhookEndpoint webhookEndpoint;

  private WebhookEndpointUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.webhookEndpoint = builder.webhookEndpoint;
  }

  /** Parse JSON response into WebhookEndpointUpdateResponse object. */
  public static WebhookEndpointUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into WebhookEndpointUpdateResponse object with HTTP response. */
  public static WebhookEndpointUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        builder.webhookEndpoint(WebhookEndpoint.fromJson(__webhookEndpointJson));
      }

      builder.httpResponse(httpResponse);
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

    public WebhookEndpointUpdateResponse build() {
      return new WebhookEndpointUpdateResponse(this);
    }
  }

  /** Get the webhookEndpoint from the response. */
  public WebhookEndpoint getWebhookEndpoint() {
    return webhookEndpoint;
  }

  @Override
  public String toString() {
    return "WebhookEndpointUpdateResponse{" + "webhookEndpoint=" + webhookEndpoint + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WebhookEndpointUpdateResponse that = (WebhookEndpointUpdateResponse) o;
    return java.util.Objects.equals(webhookEndpoint, that.webhookEndpoint);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(webhookEndpoint);
  }
}
