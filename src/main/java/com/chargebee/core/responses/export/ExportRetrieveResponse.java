package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ExportRetrieveResponse {

  private final Export export;

  private ExportRetrieveResponse(Export export) {

    this.export = export;
  }

  /** Parse JSON response into ExportRetrieveResponse object. */
  public static ExportRetrieveResponse fromJson(String json) {
    try {

      Export export = Export.fromJson(JsonUtil.getObject(json, "export"));

      return new ExportRetrieveResponse(export);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportRetrieveResponse from JSON", e);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
