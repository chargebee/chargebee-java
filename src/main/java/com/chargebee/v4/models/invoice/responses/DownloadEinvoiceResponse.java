package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for DownloadEinvoice operation. Contains the response data from a
 * single resource get operation.
 */
public final class DownloadEinvoiceResponse extends BaseResponse {
  private final List<Download> downloads;

  private DownloadEinvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.downloads = builder.downloads;
  }

  /** Parse JSON response into DownloadEinvoiceResponse object. */
  public static DownloadEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DownloadEinvoiceResponse object with HTTP response. */
  public static DownloadEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.downloads(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "downloads"), Download::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for DownloadEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DownloadEinvoiceResponse. */
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

    public DownloadEinvoiceResponse build() {
      return new DownloadEinvoiceResponse(this);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
  }

  @Override
  public String toString() {
    return "DownloadEinvoiceResponse{" + "downloads=" + downloads + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DownloadEinvoiceResponse that = (DownloadEinvoiceResponse) o;
    return java.util.Objects.equals(downloads, that.downloads);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(downloads);
  }
}
