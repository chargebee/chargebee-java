package com.chargebee.core.responses.media;

import com.chargebee.core.models.media.Media;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for MediaCreateMediaAndAttachToItem operation. Contains the response
 * data from the API.
 */
public final class MediaCreateMediaAndAttachToItemResponse {

  private final Media media;

  private MediaCreateMediaAndAttachToItemResponse(Builder builder) {

    this.media = builder.media;
  }

  /** Parse JSON response into MediaCreateMediaAndAttachToItemResponse object. */
  public static MediaCreateMediaAndAttachToItemResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __mediaJson = JsonUtil.getObject(json, "media");
      if (__mediaJson != null) {
        builder.media(Media.fromJson(__mediaJson));
      }

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

    private Builder() {}

    public Builder media(Media media) {
      this.media = media;
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
