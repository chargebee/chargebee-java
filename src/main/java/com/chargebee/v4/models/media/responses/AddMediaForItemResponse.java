package com.chargebee.v4.models.media.responses;

import com.chargebee.v4.models.media.Media;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddMediaForItem operation. Contains the response data from the API.
 */
public final class AddMediaForItemResponse extends BaseResponse {
  private final Media media;

  private AddMediaForItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.media = builder.media;
  }

  /** Parse JSON response into AddMediaForItemResponse object. */
  public static AddMediaForItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddMediaForItemResponse object with HTTP response. */
  public static AddMediaForItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __mediaJson = JsonUtil.getObject(json, "media");
      if (__mediaJson != null) {
        builder.media(Media.fromJson(__mediaJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddMediaForItemResponse from JSON", e);
    }
  }

  /** Create a new builder for AddMediaForItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddMediaForItemResponse. */
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

    public AddMediaForItemResponse build() {
      return new AddMediaForItemResponse(this);
    }
  }

  /** Get the media from the response. */
  public Media getMedia() {
    return media;
  }
}
