package com.chargebee.v4.core.responses.item;

import java.util.List;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.ItemService;
import com.chargebee.v4.core.models.item.params.ItemListParams;

/** Immutable response object for ItemList operation. Contains paginated list data. */
public final class ItemListResponse {

  private final List<ItemListItem> list;

  private final String nextOffset;

  private final ItemService service;
  private final ItemListParams originalParams;
  private final Response httpResponse;

  private ItemListResponse(
      List<ItemListItem> list,
      String nextOffset,
      ItemService service,
      ItemListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static ItemListResponse fromJson(String json) {
    try {

      List<ItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static ItemListResponse fromJson(
      String json, ItemService service, ItemListParams originalParams, Response httpResponse) {
    try {

      List<ItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemListItem> getList() {
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
  public ItemListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ItemListParams.builder())
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

  public static class ItemListItem {

    private Item item;

    public Item getItem() {
      return item;
    }

    public static ItemListItem fromJson(String json) {
      ItemListItem item = new ItemListItem();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        item.item = Item.fromJson(__itemJson);
      }

      return item;
    }
  }
}
