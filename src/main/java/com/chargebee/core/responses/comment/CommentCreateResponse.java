package com.chargebee.core.responses.comment;

import com.chargebee.core.models.comment.Comment;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CommentCreate operation. Contains the response data from the API.
 */
public final class CommentCreateResponse {

  private final Comment comment;

  private CommentCreateResponse(Builder builder) {

    this.comment = builder.comment;
  }

  /** Parse JSON response into CommentCreateResponse object. */
  public static CommentCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __commentJson = JsonUtil.getObject(json, "comment");
      if (__commentJson != null) {
        builder.comment(Comment.fromJson(__commentJson));
      }

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

    private Builder() {}

    public Builder comment(Comment comment) {
      this.comment = comment;
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
}
