package com.chargebee.v4.models.portalSession.responses;

import com.chargebee.v4.models.portalSession.PortalSession;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PortalSessionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PortalSessionRetrieveResponse extends BaseResponse {
  private final PortalSession portalSession;

  private PortalSessionRetrieveResponse(PortalSession portalSession, Response httpResponse) {
    super(httpResponse);

    this.portalSession = portalSession;
  }

  /** Parse JSON response into PortalSessionRetrieveResponse object. */
  public static PortalSessionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PortalSessionRetrieveResponse object with HTTP response. */
  public static PortalSessionRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PortalSession portalSession =
          PortalSession.fromJson(JsonUtil.getObject(json, "portal_session"));

      return new PortalSessionRetrieveResponse(portalSession, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionRetrieveResponse from JSON", e);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }
}
