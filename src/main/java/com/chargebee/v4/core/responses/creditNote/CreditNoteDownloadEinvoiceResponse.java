package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for CreditNoteDownloadEinvoice operation. Contains the response data
 * from a single resource get operation.
 */
public final class CreditNoteDownloadEinvoiceResponse {

  private final List<Download> downloads;

  private CreditNoteDownloadEinvoiceResponse(List<Download> downloads) {

    this.downloads = downloads;
  }

  /** Parse JSON response into CreditNoteDownloadEinvoiceResponse object. */
  public static CreditNoteDownloadEinvoiceResponse fromJson(String json) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new CreditNoteDownloadEinvoiceResponse(downloads);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteDownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
