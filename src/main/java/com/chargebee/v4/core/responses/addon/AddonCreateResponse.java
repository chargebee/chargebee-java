package com.chargebee.v4.core.responses.addon;

import com.chargebee.v4.core.models.addon.Addon;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for AddonCreate operation. Contains the response data from the API. */
public final class AddonCreateResponse {

  private final Addon addon;

  private final Response httpResponse;

  private AddonCreateResponse(Builder builder) {

    this.addon = builder.addon;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into AddonCreateResponse object. */
  public static AddonCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddonCreateResponse object with HTTP response. */
  public static AddonCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for AddonCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddonCreateResponse. */
  public static class Builder {

    private Addon addon;

    private Response httpResponse;

    private Builder() {}

    public Builder addon(Addon addon) {
      this.addon = addon;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AddonCreateResponse build() {
      return new AddonCreateResponse(this);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
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
