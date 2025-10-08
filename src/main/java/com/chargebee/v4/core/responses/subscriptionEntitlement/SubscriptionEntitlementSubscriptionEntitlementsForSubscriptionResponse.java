package com.chargebee.v4.core.responses.subscriptionEntitlement;

import java.util.List;

import com.chargebee.v4.core.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.SubscriptionEntitlementService;
import com.chargebee.v4.core.models.subscriptionEntitlement.params.SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams;

/**
 * Immutable response object for SubscriptionEntitlementSubscriptionEntitlementsForSubscription
 * operation. Contains paginated list data.
 */
public final class SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse {

  private final List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionEntitlementService service;
  private final SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams;
  private final Response httpResponse;

  private SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
   * object (no service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse fromJson(
      String json) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
   * object with service context for pagination (enables nextPage()).
   */
  public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse fromJson(
      String json,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> items() {
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
  public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.subscriptionEntitlementsForSubscription(subscriptionId, nextParams);
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

  public static class SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem {

    private SubscriptionEntitlement subscriptionEntitlement;

    public SubscriptionEntitlement getSubscriptionEntitlement() {
      return subscriptionEntitlement;
    }

    public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem fromJson(
        String json) {
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem item =
          new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem();

      String __subscriptionEntitlementJson = JsonUtil.getObject(json, "subscription_entitlement");
      if (__subscriptionEntitlementJson != null) {
        item.subscriptionEntitlement =
            SubscriptionEntitlement.fromJson(__subscriptionEntitlementJson);
      }

      return item;
    }
  }
}
