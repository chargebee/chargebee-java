package com.chargebee.v4.models.subscriptionEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.SubscriptionEntitlementService;
import com.chargebee.v4.models.subscriptionEntitlement.params.SubscriptionEntitlementsForSubscriptionParams;

/**
 * Immutable response object for SubscriptionEntitlementsForSubscription operation. Contains
 * paginated list data.
 */
public final class SubscriptionEntitlementsForSubscriptionResponse {

  private final List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionEntitlementService service;
  private final SubscriptionEntitlementsForSubscriptionParams originalParams;
  private final Response httpResponse;

  private SubscriptionEntitlementsForSubscriptionResponse(
      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementsForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementsForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionEntitlementsForSubscriptionResponse fromJson(String json) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionEntitlementsForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static SubscriptionEntitlementsForSubscriptionResponse fromJson(
      String json,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementsForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementsForSubscriptionResponse from JSON", e);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public SubscriptionEntitlementsForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    SubscriptionEntitlementsForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : SubscriptionEntitlementsForSubscriptionParams.builder())
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

  @Override
  public String toString() {
    return "SubscriptionEntitlementsForSubscriptionResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionEntitlementsForSubscriptionResponse that =
        (SubscriptionEntitlementsForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem{"
          + "subscriptionEntitlement="
          + subscriptionEntitlement
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem that =
          (SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem) o;
      return java.util.Objects.equals(subscriptionEntitlement, that.subscriptionEntitlement);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(subscriptionEntitlement);
    }
  }
}
