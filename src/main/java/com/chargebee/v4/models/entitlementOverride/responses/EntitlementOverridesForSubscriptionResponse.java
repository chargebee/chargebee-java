package com.chargebee.v4.models.entitlementOverride.responses;

import java.util.List;

import com.chargebee.v4.models.entitlementOverride.EntitlementOverride;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.EntitlementOverrideService;
import com.chargebee.v4.models.entitlementOverride.params.EntitlementOverridesForSubscriptionParams;

/**
 * Immutable response object for EntitlementOverridesForSubscription operation. Contains paginated
 * list data.
 */
public final class EntitlementOverridesForSubscriptionResponse {

  private final List<EntitlementOverrideEntitlementOverridesForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final EntitlementOverrideService service;
  private final EntitlementOverridesForSubscriptionParams originalParams;
  private final Response httpResponse;

  private EntitlementOverridesForSubscriptionResponse(
      List<EntitlementOverrideEntitlementOverridesForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      EntitlementOverrideService service,
      EntitlementOverridesForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into EntitlementOverridesForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static EntitlementOverridesForSubscriptionResponse fromJson(String json) {
    try {

      List<EntitlementOverrideEntitlementOverridesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideEntitlementOverridesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementOverridesForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverridesForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into EntitlementOverridesForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static EntitlementOverridesForSubscriptionResponse fromJson(
      String json,
      EntitlementOverrideService service,
      EntitlementOverridesForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<EntitlementOverrideEntitlementOverridesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideEntitlementOverridesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementOverridesForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverridesForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementOverrideEntitlementOverridesForSubscriptionItem> getList() {
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
  public EntitlementOverridesForSubscriptionResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    EntitlementOverridesForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : EntitlementOverridesForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.entitlementOverridesForSubscription(subscriptionId, nextParams);
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

  public static class EntitlementOverrideEntitlementOverridesForSubscriptionItem {

    private EntitlementOverride entitlementOverride;

    public EntitlementOverride getEntitlementOverride() {
      return entitlementOverride;
    }

    public static EntitlementOverrideEntitlementOverridesForSubscriptionItem fromJson(String json) {
      EntitlementOverrideEntitlementOverridesForSubscriptionItem item =
          new EntitlementOverrideEntitlementOverridesForSubscriptionItem();

      String __entitlementOverrideJson = JsonUtil.getObject(json, "entitlement_override");
      if (__entitlementOverrideJson != null) {
        item.entitlementOverride = EntitlementOverride.fromJson(__entitlementOverrideJson);
      }

      return item;
    }
  }
}
