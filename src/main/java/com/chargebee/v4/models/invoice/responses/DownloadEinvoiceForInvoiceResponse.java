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

  private DownloadEinvoiceForInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.downloads = builder.downloads;
  }

  /** Parse JSON response into DownloadEinvoiceForInvoiceResponse object. */
  public static DownloadEinvoiceForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DownloadEinvoiceForInvoiceResponse object with HTTP response. */
  public static DownloadEinvoiceForInvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __downloadsJson = JsonUtil.getArray(json, "downloads");
      if (__downloadsJson != null) {
        builder.downloads(
            JsonUtil.parseObjectArray(__downloadsJson).stream()
                .map(Download::fromJson)
                .collect(java.util.stream.Collectors.toList()));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DownloadEinvoiceForInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for DownloadEinvoiceForInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DownloadEinvoiceForInvoiceResponse. */
  public static class Builder {

    private List<Download> downloads;

    private Response httpResponse;

    private Builder() {}

    public Builder downloads(List<Download> downloads) {
      this.downloads = downloads;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DownloadEinvoiceForInvoiceResponse build() {
      return new DownloadEinvoiceForInvoiceResponse(this);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }
}
