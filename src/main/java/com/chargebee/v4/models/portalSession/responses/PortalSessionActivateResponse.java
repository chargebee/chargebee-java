package com.chargebee.v4.models.portalSession.responses;

import com.chargebee.v4.models.portalSession.PortalSession;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PortalSessionActivate operation. Contains the response data from
 * the API.
 */
public final class PortalSessionActivateResponse extends BaseResponse {
  private final PortalSession portalSession;

  private PortalSessionActivateResponse(Builder builder) {
    super(builder.httpResponse);

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into PortalSessionActivateResponse object. */
  public static PortalSessionActivateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PortalSessionActivateResponse object with HTTP response. */
  public static PortalSessionActivateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionActivateResponse from JSON", e);
    }
  }

  /** Create a new builder for PortalSessionActivateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PortalSessionActivateResponse. */
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

    public PortalSessionActivateResponse build() {
      return new PortalSessionActivateResponse(this);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }
}
