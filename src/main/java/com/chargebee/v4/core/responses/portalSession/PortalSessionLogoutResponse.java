package com.chargebee.v4.core.responses.portalSession;

import com.chargebee.v4.core.models.portalSession.PortalSession;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PortalSessionLogout operation. Contains the response data from the
 * API.
 */
public final class PortalSessionLogoutResponse {

  private final PortalSession portalSession;

  private final Response httpResponse;

  private PortalSessionLogoutResponse(Builder builder) {

    this.portalSession = builder.portalSession;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PortalSessionLogoutResponse object. */
  public static PortalSessionLogoutResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PortalSessionLogoutResponse object with HTTP response. */
  public static PortalSessionLogoutResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionLogoutResponse from JSON", e);
    }
  }

  /** Create a new builder for PortalSessionLogoutResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PortalSessionLogoutResponse. */
  public static class Builder {

    private PortalSession portalSession;

    private Response httpResponse;

    private Builder() {}

    public Builder portalSession(PortalSession portalSession) {
      this.portalSession = portalSession;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PortalSessionLogoutResponse build() {
      return new PortalSessionLogoutResponse(this);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
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
