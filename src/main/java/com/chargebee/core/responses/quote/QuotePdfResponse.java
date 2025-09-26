package com.chargebee.core.responses.quote;

import com.chargebee.core.models.download.Download;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for QuotePdf operation. Contains the response data from the API. */
public final class QuotePdfResponse {

  private final Download download;

  private QuotePdfResponse(Builder builder) {

    this.download = builder.download;
  }

  /** Parse JSON response into QuotePdfResponse object. */
  public static QuotePdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuotePdfResponse from JSON", e);
    }
  }

  /** Create a new builder for QuotePdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuotePdfResponse. */
  public static class Builder {

    private Download download;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public QuotePdfResponse build() {
      return new QuotePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
