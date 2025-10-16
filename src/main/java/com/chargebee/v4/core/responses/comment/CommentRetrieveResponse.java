package com.chargebee.v4.core.responses.comment;

import com.chargebee.v4.core.models.comment.Comment;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CommentRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CommentRetrieveResponse extends BaseResponse {
  private final Comment comment;

  private CommentRetrieveResponse(Comment comment, Response httpResponse) {
    super(httpResponse);

    this.comment = comment;
  }

  /** Parse JSON response into CommentRetrieveResponse object. */
  public static CommentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CommentRetrieveResponse object with HTTP response. */
  public static CommentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Comment comment = Comment.fromJson(JsonUtil.getObject(json, "comment"));

      return new CommentRetrieveResponse(comment, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentRetrieveResponse from JSON", e);
    }
  }

  /** Get the comment from the response. */
  public Comment getComment() {
    return comment;
  }
}
