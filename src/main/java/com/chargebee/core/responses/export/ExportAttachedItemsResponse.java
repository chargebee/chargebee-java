package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportAttachedItems operation. Contains the response data from the
 * API.
 */
public final class ExportAttachedItemsResponse {

  private final Export export;

  private ExportAttachedItemsResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportAttachedItemsResponse object. */
  public static ExportAttachedItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportAttachedItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportAttachedItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportAttachedItemsResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportAttachedItemsResponse build() {
      return new ExportAttachedItemsResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
