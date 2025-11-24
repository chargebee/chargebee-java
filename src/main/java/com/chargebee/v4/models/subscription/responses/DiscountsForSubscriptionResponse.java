package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.discount.Discount;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.SubscriptionService;
import com.chargebee.v4.models.subscription.params.DiscountsForSubscriptionParams;

/**
 * Immutable response object for DiscountsForSubscription operation. Contains paginated list data.
 */
public final class DiscountsForSubscriptionResponse {

  private final List<SubscriptionDiscountsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionService service;
  private final DiscountsForSubscriptionParams originalParams;
  private final Response httpResponse;

  private DiscountsForSubscriptionResponse(
      List<SubscriptionDiscountsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionService service,
      DiscountsForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into DiscountsForSubscriptionResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static DiscountsForSubscriptionResponse fromJson(String json) {
    try {

      List<SubscriptionDiscountsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionDiscountsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new DiscountsForSubscriptionResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DiscountsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into DiscountsForSubscriptionResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static DiscountsForSubscriptionResponse fromJson(
      String json,
      SubscriptionService service,
      DiscountsForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<SubscriptionDiscountsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionDiscountsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new DiscountsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DiscountsForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionDiscountsForSubscriptionItem> getList() {
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
  public DiscountsForSubscriptionResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    DiscountsForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : DiscountsForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.discountsForSubscription(subscriptionId, nextParams);
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

  public static class SubscriptionDiscountsForSubscriptionItem {

    private Discount discount;

    public Discount getDiscount() {
      return discount;
    }

    public static SubscriptionDiscountsForSubscriptionItem fromJson(String json) {
      SubscriptionDiscountsForSubscriptionItem item =
          new SubscriptionDiscountsForSubscriptionItem();

      String __discountJson = JsonUtil.getObject(json, "discount");
      if (__discountJson != null) {
        item.discount = Discount.fromJson(__discountJson);
      }

      return item;
    }
  }
}
