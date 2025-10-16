package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for InvoiceDownloadEinvoice operation. Contains the response data from
 * a single resource get operation.
 */
public final class InvoiceDownloadEinvoiceResponse extends BaseResponse {
  private final List<Download> downloads;

  private InvoiceDownloadEinvoiceResponse(List<Download> downloads, Response httpResponse) {
    super(httpResponse);

    this.downloads = downloads;
  }

  /** Parse JSON response into InvoiceDownloadEinvoiceResponse object. */
  public static InvoiceDownloadEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceDownloadEinvoiceResponse object with HTTP response. */
  public static InvoiceDownloadEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new InvoiceDownloadEinvoiceResponse(downloads, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceDownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
