package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportTransactions operation. Contains the response data from the
 * API.
 */
public final class ExportTransactionsResponse {

  private final Export export;

  private ExportTransactionsResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportTransactionsResponse object. */
  public static ExportTransactionsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportTransactionsResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportTransactionsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportTransactionsResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportTransactionsResponse build() {
      return new ExportTransactionsResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
