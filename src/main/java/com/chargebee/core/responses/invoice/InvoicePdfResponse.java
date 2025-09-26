package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.download.Download;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for InvoicePdf operation. Contains the response data from the API. */
public final class InvoicePdfResponse {

  private final Download download;

  private InvoicePdfResponse(Builder builder) {

    this.download = builder.download;
  }

  /** Parse JSON response into InvoicePdfResponse object. */
  public static InvoicePdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

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

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
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
}
