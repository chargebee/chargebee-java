package com.chargebee.v4.core.responses.customer;

import java.util.List;

import com.chargebee.v4.core.models.hierarchy.Hierarchy;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.CustomerService;
import com.chargebee.v4.core.models.customer.params.CustomerListHierarchyDetailParams;

/**
 * Immutable response object for CustomerListHierarchyDetail operation. Contains paginated list
 * data.
 */
public final class CustomerListHierarchyDetailResponse {

  private final List<CustomerListHierarchyDetailItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final CustomerListHierarchyDetailParams originalParams;

  private CustomerListHierarchyDetailResponse(
      List<CustomerListHierarchyDetailItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      CustomerListHierarchyDetailParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into CustomerListHierarchyDetailResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static CustomerListHierarchyDetailResponse fromJson(String json) {
    try {

      List<CustomerListHierarchyDetailItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerListHierarchyDetailItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerListHierarchyDetailResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerListHierarchyDetailResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerListHierarchyDetailResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static CustomerListHierarchyDetailResponse fromJson(
      String json,
      CustomerService service,
      CustomerListHierarchyDetailParams originalParams,
      String customerId) {
    try {

      List<CustomerListHierarchyDetailItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerListHierarchyDetailItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerListHierarchyDetailResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerListHierarchyDetailResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerListHierarchyDetailItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CustomerListHierarchyDetailItem> items() {
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
  public CustomerListHierarchyDetailResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CustomerListHierarchyDetailParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listHierarchyDetail(customerId, nextParams);
  }

  public static class CustomerListHierarchyDetailItem {

    private List<Hierarchy> hierarchies;

    public List<Hierarchy> getHierarchies() {
      return hierarchies;
    }

    public static CustomerListHierarchyDetailItem fromJson(String json) {
      CustomerListHierarchyDetailItem item = new CustomerListHierarchyDetailItem();

      item.hierarchies =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "hierarchies")).stream()
              .map(Hierarchy::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return item;
    }
  }
}
