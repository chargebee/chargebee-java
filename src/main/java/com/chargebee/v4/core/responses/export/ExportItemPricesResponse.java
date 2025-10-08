package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportItemPrices operation. Contains the response data from the
 * API.
 */
public final class ExportItemPricesResponse {

  private final Export export;

  private final Response httpResponse;

  private ExportItemPricesResponse(Builder builder) {

    this.export = builder.export;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ExportItemPricesResponse object. */
  public static ExportItemPricesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportItemPricesResponse object with HTTP response. */
  public static ExportItemPricesResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportItemPricesResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportItemPricesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportItemPricesResponse. */
  public static class Builder {

    private Export export;

    private Response httpResponse;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ExportItemPricesResponse build() {
      return new ExportItemPricesResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
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
