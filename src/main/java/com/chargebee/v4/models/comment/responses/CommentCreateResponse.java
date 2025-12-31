package com.chargebee.v4.models.comment.responses;

import com.chargebee.v4.models.comment.Comment;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CommentCreate operation. Contains the response data from the API.
 */
public final class CommentCreateResponse extends BaseResponse {
  private final Comment comment;

  private CommentCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.comment = builder.comment;
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

  @Override
  public String toString() {
    return "CommentCreateResponse{" + "comment=" + comment + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CommentCreateResponse that = (CommentCreateResponse) o;
    return java.util.Objects.equals(comment, that.comment);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(comment);
  }
}
