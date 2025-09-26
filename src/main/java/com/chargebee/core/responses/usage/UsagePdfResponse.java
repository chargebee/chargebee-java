package com.chargebee.core.responses.usage;

import com.chargebee.core.models.download.Download;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for UsagePdf operation. Contains the response data from the API. */
public final class UsagePdfResponse {

  private final Download download;

  private UsagePdfResponse(Builder builder) {

    this.download = builder.download;
  }

  /** Parse JSON response into UsagePdfResponse object. */
  public static UsagePdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsagePdfResponse from JSON", e);
    }
  }

  /** Create a new builder for UsagePdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsagePdfResponse. */
  public static class Builder {

    private Download download;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public UsagePdfResponse build() {
      return new UsagePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
