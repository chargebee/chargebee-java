package com.chargebee.v4.core.responses.differentialPrice;

import java.util.List;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.DifferentialPriceService;
import com.chargebee.v4.core.models.differentialPrice.params.DifferentialPriceListParams;

/** Immutable response object for DifferentialPriceList operation. Contains paginated list data. */
public final class DifferentialPriceListResponse {

  private final List<DifferentialPriceListItem> list;

  private final String nextOffset;

  private final DifferentialPriceService service;
  private final DifferentialPriceListParams originalParams;

  private DifferentialPriceListResponse(
      List<DifferentialPriceListItem> list,
      String nextOffset,
      DifferentialPriceService service,
      DifferentialPriceListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into DifferentialPriceListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static DifferentialPriceListResponse fromJson(String json) {
    try {

      List<DifferentialPriceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(DifferentialPriceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new DifferentialPriceListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into DifferentialPriceListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static DifferentialPriceListResponse fromJson(
      String json, DifferentialPriceService service, DifferentialPriceListParams originalParams) {
    try {

      List<DifferentialPriceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(DifferentialPriceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new DifferentialPriceListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<DifferentialPriceListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<DifferentialPriceListItem> items() {
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
  public DifferentialPriceListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    DifferentialPriceListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class DifferentialPriceListItem {

    private DifferentialPrice differentialPrice;

    public DifferentialPrice getDifferentialPrice() {
      return differentialPrice;
    }

    public static DifferentialPriceListItem fromJson(String json) {
      DifferentialPriceListItem item = new DifferentialPriceListItem();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        item.differentialPrice = DifferentialPrice.fromJson(__differentialPriceJson);
      }

      return item;
    }
  }
}
