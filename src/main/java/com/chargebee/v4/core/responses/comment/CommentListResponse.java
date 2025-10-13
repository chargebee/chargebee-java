package com.chargebee.v4.core.responses.comment;

import java.util.List;

import com.chargebee.v4.core.models.comment.Comment;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.CommentService;
import com.chargebee.v4.core.models.comment.params.CommentListParams;

/** Immutable response object for CommentList operation. Contains paginated list data. */
public final class CommentListResponse {

  private final List<CommentListItem> list;

  private final String nextOffset;

  private final CommentService service;
  private final CommentListParams originalParams;
  private final Response httpResponse;

  private CommentListResponse(
      List<CommentListItem> list,
      String nextOffset,
      CommentService service,
      CommentListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into CommentListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CommentListResponse fromJson(String json) {
    try {

      List<CommentListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CommentListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CommentListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CommentListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static CommentListResponse fromJson(
      String json,
      CommentService service,
      CommentListParams originalParams,
      Response httpResponse) {
    try {

      List<CommentListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CommentListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CommentListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CommentListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CommentListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CommentListItem> items() {
    return list;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public CommentListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CommentListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : CommentListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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

  public static class CommentListItem {

    private Comment comment;

    public Comment getComment() {
      return comment;
    }

    public static CommentListItem fromJson(String json) {
      CommentListItem item = new CommentListItem();

      String __commentJson = JsonUtil.getObject(json, "comment");
      if (__commentJson != null) {
        item.comment = Comment.fromJson(__commentJson);
      }

      return item;
    }
  }
}
