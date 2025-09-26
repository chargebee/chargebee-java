package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.download.Download;

import com.chargebee.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for InvoiceDownloadEinvoice operation. Contains the response data from
 * a single resource get operation.
 */
public final class InvoiceDownloadEinvoiceResponse {

  private final List<Download> downloads;

  private InvoiceDownloadEinvoiceResponse(List<Download> downloads) {

    this.downloads = downloads;
  }

  /** Parse JSON response into InvoiceDownloadEinvoiceResponse object. */
  public static InvoiceDownloadEinvoiceResponse fromJson(String json) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new InvoiceDownloadEinvoiceResponse(downloads);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceDownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
