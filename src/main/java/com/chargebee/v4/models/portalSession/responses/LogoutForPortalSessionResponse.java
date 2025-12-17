package com.chargebee.v4.models.portalSession.responses;

import com.chargebee.v4.models.portalSession.PortalSession;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for LogoutForPortalSession operation. Contains the response data from
 * the API.
 */
public final class LogoutForPortalSessionResponse extends BaseResponse {
  private final PortalSession portalSession;

  private LogoutForPortalSessionResponse(Builder builder) {
    super(builder.httpResponse);

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into LogoutForPortalSessionResponse object. */
  public static LogoutForPortalSessionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into LogoutForPortalSessionResponse object with HTTP response. */
  public static LogoutForPortalSessionResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse LogoutForPortalSessionResponse from JSON", e);
    }
  }

  /** Create a new builder for LogoutForPortalSessionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for LogoutForPortalSessionResponse. */
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

    public LogoutForPortalSessionResponse build() {
      return new LogoutForPortalSessionResponse(this);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }
}
