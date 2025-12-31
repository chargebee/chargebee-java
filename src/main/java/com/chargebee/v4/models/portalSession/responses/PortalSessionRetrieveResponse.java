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

  private PortalSessionRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into PortalSessionRetrieveResponse object. */
  public static PortalSessionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PortalSessionRetrieveResponse object with HTTP response. */
  public static PortalSessionRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PortalSessionRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PortalSessionRetrieveResponse. */
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

    public PortalSessionRetrieveResponse build() {
      return new PortalSessionRetrieveResponse(this);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }

  @Override
  public String toString() {
    return "PortalSessionRetrieveResponse{" + "portalSession=" + portalSession + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PortalSessionRetrieveResponse that = (PortalSessionRetrieveResponse) o;
    return java.util.Objects.equals(portalSession, that.portalSession);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(portalSession);
  }
}
