package com.chargebee.v4.models.comment.responses;

import com.chargebee.v4.models.comment.Comment;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteComment operation. Contains the response data from the API.
 */
public final class DeleteCommentResponse extends BaseResponse {
  private final Comment comment;

  private DeleteCommentResponse(Builder builder) {
    super(builder.httpResponse);

    this.comment = builder.comment;
  }

  /** Parse JSON response into DeleteCommentResponse object. */
  public static DeleteCommentResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteCommentResponse object with HTTP response. */
  public static DeleteCommentResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __commentJson = JsonUtil.getObject(json, "comment");
      if (__commentJson != null) {
        builder.comment(Comment.fromJson(__commentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteCommentResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteCommentResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteCommentResponse. */
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

    public DeleteCommentResponse build() {
      return new DeleteCommentResponse(this);
    }
  }

  /** Get the comment from the response. */
  public Comment getComment() {
    return comment;
  }
}
