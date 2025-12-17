package com.chargebee.v4.models.customer.responses;

import java.util.List;

import com.chargebee.v4.models.hierarchy.Hierarchy;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CustomerService;
import com.chargebee.v4.models.customer.params.HierarchyDetailForCustomerParams;

/**
 * Immutable response object for HierarchyDetailForCustomer operation. Contains paginated list data.
 */
public final class HierarchyDetailForCustomerResponse {

  private final List<CustomerHierarchyDetailForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final HierarchyDetailForCustomerParams originalParams;
  private final Response httpResponse;

  private HierarchyDetailForCustomerResponse(
      List<CustomerHierarchyDetailForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      HierarchyDetailForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into HierarchyDetailForCustomerResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static HierarchyDetailForCustomerResponse fromJson(String json) {
    try {

      List<CustomerHierarchyDetailForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerHierarchyDetailForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HierarchyDetailForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HierarchyDetailForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into HierarchyDetailForCustomerResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static HierarchyDetailForCustomerResponse fromJson(
      String json,
      CustomerService service,
      HierarchyDetailForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<CustomerHierarchyDetailForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerHierarchyDetailForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HierarchyDetailForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HierarchyDetailForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerHierarchyDetailForCustomerItem> getList() {
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
  public HierarchyDetailForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    HierarchyDetailForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : HierarchyDetailForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.hierarchyDetailForCustomer(customerId, nextParams);
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

  public static class CustomerHierarchyDetailForCustomerItem {

    private Hierarchy hierarchy;

    public Hierarchy getHierarchy() {
      return hierarchy;
    }

    public static CustomerHierarchyDetailForCustomerItem fromJson(String json) {
      CustomerHierarchyDetailForCustomerItem item = new CustomerHierarchyDetailForCustomerItem();

      String __hierarchyJson = JsonUtil.getObject(json, "hierarchy");
      if (__hierarchyJson != null) {
        item.hierarchy = Hierarchy.fromJson(__hierarchyJson);
      }

      return item;
    }
  }
}
