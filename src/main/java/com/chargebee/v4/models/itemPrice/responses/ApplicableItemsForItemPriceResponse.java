package com.chargebee.v4.models.itemPrice.responses;

import java.util.List;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.ItemPriceService;
import com.chargebee.v4.models.itemPrice.params.ApplicableItemsForItemPriceParams;

/**
 * Immutable response object for ApplicableItemsForItemPrice operation. Contains paginated list
 * data.
 */
public final class ApplicableItemsForItemPriceResponse {

  private final List<ItemPriceApplicableItemsForItemPriceItem> list;

  private final String nextOffset;

  private final String itemPriceId;

  private final ItemPriceService service;
  private final ApplicableItemsForItemPriceParams originalParams;
  private final Response httpResponse;

  private ApplicableItemsForItemPriceResponse(
      List<ItemPriceApplicableItemsForItemPriceItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ApplicableItemsForItemPriceParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ApplicableItemsForItemPriceResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static ApplicableItemsForItemPriceResponse fromJson(String json) {
    try {

      List<ItemPriceApplicableItemsForItemPriceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceApplicableItemsForItemPriceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ApplicableItemsForItemPriceResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ApplicableItemsForItemPriceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ApplicableItemsForItemPriceResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ApplicableItemsForItemPriceResponse fromJson(
      String json,
      ItemPriceService service,
      ApplicableItemsForItemPriceParams originalParams,
      String itemPriceId,
      Response httpResponse) {
    try {

      List<ItemPriceApplicableItemsForItemPriceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceApplicableItemsForItemPriceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ApplicableItemsForItemPriceResponse(
          list, nextOffset, itemPriceId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ApplicableItemsForItemPriceResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemPriceApplicableItemsForItemPriceItem> getList() {
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
  public ApplicableItemsForItemPriceResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ApplicableItemsForItemPriceParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ApplicableItemsForItemPriceParams.builder())
            .offset(nextOffset)
            .build();

    return service.applicableItemsForItemPrice(itemPriceId, nextParams);
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

  public static class ItemPriceApplicableItemsForItemPriceItem {

    private Item item;

    public Item getItem() {
      return item;
    }

    public static ItemPriceApplicableItemsForItemPriceItem fromJson(String json) {
      ItemPriceApplicableItemsForItemPriceItem item =
          new ItemPriceApplicableItemsForItemPriceItem();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        item.item = Item.fromJson(__itemJson);
      }

      return item;
    }
  }
}
