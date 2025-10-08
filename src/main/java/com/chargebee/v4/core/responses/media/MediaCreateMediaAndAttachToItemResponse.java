package com.chargebee.v4.core.responses.media;

import com.chargebee.v4.core.models.media.Media;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MediaCreateMediaAndAttachToItem operation. Contains the response
 * data from the API.
 */
public final class MediaCreateMediaAndAttachToItemResponse {

  private final Media media;

  private final Response httpResponse;

  private MediaCreateMediaAndAttachToItemResponse(Builder builder) {

    this.media = builder.media;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
