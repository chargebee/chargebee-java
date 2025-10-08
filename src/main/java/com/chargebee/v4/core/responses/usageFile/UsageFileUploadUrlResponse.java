package com.chargebee.v4.core.responses.usageFile;

import com.chargebee.v4.core.models.usageFile.UsageFile;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageFileUploadUrl operation. Contains the response data from the
 * API.
 */
public final class UsageFileUploadUrlResponse {

  private final UsageFile usageFile;

  private final Response httpResponse;

  private UsageFileUploadUrlResponse(Builder builder) {

    this.usageFile = builder.usageFile;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into UsageFileUploadUrlResponse object. */
  public static UsageFileUploadUrlResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageFileUploadUrlResponse object with HTTP response. */
  public static UsageFileUploadUrlResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageFileJson = JsonUtil.getObject(json, "usage_file");
      if (__usageFileJson != null) {
        builder.usageFile(UsageFile.fromJson(__usageFileJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageFileUploadUrlResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageFileUploadUrlResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageFileUploadUrlResponse. */
  public static class Builder {

    private UsageFile usageFile;

    private Response httpResponse;

    private Builder() {}

    public Builder usageFile(UsageFile usageFile) {
      this.usageFile = usageFile;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UsageFileUploadUrlResponse build() {
      return new UsageFileUploadUrlResponse(this);
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
