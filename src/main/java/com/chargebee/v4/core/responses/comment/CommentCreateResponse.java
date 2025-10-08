package com.chargebee.v4.core.responses.comment;

import com.chargebee.v4.core.models.comment.Comment;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CommentCreate operation. Contains the response data from the API.
 */
public final class CommentCreateResponse {

  private final Comment comment;

  private final Response httpResponse;

  private CommentCreateResponse(Builder builder) {

    this.comment = builder.comment;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CommentCreateResponse object. */
  public static CommentCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CommentCreateResponse object with HTTP response. */
  public static CommentCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __commentJson = JsonUtil.getObject(json, "comment");
      if (__commentJson != null) {
        builder.comment(Comment.fromJson(__commentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CommentCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CommentCreateResponse. */
  public static class Builder {

    private Comment comment;

    private Response httpResponse;

    private Builder() {}

    public Builder comment(Comment comment) {
      this.comment = comment;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CommentCreateResponse build() {
      return new CommentCreateResponse(this);
    }
  }

  /** Get the comment from the response. */
  public Comment getComment() {
    return comment;
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
