package com.chargebee.v4.models.itemFamily.responses;

import java.util.List;

import com.chargebee.v4.models.itemFamily.ItemFamily;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.ItemFamilyService;
import com.chargebee.v4.models.itemFamily.params.ItemFamilyListParams;

/** Immutable response object for ItemFamilyList operation. Contains paginated list data. */
public final class ItemFamilyListResponse {

  private final List<ItemFamilyListItem> list;

  private final String nextOffset;

  private final ItemFamilyService service;
  private final ItemFamilyListParams originalParams;
  private final Response httpResponse;

  private ItemFamilyListResponse(
      List<ItemFamilyListItem> list,
      String nextOffset,
      ItemFamilyService service,
      ItemFamilyListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemFamilyListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ItemFamilyListResponse fromJson(String json) {
    try {

      List<ItemFamilyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemFamilyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemFamilyListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemFamilyListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ItemFamilyListResponse fromJson(
      String json,
      ItemFamilyService service,
      ItemFamilyListParams originalParams,
      Response httpResponse) {
    try {

      List<ItemFamilyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemFamilyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemFamilyListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemFamilyListItem> getList() {
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
  public ItemFamilyListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemFamilyListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ItemFamilyListParams.builder())
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

  @Override
  public String toString() {
    return "ItemFamilyListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemFamilyListResponse that = (ItemFamilyListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class ItemFamilyListItem {

    private ItemFamily itemFamily;

    public ItemFamily getItemFamily() {
      return itemFamily;
    }

    public static ItemFamilyListItem fromJson(String json) {
      ItemFamilyListItem item = new ItemFamilyListItem();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        item.itemFamily = ItemFamily.fromJson(__itemFamilyJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "ItemFamilyListItem{" + "itemFamily=" + itemFamily + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ItemFamilyListItem that = (ItemFamilyListItem) o;
      return java.util.Objects.equals(itemFamily, that.itemFamily);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(itemFamily);
    }
  }
}
