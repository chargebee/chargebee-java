package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportItemFamilies operation. Contains the response data from the
 * API.
 */
public final class ExportItemFamiliesResponse {

  private final Export export;

  private ExportItemFamiliesResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportItemFamiliesResponse object. */
  public static ExportItemFamiliesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportItemFamiliesResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportItemFamiliesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportItemFamiliesResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportItemFamiliesResponse build() {
      return new ExportItemFamiliesResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
