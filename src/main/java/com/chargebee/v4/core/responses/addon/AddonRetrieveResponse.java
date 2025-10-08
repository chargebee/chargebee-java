package com.chargebee.v4.core.responses.addon;

import com.chargebee.v4.core.models.addon.Addon;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddonRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddonRetrieveResponse {

  private final Addon addon;

  private final Response httpResponse;

  private AddonRetrieveResponse(Addon addon, Response httpResponse) {

    this.addon = addon;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into AddonRetrieveResponse object. */
  public static AddonRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddonRetrieveResponse object with HTTP response. */
  public static AddonRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Addon addon = Addon.fromJson(JsonUtil.getObject(json, "addon"));

      return new AddonRetrieveResponse(addon, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonRetrieveResponse from JSON", e);
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
