package com.chargebee.core.responses.portalSession;

import com.chargebee.core.models.portalSession.PortalSession;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PortalSessionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PortalSessionRetrieveResponse {

  private final PortalSession portalSession;

  private PortalSessionRetrieveResponse(PortalSession portalSession) {

    this.portalSession = portalSession;
  }

  /** Parse JSON response into PortalSessionRetrieveResponse object. */
  public static PortalSessionRetrieveResponse fromJson(String json) {
    try {

      PortalSession portalSession =
          PortalSession.fromJson(JsonUtil.getObject(json, "portal_session"));

      return new PortalSessionRetrieveResponse(portalSession);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionRetrieveResponse from JSON", e);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }
}
