package com.chargebee.v4.core.responses.usageFile;

import com.chargebee.v4.core.models.usageFile.UsageFile;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageFileProcessingStatus operation. Contains the response data
 * from a single resource get operation.
 */
public final class UsageFileProcessingStatusResponse {

  private final UsageFile usageFile;

  private final Response httpResponse;

  private UsageFileProcessingStatusResponse(UsageFile usageFile, Response httpResponse) {

    this.usageFile = usageFile;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into UsageFileProcessingStatusResponse object. */
  public static UsageFileProcessingStatusResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageFileProcessingStatusResponse object with HTTP response. */
  public static UsageFileProcessingStatusResponse fromJson(String json, Response httpResponse) {
    try {

      UsageFile usageFile = UsageFile.fromJson(JsonUtil.getObject(json, "usage_file"));

      return new UsageFileProcessingStatusResponse(usageFile, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageFileProcessingStatusResponse from JSON", e);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
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
