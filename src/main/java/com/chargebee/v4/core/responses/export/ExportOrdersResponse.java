package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ExportOrders operation. Contains the response data from the API.
 */
public final class ExportOrdersResponse {

  private final Export export;

  private ExportOrdersResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportOrdersResponse object. */
  public static ExportOrdersResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportOrdersResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportOrdersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportOrdersResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportOrdersResponse build() {
      return new ExportOrdersResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
