package com.chargebee.v4.models.attachedItem.responses;

import java.util.List;

import com.chargebee.v4.models.attachedItem.AttachedItem;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.AttachedItemService;
import com.chargebee.v4.models.attachedItem.params.AttachedItemsForItemParams;

/** Immutable response object for AttachedItemsForItem operation. Contains paginated list data. */
public final class AttachedItemsForItemResponse {

  private final List<AttachedItemAttachedItemsForItemItem> list;

  private final String nextOffset;

  private final String itemId;

  private final AttachedItemService service;
  private final AttachedItemsForItemParams originalParams;
  private final Response httpResponse;

  private AttachedItemsForItemResponse(
      List<AttachedItemAttachedItemsForItemItem> list,
      String nextOffset,
      String itemId,
      AttachedItemService service,
      AttachedItemsForItemParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into AttachedItemsForItemResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static AttachedItemsForItemResponse fromJson(String json) {
    try {

      List<AttachedItemAttachedItemsForItemItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemAttachedItemsForItemItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemsForItemResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemsForItemResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AttachedItemsForItemResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static AttachedItemsForItemResponse fromJson(
      String json,
      AttachedItemService service,
      AttachedItemsForItemParams originalParams,
      String itemId,
      Response httpResponse) {
    try {

      List<AttachedItemAttachedItemsForItemItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemAttachedItemsForItemItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemsForItemResponse(
          list, nextOffset, itemId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemsForItemResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AttachedItemAttachedItemsForItemItem> getList() {
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
   * @throws Exception if unable to fetch next page
   */
  public AttachedItemsForItemResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    AttachedItemsForItemParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : AttachedItemsForItemParams.builder())
            .offset(nextOffset)
            .build();

    return service.attachedItemsForItem(itemId, nextParams);
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

  public static class AttachedItemAttachedItemsForItemItem {

    private AttachedItem attachedItem;

    public AttachedItem getAttachedItem() {
      return attachedItem;
    }

    public static AttachedItemAttachedItemsForItemItem fromJson(String json) {
      AttachedItemAttachedItemsForItemItem item = new AttachedItemAttachedItemsForItemItem();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        item.attachedItem = AttachedItem.fromJson(__attachedItemJson);
      }

      return item;
    }
  }
}
