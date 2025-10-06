package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ExportCreditNotes operation. Contains the response data from the
 * API.
 */
public final class ExportCreditNotesResponse {

  private final Export export;

  private ExportCreditNotesResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportCreditNotesResponse object. */
  public static ExportCreditNotesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportCreditNotesResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportCreditNotesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportCreditNotesResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportCreditNotesResponse build() {
      return new ExportCreditNotesResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
