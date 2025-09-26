package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportCustomers operation. Contains the response data from the API.
 */
public final class ExportCustomersResponse {

  private final Export export;

  private ExportCustomersResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportCustomersResponse object. */
  public static ExportCustomersResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportCustomersResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportCustomersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportCustomersResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportCustomersResponse build() {
      return new ExportCustomersResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
