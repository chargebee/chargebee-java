package com.chargebee.core.responses.subscription;

import java.util.List;

import com.chargebee.core.models.contractTerm.ContractTerm;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.SubscriptionService;
import com.chargebee.core.models.subscription.params.SubscriptionContractTermsForSubscriptionParams;

/**
 * Immutable response object for SubscriptionContractTermsForSubscription operation. Contains
 * paginated list data.
 */
public final class SubscriptionContractTermsForSubscriptionResponse {

  private final List<SubscriptionContractTermsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionService service;
  private final SubscriptionContractTermsForSubscriptionParams originalParams;

  private SubscriptionContractTermsForSubscriptionResponse(
      List<SubscriptionContractTermsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionService service,
      SubscriptionContractTermsForSubscriptionParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into SubscriptionContractTermsForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionContractTermsForSubscriptionResponse fromJson(String json) {
    try {

      List<SubscriptionContractTermsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionContractTermsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionContractTermsForSubscriptionResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionContractTermsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionContractTermsForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static SubscriptionContractTermsForSubscriptionResponse fromJson(
      String json,
      SubscriptionService service,
      SubscriptionContractTermsForSubscriptionParams originalParams,
      String subscriptionId) {
    try {

      List<SubscriptionContractTermsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionContractTermsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionContractTermsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionContractTermsForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionContractTermsForSubscriptionItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionContractTermsForSubscriptionItem> items() {
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
  public SubscriptionContractTermsForSubscriptionResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SubscriptionContractTermsForSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.contractTermsForSubscription(subscriptionId, nextParams);
  }

  public static class SubscriptionContractTermsForSubscriptionItem {

    private ContractTerm contractTerm;

    public ContractTerm getContractTerm() {
      return contractTerm;
    }

    public static SubscriptionContractTermsForSubscriptionItem fromJson(String json) {
      SubscriptionContractTermsForSubscriptionItem item =
          new SubscriptionContractTermsForSubscriptionItem();

      String __contractTermJson = JsonUtil.getObject(json, "contract_term");
      if (__contractTermJson != null) {
        item.contractTerm = ContractTerm.fromJson(__contractTermJson);
      }

      return item;
    }
  }
}
