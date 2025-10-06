package com.chargebee.v4.core.responses.subscription;

import java.util.List;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.SubscriptionService;
import com.chargebee.v4.core.models.subscription.params.SubscriptionListParams;

/** Immutable response object for SubscriptionList operation. Contains paginated list data. */
public final class SubscriptionListResponse {

  private final List<SubscriptionListItem> list;

  private final String nextOffset;

  private final SubscriptionService service;
  private final SubscriptionListParams originalParams;

  private SubscriptionListResponse(
      List<SubscriptionListItem> list,
      String nextOffset,
      SubscriptionService service,
      SubscriptionListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into SubscriptionListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static SubscriptionListResponse fromJson(String json) {
    try {

      List<SubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static SubscriptionListResponse fromJson(
      String json, SubscriptionService service, SubscriptionListParams originalParams) {
    try {

      List<SubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionListItem> items() {
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
  public SubscriptionListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SubscriptionListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class SubscriptionListItem {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    public Subscription getSubscription() {
      return subscription;
    }

    public Customer getCustomer() {
      return customer;
    }

    public Card getCard() {
      return card;
    }

    public static SubscriptionListItem fromJson(String json) {
      SubscriptionListItem item = new SubscriptionListItem();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        item.subscription = Subscription.fromJson(__subscriptionJson);
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        item.customer = Customer.fromJson(__customerJson);
      }

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        item.card = Card.fromJson(__cardJson);
      }

      return item;
    }
  }
}
