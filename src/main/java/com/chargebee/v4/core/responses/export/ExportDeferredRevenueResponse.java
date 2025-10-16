package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportDeferredRevenue operation. Contains the response data from
 * the API.
 */
public final class ExportDeferredRevenueResponse extends BaseResponse {
  private final Export export;

  private ExportDeferredRevenueResponse(Builder builder) {
    super(builder.httpResponse);

    this.export = builder.export;
  }

  /** Parse JSON response into ExportDeferredRevenueResponse object. */
  public static ExportDeferredRevenueResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportDeferredRevenueResponse object with HTTP response. */
  public static ExportDeferredRevenueResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportDeferredRevenueResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportDeferredRevenueResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportDeferredRevenueResponse. */
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

    public ExportDeferredRevenueResponse build() {
      return new ExportDeferredRevenueResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
