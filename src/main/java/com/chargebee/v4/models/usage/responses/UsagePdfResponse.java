package com.chargebee.v4.models.usage.responses;

import com.chargebee.v4.models.download.Download;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for UsagePdf operation. Contains the response data from the API. */
public final class UsagePdfResponse extends BaseResponse {
  private final Download download;

  private UsagePdfResponse(Builder builder) {
    super(builder.httpResponse);

    this.download = builder.download;
  }

  /** Parse JSON response into UsagePdfResponse object. */
  public static UsagePdfResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsagePdfResponse object with HTTP response. */
  public static UsagePdfResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      builder.httpResponse(httpResponse);
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

    public UsagePdfResponse build() {
      return new UsagePdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
