package com.chargebee.v4.models.export.responses;

import com.chargebee.v4.models.export.Export;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ExportRetrieveResponse extends BaseResponse {
  private final Export export;

  private ExportRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.export = builder.export;
  }

  /** Parse JSON response into ExportRetrieveResponse object. */
  public static ExportRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportRetrieveResponse object with HTTP response. */
  public static ExportRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportRetrieveResponse. */
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

    public ExportRetrieveResponse build() {
      return new ExportRetrieveResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
