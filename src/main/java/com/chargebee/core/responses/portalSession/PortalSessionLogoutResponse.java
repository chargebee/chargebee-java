package com.chargebee.core.responses.portalSession;

import com.chargebee.core.models.portalSession.PortalSession;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PortalSessionLogout operation. Contains the response data from the
 * API.
 */
public final class PortalSessionLogoutResponse {

  private final PortalSession portalSession;

  private PortalSessionLogoutResponse(Builder builder) {

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into PortalSessionLogoutResponse object. */
  public static PortalSessionLogoutResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

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

    private Builder() {}

    public Builder portalSession(PortalSession portalSession) {
      this.portalSession = portalSession;
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
}
