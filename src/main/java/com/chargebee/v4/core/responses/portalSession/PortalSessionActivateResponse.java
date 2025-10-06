package com.chargebee.v4.core.responses.portalSession;

import com.chargebee.v4.core.models.portalSession.PortalSession;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PortalSessionActivate operation. Contains the response data from
 * the API.
 */
public final class PortalSessionActivateResponse {

  private final PortalSession portalSession;

  private PortalSessionActivateResponse(Builder builder) {

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into PortalSessionActivateResponse object. */
  public static PortalSessionActivateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

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

    private Builder() {}

    public Builder portalSession(PortalSession portalSession) {
      this.portalSession = portalSession;
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
