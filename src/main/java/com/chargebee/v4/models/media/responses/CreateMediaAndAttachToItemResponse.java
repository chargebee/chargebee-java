package com.chargebee.v4.models.media.responses;

import com.chargebee.v4.models.media.Media;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateMediaAndAttachToItem operation. Contains the response data
 * from the API.
 */
public final class CreateMediaAndAttachToItemResponse extends BaseResponse {
  private final Media media;

  private CreateMediaAndAttachToItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.media = builder.media;
  }

  /** Parse JSON response into CreateMediaAndAttachToItemResponse object. */
  public static CreateMediaAndAttachToItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreateMediaAndAttachToItemResponse object with HTTP response. */
  public static CreateMediaAndAttachToItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __mediaJson = JsonUtil.getObject(json, "media");
      if (__mediaJson != null) {
        builder.media(Media.fromJson(__mediaJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreateMediaAndAttachToItemResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateMediaAndAttachToItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateMediaAndAttachToItemResponse. */
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

    public CreateMediaAndAttachToItemResponse build() {
      return new CreateMediaAndAttachToItemResponse(this);
    }
  }

  /** Get the media from the response. */
  public Media getMedia() {
    return media;
  }

  @Override
  public String toString() {
    return "CreateMediaAndAttachToItemResponse{" + "media=" + media + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreateMediaAndAttachToItemResponse that = (CreateMediaAndAttachToItemResponse) o;
    return java.util.Objects.equals(media, that.media);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(media);
  }
}
