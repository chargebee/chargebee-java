package com.chargebee.v4.core.responses.usage;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class UsageRetrieveResponse {

  private final Usage usage;

  private final Response httpResponse;

  private UsageRetrieveResponse(Usage usage, Response httpResponse) {

    this.usage = usage;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into UsageRetrieveResponse object. */
  public static UsageRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageRetrieveResponse object with HTTP response. */
  public static UsageRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Usage usage = Usage.fromJson(JsonUtil.getObject(json, "usage"));

      return new UsageRetrieveResponse(usage, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageRetrieveResponse from JSON", e);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
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
