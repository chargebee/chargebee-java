package com.chargebee.v4.core.responses.media;

import com.chargebee.v4.core.models.media.Media;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MediaCreateMediaAndAttachToItem operation. Contains the response
 * data from the API.
 */
public final class MediaCreateMediaAndAttachToItemResponse extends BaseResponse {
  private final Media media;

  private MediaCreateMediaAndAttachToItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.media = builder.media;
  }

  /** Parse JSON response into MediaCreateMediaAndAttachToItemResponse object. */
  public static MediaCreateMediaAndAttachToItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into MediaCreateMediaAndAttachToItemResponse object with HTTP response. */
  public static MediaCreateMediaAndAttachToItemResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __mediaJson = JsonUtil.getObject(json, "media");
      if (__mediaJson != null) {
        builder.media(Media.fromJson(__mediaJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse MediaCreateMediaAndAttachToItemResponse from JSON", e);
    }
  }

  /** Create a new builder for MediaCreateMediaAndAttachToItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for MediaCreateMediaAndAttachToItemResponse. */
  public static class Builder {

    private Media media;

    private Response httpResponse;

    private Builder() {}

    public Builder media(Media media) {
      this.media = media;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public MediaCreateMediaAndAttachToItemResponse build() {
      return new MediaCreateMediaAndAttachToItemResponse(this);
    }
  }

  /** Get the media from the response. */
  public Media getMedia() {
    return media;
  }
}
