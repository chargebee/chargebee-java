package com.chargebee.v4.models.gatewayRequestLog.responses;

import com.chargebee.v4.models.gatewayRequestLog.GatewayRequestLog;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for GatewayRequestLogRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class GatewayRequestLogRetrieveResponse extends BaseResponse {
  private final GatewayRequestLog gatewayRequestLog;

  private GatewayRequestLogRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.gatewayRequestLog = builder.gatewayRequestLog;
  }

  /** Parse JSON response into GatewayRequestLogRetrieveResponse object. */
  public static GatewayRequestLogRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into GatewayRequestLogRetrieveResponse object with HTTP response. */
  public static GatewayRequestLogRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __gatewayRequestLogObj = JsonUtil.getJsonObject(jsonObj, "gateway_request_log");
      if (__gatewayRequestLogObj != null) {
        builder.gatewayRequestLog(GatewayRequestLog.fromJson(__gatewayRequestLogObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayRequestLogRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for GatewayRequestLogRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for GatewayRequestLogRetrieveResponse. */
  public static class Builder {

    private GatewayRequestLog gatewayRequestLog;

    private Response httpResponse;

    private Builder() {}

    public Builder gatewayRequestLog(GatewayRequestLog gatewayRequestLog) {
      this.gatewayRequestLog = gatewayRequestLog;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public GatewayRequestLogRetrieveResponse build() {
      return new GatewayRequestLogRetrieveResponse(this);
    }
  }

  /** Get the gatewayRequestLog from the response. */
  public GatewayRequestLog getGatewayRequestLog() {
    return gatewayRequestLog;
  }

  @Override
  public String toString() {
    return "GatewayRequestLogRetrieveResponse{" + "gatewayRequestLog=" + gatewayRequestLog + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GatewayRequestLogRetrieveResponse that = (GatewayRequestLogRetrieveResponse) o;
    return java.util.Objects.equals(gatewayRequestLog, that.gatewayRequestLog);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(gatewayRequestLog);
  }
}
