package com.chargebee.v4.models.attachedItem.responses;

import java.util.List;

import com.chargebee.v4.models.attachedItem.AttachedItem;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.AttachedItemService;
import com.chargebee.v4.models.attachedItem.params.AttachedItemListParams;

/** Immutable response object for AttachedItemList operation. Contains paginated list data. */
public final class AttachedItemListResponse {

  private final List<AttachedItemListItem> list;

  private final String nextOffset;

  private final String itemId;

  private final AttachedItemService service;
  private final AttachedItemListParams originalParams;
  private final Response httpResponse;

  private AttachedItemListResponse(
      List<AttachedItemListItem> list,
      String nextOffset,
      String itemId,
      AttachedItemService service,
      AttachedItemListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into AttachedItemListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static AttachedItemListResponse fromJson(String json) {
    try {

      List<AttachedItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemListResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AttachedItemListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static AttachedItemListResponse fromJson(
      String json,
      AttachedItemService service,
      AttachedItemListParams originalParams,
      String itemId,
      Response httpResponse) {
    try {

      List<AttachedItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemListResponse(
          list, nextOffset, itemId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AttachedItemListItem> getList() {
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
  public AttachedItemListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    AttachedItemListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : AttachedItemListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(itemId, nextParams);
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
    return "AttachedItemListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AttachedItemListResponse that = (AttachedItemListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class AttachedItemListItem {

    private AttachedItem attachedItem;

    public AttachedItem getAttachedItem() {
      return attachedItem;
    }

    public static AttachedItemListItem fromJson(String json) {
      AttachedItemListItem item = new AttachedItemListItem();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        item.attachedItem = AttachedItem.fromJson(__attachedItemJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "AttachedItemListItem{" + "attachedItem=" + attachedItem + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      AttachedItemListItem that = (AttachedItemListItem) o;
      return java.util.Objects.equals(attachedItem, that.attachedItem);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(attachedItem);
    }
  }
}
