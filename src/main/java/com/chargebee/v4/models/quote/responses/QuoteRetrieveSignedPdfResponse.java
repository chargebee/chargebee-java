package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteRetrieveSignedPdf operation. Contains the response data from
 * the API.
 */
public final class QuoteRetrieveSignedPdfResponse extends BaseResponse {
  private final Download download;

  private QuoteRetrieveSignedPdfResponse(Builder builder) {
    super(builder.httpResponse);

    this.download = builder.download;
  }

  /** Parse JSON response into QuoteRetrieveSignedPdfResponse object. */
  public static QuoteRetrieveSignedPdfResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteRetrieveSignedPdfResponse object with HTTP response. */
  public static QuoteRetrieveSignedPdfResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __downloadObj = JsonUtil.getJsonObject(jsonObj, "download");
      if (__downloadObj != null) {
        builder.download(Download.fromJson(__downloadObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteRetrieveSignedPdfResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteRetrieveSignedPdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteRetrieveSignedPdfResponse. */
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

    public QuoteRetrieveSignedPdfResponse build() {
      return new QuoteRetrieveSignedPdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }

  @Override
  public String toString() {
    return "QuoteRetrieveSignedPdfResponse{" + "download=" + download + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuoteRetrieveSignedPdfResponse that = (QuoteRetrieveSignedPdfResponse) o;
    return java.util.Objects.equals(download, that.download);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(download);
  }
}
