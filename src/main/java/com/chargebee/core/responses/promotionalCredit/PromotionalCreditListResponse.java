package com.chargebee.core.responses.promotionalCredit;

import java.util.List;

import com.chargebee.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.PromotionalCreditService;
import com.chargebee.core.models.promotionalCredit.params.PromotionalCreditListParams;

/** Immutable response object for PromotionalCreditList operation. Contains paginated list data. */
public final class PromotionalCreditListResponse {

  private final List<PromotionalCreditListItem> list;

  private final String nextOffset;

  private final PromotionalCreditService service;
  private final PromotionalCreditListParams originalParams;

  private PromotionalCreditListResponse(
      List<PromotionalCreditListItem> list,
      String nextOffset,
      PromotionalCreditService service,
      PromotionalCreditListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into PromotionalCreditListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static PromotionalCreditListResponse fromJson(String json) {
    try {

      List<PromotionalCreditListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PromotionalCreditListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PromotionalCreditListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PromotionalCreditListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static PromotionalCreditListResponse fromJson(
      String json, PromotionalCreditService service, PromotionalCreditListParams originalParams) {
    try {

      List<PromotionalCreditListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PromotionalCreditListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PromotionalCreditListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PromotionalCreditListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PromotionalCreditListItem> items() {
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
  public PromotionalCreditListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PromotionalCreditListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class PromotionalCreditListItem {

    private PromotionalCredit promotionalCredit;

    public PromotionalCredit getPromotionalCredit() {
      return promotionalCredit;
    }

    public static PromotionalCreditListItem fromJson(String json) {
      PromotionalCreditListItem item = new PromotionalCreditListItem();

      String __promotionalCreditJson = JsonUtil.getObject(json, "promotional_credit");
      if (__promotionalCreditJson != null) {
        item.promotionalCredit = PromotionalCredit.fromJson(__promotionalCreditJson);
      }

      return item;
    }
  }
}
