package com.chargebee.v4.core.responses.omnichannelSubscription;

import java.util.List;

import com.chargebee.v4.core.models.omnichannelTransaction.OmnichannelTransaction;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.OmnichannelSubscriptionService;
import com.chargebee.v4.core.models.omnichannelSubscription.params.OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionParams;

/**
 * Immutable response object for
 * OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscription operation. Contains
 * paginated list data.
 */
public final
class OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse {

  private final List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem>
      list;

  private final String nextOffset;

  private final String omnichannelSubscriptionId;

  private final OmnichannelSubscriptionService service;
  private final OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionParams
      originalParams;

  private OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse(
      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list,
      String nextOffset,
      String omnichannelSubscriptionId,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionParams
          originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionId = omnichannelSubscriptionId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into
   * OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse object (no
   * service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse
      fromJson(String json) {
    try {

      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(
                  OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
                      ::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into
   * OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse object with
   * service context for pagination (enables nextPage()).
   */
  public static OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse
      fromJson(
          String json,
          OmnichannelSubscriptionService service,
          OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionParams
              originalParams,
          String omnichannelSubscriptionId) {
    try {

      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(
                  OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
                      ::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse(
          list, nextOffset, omnichannelSubscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem>
      getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem>
      items() {
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
  public OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.omnichannel_transactionsForOmnichannelSubscription(
        omnichannelSubscriptionId, nextParams);
  }

  public static class OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem {

    private OmnichannelTransaction omnichannelTransaction;

    public OmnichannelTransaction getOmnichannelTransaction() {
      return omnichannelTransaction;
    }

    public static OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
        fromJson(String json) {
      OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem item =
          new OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem();

      String __omnichannelTransactionJson = JsonUtil.getObject(json, "omnichannel_transaction");
      if (__omnichannelTransactionJson != null) {
        item.omnichannelTransaction = OmnichannelTransaction.fromJson(__omnichannelTransactionJson);
      }

      return item;
    }
  }
}
