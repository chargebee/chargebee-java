package com.chargebee.v4.core.responses.portalSession;

import com.chargebee.v4.core.models.portalSession.PortalSession;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PortalSessionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PortalSessionRetrieveResponse {

  private final PortalSession portalSession;

  private final Response httpResponse;

  private PortalSessionRetrieveResponse(PortalSession portalSession, Response httpResponse) {

    this.portalSession = portalSession;

    this.httpResponse = httpResponse;
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
