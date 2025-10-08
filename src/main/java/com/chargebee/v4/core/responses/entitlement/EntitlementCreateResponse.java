package com.chargebee.v4.core.responses.entitlement;

import com.chargebee.v4.core.models.entitlement.Entitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EntitlementCreate operation. Contains the response data from the
 * API.
 */
public final class EntitlementCreateResponse {

  private final Entitlement entitlement;

  private final Response httpResponse;

  private EntitlementCreateResponse(Builder builder) {

    this.entitlement = builder.entitlement;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into EntitlementCreateResponse object. */
  public static EntitlementCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EntitlementCreateResponse object with HTTP response. */
  public static EntitlementCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __entitlementJson = JsonUtil.getObject(json, "entitlement");
      if (__entitlementJson != null) {
        builder.entitlement(Entitlement.fromJson(__entitlementJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for EntitlementCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EntitlementCreateResponse. */
  public static class Builder {

    private Entitlement entitlement;

    private Response httpResponse;

    private Builder() {}

    public Builder entitlement(Entitlement entitlement) {
      this.entitlement = entitlement;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EntitlementCreateResponse build() {
      return new EntitlementCreateResponse(this);
    }
  }

  /** Get the entitlement from the response. */
  public Entitlement getEntitlement() {
    return entitlement;
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
