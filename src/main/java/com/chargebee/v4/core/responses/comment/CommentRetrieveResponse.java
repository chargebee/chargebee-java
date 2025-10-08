package com.chargebee.v4.core.responses.comment;

import com.chargebee.v4.core.models.comment.Comment;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CommentRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CommentRetrieveResponse {

  private final Comment comment;

  private final Response httpResponse;

  private CommentRetrieveResponse(Comment comment, Response httpResponse) {

    this.comment = comment;

    this.httpResponse = httpResponse;
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
