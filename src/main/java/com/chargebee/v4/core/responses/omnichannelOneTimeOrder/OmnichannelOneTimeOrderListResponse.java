package com.chargebee.v4.core.responses.omnichannelOneTimeOrder;

import java.util.List;

import com.chargebee.v4.core.models.omnichannelOneTimeOrder.OmnichannelOneTimeOrder;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.OmnichannelOneTimeOrderService;
import com.chargebee.v4.core.models.omnichannelOneTimeOrder.params.OmnichannelOneTimeOrderListParams;

/**
 * Immutable response object for OmnichannelOneTimeOrderList operation. Contains paginated list
 * data.
 */
public final class OmnichannelOneTimeOrderListResponse {

  private final List<OmnichannelOneTimeOrderListItem> list;

  private final String nextOffset;

  private final OmnichannelOneTimeOrderService service;
  private final OmnichannelOneTimeOrderListParams originalParams;

  private OmnichannelOneTimeOrderListResponse(
      List<OmnichannelOneTimeOrderListItem> list,
      String nextOffset,
      OmnichannelOneTimeOrderService service,
      OmnichannelOneTimeOrderListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into OmnichannelOneTimeOrderListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelOneTimeOrderListResponse fromJson(String json) {
    try {

      List<OmnichannelOneTimeOrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelOneTimeOrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelOneTimeOrderListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OmnichannelOneTimeOrderListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static OmnichannelOneTimeOrderListResponse fromJson(
      String json,
      OmnichannelOneTimeOrderService service,
      OmnichannelOneTimeOrderListParams originalParams) {
    try {

      List<OmnichannelOneTimeOrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelOneTimeOrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelOneTimeOrderListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelOneTimeOrderListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OmnichannelOneTimeOrderListItem> items() {
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
  public OmnichannelOneTimeOrderListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OmnichannelOneTimeOrderListParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class OmnichannelOneTimeOrderListItem {

    private OmnichannelOneTimeOrder omnichannelOneTimeOrder;

    public OmnichannelOneTimeOrder getOmnichannelOneTimeOrder() {
      return omnichannelOneTimeOrder;
    }

    public static OmnichannelOneTimeOrderListItem fromJson(String json) {
      OmnichannelOneTimeOrderListItem item = new OmnichannelOneTimeOrderListItem();

      String __omnichannelOneTimeOrderJson = JsonUtil.getObject(json, "omnichannel_one_time_order");
      if (__omnichannelOneTimeOrderJson != null) {
        item.omnichannelOneTimeOrder =
            OmnichannelOneTimeOrder.fromJson(__omnichannelOneTimeOrderJson);
      }

      return item;
    }
  }
}
