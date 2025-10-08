package com.chargebee.v4.core.responses.thirdPartySyncDetail;

import com.chargebee.v4.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartySyncDetailUpdate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartySyncDetailUpdateResponse {

  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private final Response httpResponse;

  private ThirdPartySyncDetailUpdateResponse(Builder builder) {

    this.thirdPartySyncDetail = builder.thirdPartySyncDetail;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ThirdPartySyncDetailUpdateResponse object. */
  public static ThirdPartySyncDetailUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartySyncDetailUpdateResponse object with HTTP response. */
  public static ThirdPartySyncDetailUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __thirdPartySyncDetailJson = JsonUtil.getObject(json, "third_party_sync_detail");
      if (__thirdPartySyncDetailJson != null) {
        builder.thirdPartySyncDetail(ThirdPartySyncDetail.fromJson(__thirdPartySyncDetailJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ThirdPartySyncDetailUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartySyncDetailUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartySyncDetailUpdateResponse. */
  public static class Builder {

    private ThirdPartySyncDetail thirdPartySyncDetail;

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartySyncDetail(ThirdPartySyncDetail thirdPartySyncDetail) {
      this.thirdPartySyncDetail = thirdPartySyncDetail;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ThirdPartySyncDetailUpdateResponse build() {
      return new ThirdPartySyncDetailUpdateResponse(this);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
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
