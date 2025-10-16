package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ExportRetrieveResponse extends BaseResponse {
  private final Export export;

  private ExportRetrieveResponse(Export export, Response httpResponse) {
    super(httpResponse);

    this.export = export;
  }

  /** Parse JSON response into ExportRetrieveResponse object. */
  public static ExportRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportRetrieveResponse object with HTTP response. */
  public static ExportRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Export export = Export.fromJson(JsonUtil.getObject(json, "export"));

      return new ExportRetrieveResponse(export, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportRetrieveResponse from JSON", e);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
