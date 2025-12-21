package com.chargebee.v4.models.gift.responses;

import java.util.List;

import com.chargebee.v4.models.gift.Gift;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.GiftService;
import com.chargebee.v4.models.gift.params.GiftListParams;

/** Immutable response object for GiftList operation. Contains paginated list data. */
public final class GiftListResponse {

  private final List<GiftListItem> list;

  private final String nextOffset;

  private final GiftService service;
  private final GiftListParams originalParams;
  private final Response httpResponse;

  private GiftListResponse(
      List<GiftListItem> list,
      String nextOffset,
      GiftService service,
      GiftListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new GiftListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GiftListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into GiftListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static GiftListResponse fromJson(
      String json, GiftService service, GiftListParams originalParams, Response httpResponse) {
    try {

      List<GiftListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(GiftListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new GiftListResponse(list, nextOffset, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public GiftListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    GiftListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : GiftListParams.builder())
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
