package com.chargebee.v4.models.draftRequest.responses;

import java.util.List;

import com.chargebee.v4.models.comment.Comment;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.DraftRequestService;
import com.chargebee.v4.models.draftRequest.params.DraftRequestListCommentsParams;

/**
 * Immutable response object for DraftRequestListComments operation. Contains paginated list data.
 */
public final class DraftRequestListCommentsResponse {

  private final List<DraftRequestListCommentsItem> list;

  private final String nextOffset;

  private final String draftRequestId;

  private final DraftRequestService service;
  private final DraftRequestListCommentsParams originalParams;
  private final Response httpResponse;

  private DraftRequestListCommentsResponse(
      List<DraftRequestListCommentsItem> list,
      String nextOffset,
      String draftRequestId,
      DraftRequestService service,
      DraftRequestListCommentsParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.draftRequestId = draftRequestId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into DraftRequestListCommentsResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static DraftRequestListCommentsResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DraftRequestListCommentsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), DraftRequestListCommentsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DraftRequestListCommentsResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestListCommentsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into DraftRequestListCommentsResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static DraftRequestListCommentsResponse fromJson(
      String json,
      DraftRequestService service,
      DraftRequestListCommentsParams originalParams,
      String draftRequestId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DraftRequestListCommentsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), DraftRequestListCommentsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DraftRequestListCommentsResponse(
          list, nextOffset, draftRequestId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestListCommentsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<DraftRequestListCommentsItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public DraftRequestListCommentsResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    DraftRequestListCommentsParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : DraftRequestListCommentsParams.builder())
            .offset(nextOffset)
            .build();

    return service.listComments(draftRequestId, nextParams);
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

  @Override
  public String toString() {
    return "DraftRequestListCommentsResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DraftRequestListCommentsResponse that = (DraftRequestListCommentsResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class DraftRequestListCommentsItem {

    private Comment comment;

    public Comment getComment() {
      return comment;
    }

    public static DraftRequestListCommentsItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static DraftRequestListCommentsItem fromJson(JsonObject jsonObj) {
      DraftRequestListCommentsItem item = new DraftRequestListCommentsItem();

      JsonObject __commentObj = JsonUtil.getJsonObject(jsonObj, "comment");
      if (__commentObj != null) {
        item.comment = Comment.fromJson(__commentObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "DraftRequestListCommentsItem{" + "comment=" + comment + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DraftRequestListCommentsItem that = (DraftRequestListCommentsItem) o;
      return java.util.Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(comment);
    }
  }
}
