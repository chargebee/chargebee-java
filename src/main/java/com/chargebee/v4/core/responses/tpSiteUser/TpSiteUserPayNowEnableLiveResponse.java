package com.chargebee.v4.core.responses.tpSiteUser;

import com.chargebee.v4.core.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TpSiteUserPayNowEnableLive operation. Contains the response data
 * from the API.
 */
public final class TpSiteUserPayNowEnableLiveResponse {

  private final TpSiteUser tpSiteUser;

  private final Response httpResponse;

  private TpSiteUserPayNowEnableLiveResponse(Builder builder) {

    this.tpSiteUser = builder.tpSiteUser;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into TpSiteUserPayNowEnableLiveResponse object. */
  public static TpSiteUserPayNowEnableLiveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TpSiteUserPayNowEnableLiveResponse object with HTTP response. */
  public static TpSiteUserPayNowEnableLiveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __tpSiteUserJson = JsonUtil.getObject(json, "tp_site_user");
      if (__tpSiteUserJson != null) {
        builder.tpSiteUser(TpSiteUser.fromJson(__tpSiteUserJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TpSiteUserPayNowEnableLiveResponse from JSON", e);
    }
  }

  /** Create a new builder for TpSiteUserPayNowEnableLiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TpSiteUserPayNowEnableLiveResponse. */
  public static class Builder {

    private TpSiteUser tpSiteUser;

    private Response httpResponse;

    private Builder() {}

    public Builder tpSiteUser(TpSiteUser tpSiteUser) {
      this.tpSiteUser = tpSiteUser;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public TpSiteUserPayNowEnableLiveResponse build() {
      return new TpSiteUserPayNowEnableLiveResponse(this);
    }
  }

  /** Get the tpSiteUser from the response. */
  public TpSiteUser getTpSiteUser() {
    return tpSiteUser;
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
