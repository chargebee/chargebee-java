package com.chargebee.core.responses.gift;

import java.util.List;

import com.chargebee.core.models.gift.Gift;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.GiftService;
import com.chargebee.core.models.gift.params.GiftListParams;

/** Immutable response object for GiftList operation. Contains paginated list data. */
public final class GiftListResponse {

  private final List<GiftListItem> list;

  private final String nextOffset;

  private final GiftService service;
  private final GiftListParams originalParams;

  private GiftListResponse(
      List<GiftListItem> list,
      String nextOffset,
      GiftService service,
      GiftListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into GiftListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static GiftListResponse fromJson(String json) {
    try {

      List<GiftListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(GiftListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new GiftListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into GiftListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static GiftListResponse fromJson(
      String json, GiftService service, GiftListParams originalParams) {
    try {

      List<GiftListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(GiftListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new GiftListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<GiftListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<GiftListItem> items() {
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
  public GiftListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    GiftListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class GiftListItem {

    private Gift gift;

    private Subscription subscription;

    public Gift getGift() {
      return gift;
    }

    public Subscription getSubscription() {
      return subscription;
    }

    public static GiftListItem fromJson(String json) {
      GiftListItem item = new GiftListItem();

      String __giftJson = JsonUtil.getObject(json, "gift");
      if (__giftJson != null) {
        item.gift = Gift.fromJson(__giftJson);
      }

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        item.subscription = Subscription.fromJson(__subscriptionJson);
      }

      return item;
    }
  }
}
