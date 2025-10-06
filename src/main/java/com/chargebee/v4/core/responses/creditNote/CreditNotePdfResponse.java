package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CreditNotePdf operation. Contains the response data from the API.
 */
public final class CreditNotePdfResponse {

  private final Download download;

  private CreditNotePdfResponse(Builder builder) {

    this.download = builder.download;
  }

  /** Parse JSON response into CreditNotePdfResponse object. */
  public static CreditNotePdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNotePdfResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNotePdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNotePdfResponse. */
  public static class Builder {

    private Download download;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public CreditNotePdfResponse build() {
      return new CreditNotePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
