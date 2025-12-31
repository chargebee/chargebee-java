package com.chargebee.v4.models.entitlement.responses;

import java.util.List;

import com.chargebee.v4.models.entitlement.Entitlement;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.EntitlementService;
import com.chargebee.v4.models.entitlement.params.EntitlementListParams;

/** Immutable response object for EntitlementList operation. Contains paginated list data. */
public final class EntitlementListResponse {

  private final List<EntitlementListItem> list;

  private final String nextOffset;

  private final EntitlementService service;
  private final EntitlementListParams originalParams;
  private final Response httpResponse;

  private EntitlementListResponse(
      List<EntitlementListItem> list,
      String nextOffset,
      EntitlementService service,
      EntitlementListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into EntitlementListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static EntitlementListResponse fromJson(String json) {
    try {

      List<EntitlementListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into EntitlementListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static EntitlementListResponse fromJson(
      String json,
      EntitlementService service,
      EntitlementListParams originalParams,
      Response httpResponse) {
    try {

      List<EntitlementListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementListItem> getList() {
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
  public EntitlementListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    EntitlementListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : EntitlementListParams.builder())
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

  @Override
  public String toString() {
    return "EntitlementListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EntitlementListResponse that = (EntitlementListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class EntitlementListItem {

    private Entitlement entitlement;

    public Entitlement getEntitlement() {
      return entitlement;
    }

    public static EntitlementListItem fromJson(String json) {
      EntitlementListItem item = new EntitlementListItem();

      String __entitlementJson = JsonUtil.getObject(json, "entitlement");
      if (__entitlementJson != null) {
        item.entitlement = Entitlement.fromJson(__entitlementJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "EntitlementListItem{" + "entitlement=" + entitlement + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EntitlementListItem that = (EntitlementListItem) o;
      return java.util.Objects.equals(entitlement, that.entitlement);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(entitlement);
    }
  }
}
