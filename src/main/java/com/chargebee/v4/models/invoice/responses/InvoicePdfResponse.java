package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for InvoicePdf operation. Contains the response data from the API. */
public final class InvoicePdfResponse extends BaseResponse {
  private final Download download;

  private InvoicePdfResponse(Builder builder) {
    super(builder.httpResponse);

    this.download = builder.download;
  }

  /** Parse JSON response into InvoicePdfResponse object. */
  public static InvoicePdfResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoicePdfResponse object with HTTP response. */
  public static InvoicePdfResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePdfResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoicePdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoicePdfResponse. */
  public static class Builder {

    private Download download;

    private Response httpResponse;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoicePdfResponse build() {
      return new InvoicePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }

  @Override
  public String toString() {
    return "InvoicePdfResponse{" + "download=" + download + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvoicePdfResponse that = (InvoicePdfResponse) o;
    return java.util.Objects.equals(download, that.download);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(download);
  }
}
