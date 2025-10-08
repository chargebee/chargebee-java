package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for InvoicePdf operation. Contains the response data from the API. */
public final class InvoicePdfResponse {

  private final Download download;

  private final Response httpResponse;

  private InvoicePdfResponse(Builder builder) {

    this.download = builder.download;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into InvoicePdfResponse object. */
  public static InvoicePdfResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoicePdfResponse object with HTTP response. */
  public static InvoicePdfResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePdfResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoicePdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoicePdfResponse. */
  public static class Builder {

    private Download download;

    private Response httpResponse;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoicePdfResponse build() {
      return new InvoicePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
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
