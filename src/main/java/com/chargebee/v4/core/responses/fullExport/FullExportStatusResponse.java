package com.chargebee.v4.core.responses.fullExport;

/**
 * Immutable response object for FullExportStatus operation. Contains the response data from a
 * single resource get operation.
 */
public final class FullExportStatusResponse {

  private FullExportStatusResponse() {}

  /** Parse JSON response into FullExportStatusResponse object. */
  public static FullExportStatusResponse fromJson(String json) {
    try {

      return new FullExportStatusResponse();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FullExportStatusResponse from JSON", e);
    }
  }
}
