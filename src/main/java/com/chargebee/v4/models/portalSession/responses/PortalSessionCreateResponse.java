package com.chargebee.v4.models.portalSession.responses;

import com.chargebee.v4.models.portalSession.PortalSession;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PortalSessionCreate operation. Contains the response data from the
 * API.
 */
public final class PortalSessionCreateResponse extends BaseResponse {
  private final PortalSession portalSession;

  private PortalSessionCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.portalSession = builder.portalSession;
  }

  /** Parse JSON response into PortalSessionCreateResponse object. */
  public static PortalSessionCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PortalSessionCreateResponse object with HTTP response. */
  public static PortalSessionCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __portalSessionJson = JsonUtil.getObject(json, "portal_session");
      if (__portalSessionJson != null) {
        builder.portalSession(PortalSession.fromJson(__portalSessionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PortalSessionCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PortalSessionCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PortalSessionCreateResponse. */
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

    public PortalSessionCreateResponse build() {
      return new PortalSessionCreateResponse(this);
    }
  }

  /** Get the portalSession from the response. */
  public PortalSession getPortalSession() {
    return portalSession;
  }

  @Override
  public String toString() {
    return "PortalSessionCreateResponse{" + "portalSession=" + portalSession + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PortalSessionCreateResponse that = (PortalSessionCreateResponse) o;
    return java.util.Objects.equals(portalSession, that.portalSession);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(portalSession);
  }
}
