package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for DownloadEinvoiceForInvoice operation. Contains the response data
 * from a single resource get operation.
 */
public final class DownloadEinvoiceForInvoiceResponse extends BaseResponse {
  private final List<Download> downloads;

  private DownloadEinvoiceForInvoiceResponse(List<Download> downloads, Response httpResponse) {
    super(httpResponse);

    this.downloads = downloads;
  }

  /** Parse JSON response into DownloadEinvoiceForInvoiceResponse object. */
  public static DownloadEinvoiceForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DownloadEinvoiceForInvoiceResponse object with HTTP response. */
  public static DownloadEinvoiceForInvoiceResponse fromJson(String json, Response httpResponse) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new DownloadEinvoiceForInvoiceResponse(downloads, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DownloadEinvoiceForInvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
