package com.chargebee.v4.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.UnbilledChargeService;
import com.chargebee.v4.core.models.unbilledCharge.params.UnbilledChargeListParams;

/** Immutable response object for UnbilledChargeList operation. Contains paginated list data. */
public final class UnbilledChargeListResponse {

  private final List<UnbilledChargeListItem> list;

  private final String nextOffset;

  private final UnbilledChargeService service;
  private final UnbilledChargeListParams originalParams;

  private UnbilledChargeListResponse(
      List<UnbilledChargeListItem> list,
      String nextOffset,
      UnbilledChargeService service,
      UnbilledChargeListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into UnbilledChargeListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static UnbilledChargeListResponse fromJson(String json) {
    try {

      List<UnbilledChargeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UnbilledChargeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UnbilledChargeListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into UnbilledChargeListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static UnbilledChargeListResponse fromJson(
      String json, UnbilledChargeService service, UnbilledChargeListParams originalParams) {
    try {

      List<UnbilledChargeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UnbilledChargeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UnbilledChargeListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<UnbilledChargeListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<UnbilledChargeListItem> items() {
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
  public UnbilledChargeListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    UnbilledChargeListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class UnbilledChargeListItem {

    private UnbilledCharge unbilledCharge;

    public UnbilledCharge getUnbilledCharge() {
      return unbilledCharge;
    }

    public static UnbilledChargeListItem fromJson(String json) {
      UnbilledChargeListItem item = new UnbilledChargeListItem();

      String __unbilledChargeJson = JsonUtil.getObject(json, "unbilled_charge");
      if (__unbilledChargeJson != null) {
        item.unbilledCharge = UnbilledCharge.fromJson(__unbilledChargeJson);
      }

      return item;
    }
  }
}
