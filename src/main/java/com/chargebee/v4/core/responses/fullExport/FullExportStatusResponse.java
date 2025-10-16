package com.chargebee.v4.core.responses.fullExport;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FullExportStatus operation. Contains the response data from a
 * single resource get operation.
 */
public final class FullExportStatusResponse extends BaseResponse {
  private final Object fullExport;

  private FullExportStatusResponse(Object fullExport, Response httpResponse) {
    super(httpResponse);

    this.fullExport = fullExport;
  }

  /** Parse JSON response into FullExportStatusResponse object. */
  public static FullExportStatusResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FullExportStatusResponse object with HTTP response. */
  public static FullExportStatusResponse fromJson(String json, Response httpResponse) {
    try {

      Object fullExport = JsonUtil.getObject(json, "full_export");

      return new FullExportStatusResponse(fullExport, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FullExportStatusResponse from JSON", e);
    }
  }

  /** Get the fullExport from the response. */
  public Object getFullExport() {
    return fullExport;
  }
}
