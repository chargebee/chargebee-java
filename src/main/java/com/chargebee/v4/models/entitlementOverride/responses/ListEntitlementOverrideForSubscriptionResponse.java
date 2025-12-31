package com.chargebee.v4.models.entitlementOverride.responses;

import java.util.List;

import com.chargebee.v4.models.entitlementOverride.EntitlementOverride;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.EntitlementOverrideService;
import com.chargebee.v4.models.entitlementOverride.params.ListEntitlementOverrideForSubscriptionParams;

/**
 * Immutable response object for ListEntitlementOverrideForSubscription operation. Contains
 * paginated list data.
 */
public final class ListEntitlementOverrideForSubscriptionResponse {

  private final List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final EntitlementOverrideService service;
  private final ListEntitlementOverrideForSubscriptionParams originalParams;
  private final Response httpResponse;

  private ListEntitlementOverrideForSubscriptionResponse(
      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      EntitlementOverrideService service,
      ListEntitlementOverrideForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListEntitlementOverrideForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static ListEntitlementOverrideForSubscriptionResponse fromJson(String json) {
    try {

      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideListEntitlementOverrideForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ListEntitlementOverrideForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListEntitlementOverrideForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListEntitlementOverrideForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static ListEntitlementOverrideForSubscriptionResponse fromJson(
      String json,
      EntitlementOverrideService service,
      ListEntitlementOverrideForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideListEntitlementOverrideForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ListEntitlementOverrideForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListEntitlementOverrideForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> getList() {
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
  public ListEntitlementOverrideForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListEntitlementOverrideForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ListEntitlementOverrideForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.listEntitlementOverrideForSubscription(subscriptionId, nextParams);
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
    return "ListEntitlementOverrideForSubscriptionResponse{"
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

    ListEntitlementOverrideForSubscriptionResponse that =
        (ListEntitlementOverrideForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class EntitlementOverrideListEntitlementOverrideForSubscriptionItem {

    private EntitlementOverride entitlementOverride;

    public EntitlementOverride getEntitlementOverride() {
      return entitlementOverride;
    }

    public static EntitlementOverrideListEntitlementOverrideForSubscriptionItem fromJson(
        String json) {
      EntitlementOverrideListEntitlementOverrideForSubscriptionItem item =
          new EntitlementOverrideListEntitlementOverrideForSubscriptionItem();

      String __entitlementOverrideJson = JsonUtil.getObject(json, "entitlement_override");
      if (__entitlementOverrideJson != null) {
        item.entitlementOverride = EntitlementOverride.fromJson(__entitlementOverrideJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "EntitlementOverrideListEntitlementOverrideForSubscriptionItem{"
          + "entitlementOverride="
          + entitlementOverride
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      EntitlementOverrideListEntitlementOverrideForSubscriptionItem that =
          (EntitlementOverrideListEntitlementOverrideForSubscriptionItem) o;
      return java.util.Objects.equals(entitlementOverride, that.entitlementOverride);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(entitlementOverride);
    }
  }
}
