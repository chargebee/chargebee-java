package com.chargebee.v4.models.grantBlock.responses;

import java.util.List;

import com.chargebee.v4.models.grantBlock.GrantBlock;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.GrantBlockService;
import com.chargebee.v4.models.grantBlock.params.ListGrantBlocksParams;

/** Immutable response object for ListGrantBlocks operation. Contains paginated list data. */
public final class ListGrantBlocksResponse {

  private final List<GrantBlockListGrantBlocksItem> list;

  private final String nextOffset;

  private final GrantBlockService service;
  private final ListGrantBlocksParams originalParams;
  private final Response httpResponse;

  private ListGrantBlocksResponse(
      List<GrantBlockListGrantBlocksItem> list,
      String nextOffset,
      GrantBlockService service,
      ListGrantBlocksParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListGrantBlocksResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ListGrantBlocksResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GrantBlockListGrantBlocksItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GrantBlockListGrantBlocksItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListGrantBlocksResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListGrantBlocksResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListGrantBlocksResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ListGrantBlocksResponse fromJson(
      String json,
      GrantBlockService service,
      ListGrantBlocksParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GrantBlockListGrantBlocksItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GrantBlockListGrantBlocksItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListGrantBlocksResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListGrantBlocksResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<GrantBlockListGrantBlocksItem> getList() {
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
  public ListGrantBlocksResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListGrantBlocksParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ListGrantBlocksParams.builder())
            .offset(nextOffset)
            .build();

    return service.listGrantBlocks(nextParams);
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
    return "ListGrantBlocksResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListGrantBlocksResponse that = (ListGrantBlocksResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class GrantBlockListGrantBlocksItem {

    private GrantBlock grantBlock;

    public GrantBlock getGrantBlock() {
      return grantBlock;
    }

    public static GrantBlockListGrantBlocksItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static GrantBlockListGrantBlocksItem fromJson(JsonObject jsonObj) {
      GrantBlockListGrantBlocksItem item = new GrantBlockListGrantBlocksItem();

      JsonObject __grantBlockObj = JsonUtil.getJsonObject(jsonObj, "grant_block");
      if (__grantBlockObj != null) {
        item.grantBlock = GrantBlock.fromJson(__grantBlockObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "GrantBlockListGrantBlocksItem{" + "grantBlock=" + grantBlock + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      GrantBlockListGrantBlocksItem that = (GrantBlockListGrantBlocksItem) o;
      return java.util.Objects.equals(grantBlock, that.grantBlock);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(grantBlock);
    }
  }
}
