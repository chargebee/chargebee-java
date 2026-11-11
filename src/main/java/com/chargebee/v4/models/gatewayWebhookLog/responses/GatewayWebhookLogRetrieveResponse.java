package com.chargebee.v4.models.gatewayWebhookLog.responses;

import com.chargebee.v4.models.gatewayWebhookLog.GatewayWebhookLog;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for GatewayWebhookLogRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class GatewayWebhookLogRetrieveResponse extends BaseResponse {
  private final GatewayWebhookLog gatewayWebhookLog;

  private GatewayWebhookLogRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.gatewayWebhookLog = builder.gatewayWebhookLog;
  }

  /** Parse JSON response into GatewayWebhookLogRetrieveResponse object. */
  public static GatewayWebhookLogRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into GatewayWebhookLogRetrieveResponse object with HTTP response. */
  public static GatewayWebhookLogRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __gatewayWebhookLogObj = JsonUtil.getJsonObject(jsonObj, "gateway_webhook_log");
      if (__gatewayWebhookLogObj != null) {
        builder.gatewayWebhookLog(GatewayWebhookLog.fromJson(__gatewayWebhookLogObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayWebhookLogRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for GatewayWebhookLogRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for GatewayWebhookLogRetrieveResponse. */
  public static class Builder {

    private GatewayWebhookLog gatewayWebhookLog;

    private Response httpResponse;

    private Builder() {}

    public Builder gatewayWebhookLog(GatewayWebhookLog gatewayWebhookLog) {
      this.gatewayWebhookLog = gatewayWebhookLog;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public GatewayWebhookLogRetrieveResponse build() {
      return new GatewayWebhookLogRetrieveResponse(this);
    }
  }

  /** Get the gatewayWebhookLog from the response. */
  public GatewayWebhookLog getGatewayWebhookLog() {
    return gatewayWebhookLog;
  }

  @Override
  public String toString() {
    return "GatewayWebhookLogRetrieveResponse{" + "gatewayWebhookLog=" + gatewayWebhookLog + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GatewayWebhookLogRetrieveResponse that = (GatewayWebhookLogRetrieveResponse) o;
    return java.util.Objects.equals(gatewayWebhookLog, that.gatewayWebhookLog);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(gatewayWebhookLog);
  }
}
