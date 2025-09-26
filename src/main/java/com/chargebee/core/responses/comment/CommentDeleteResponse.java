package com.chargebee.core.responses.comment;

import com.chargebee.core.models.comment.Comment;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CommentDelete operation. Contains the response data from the API.
 */
public final class CommentDeleteResponse {

  private final Comment comment;

  private CommentDeleteResponse(Builder builder) {

    this.comment = builder.comment;
  }

  /** Parse JSON response into CommentDeleteResponse object. */
  public static CommentDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __commentJson = JsonUtil.getObject(json, "comment");
      if (__commentJson != null) {
        builder.comment(Comment.fromJson(__commentJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CommentDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CommentDeleteResponse. */
  public static class Builder {

    private Comment comment;

    private Builder() {}

    public Builder comment(Comment comment) {
      this.comment = comment;
      return this;
    }

    public CommentDeleteResponse build() {
      return new CommentDeleteResponse(this);
    }
  }

  /** Get the comment from the response. */
  public Comment getComment() {
    return comment;
  }
}
