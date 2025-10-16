package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for CreditNoteDownloadEinvoice operation. Contains the response data
 * from a single resource get operation.
 */
public final class CreditNoteDownloadEinvoiceResponse extends BaseResponse {
  private final List<Download> downloads;

  private CreditNoteDownloadEinvoiceResponse(List<Download> downloads, Response httpResponse) {
    super(httpResponse);

    this.downloads = downloads;
  }

  /** Parse JSON response into CreditNoteDownloadEinvoiceResponse object. */
  public static CreditNoteDownloadEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteDownloadEinvoiceResponse object with HTTP response. */
  public static CreditNoteDownloadEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new CreditNoteDownloadEinvoiceResponse(downloads, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteDownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
