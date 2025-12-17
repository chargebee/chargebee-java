package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for PdfForQuote operation. Contains the response data from the API. */
public final class PdfForQuoteResponse extends BaseResponse {
  private final Download download;

  private PdfForQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.download = builder.download;
  }

  /** Parse JSON response into PdfForQuoteResponse object. */
  public static PdfForQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PdfForQuoteResponse object with HTTP response. */
  public static PdfForQuoteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PdfForQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for PdfForQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PdfForQuoteResponse. */
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

    public PdfForQuoteResponse build() {
      return new PdfForQuoteResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
