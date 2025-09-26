package com.chargebee.core.responses.comment;

import com.chargebee.core.models.comment.Comment;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CommentRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CommentRetrieveResponse {

  private final Comment comment;

  private CommentRetrieveResponse(Comment comment) {

    this.comment = comment;
  }

  /** Parse JSON response into CommentRetrieveResponse object. */
  public static CommentRetrieveResponse fromJson(String json) {
    try {

      Comment comment = Comment.fromJson(JsonUtil.getObject(json, "comment"));

      return new CommentRetrieveResponse(comment);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentRetrieveResponse from JSON", e);
    }
  }

  /** Get the comment from the response. */
  public Comment getComment() {
    return comment;
  }
}
