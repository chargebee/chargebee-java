package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ExportDeferredRevenue operation. Contains the response data from
 * the API.
 */
public final class ExportDeferredRevenueResponse {

  private final Export export;

  private ExportDeferredRevenueResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportDeferredRevenueResponse object. */
  public static ExportDeferredRevenueResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

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

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
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
