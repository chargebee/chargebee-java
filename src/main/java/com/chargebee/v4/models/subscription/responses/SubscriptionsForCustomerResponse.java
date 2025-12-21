package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.SubscriptionService;
import com.chargebee.v4.models.subscription.params.SubscriptionsForCustomerParams;

/**
 * Immutable response object for SubscriptionsForCustomer operation. Contains paginated list data.
 */
public final class SubscriptionsForCustomerResponse {

  private final List<SubscriptionSubscriptionsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final SubscriptionService service;
  private final SubscriptionsForCustomerParams originalParams;
  private final Response httpResponse;

  private SubscriptionsForCustomerResponse(
      List<SubscriptionSubscriptionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      SubscriptionService service,
      SubscriptionsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into SubscriptionsForCustomerResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionsForCustomerResponse fromJson(String json) {
    try {

      List<SubscriptionSubscriptionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionSubscriptionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionsForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionsForCustomerResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static SubscriptionsForCustomerResponse fromJson(
      String json,
      SubscriptionService service,
      SubscriptionsForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<SubscriptionSubscriptionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionSubscriptionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionSubscriptionsForCustomerItem> getList() {
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
  public SubscriptionsForCustomerResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    SubscriptionsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : SubscriptionsForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.subscriptionsForCustomer(customerId, nextParams);
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

  public static class SubscriptionSubscriptionsForCustomerItem {

    private Subscription subscription;

    public Subscription getSubscription() {
      return subscription;
    }

    public static SubscriptionSubscriptionsForCustomerItem fromJson(String json) {
      SubscriptionSubscriptionsForCustomerItem item =
          new SubscriptionSubscriptionsForCustomerItem();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        item.subscription = Subscription.fromJson(__subscriptionJson);
      }

      return item;
    }
  }
}
