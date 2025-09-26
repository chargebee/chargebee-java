package com.chargebee.core.responses.customer;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.hierarchy.Hierarchy;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerListHierarchyDetailParams;

/**
 * Immutable response object for CustomerListHierarchyDetail operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class CustomerListHierarchyDetailResponse
    implements Iterable<CustomerListHierarchyDetailResponse.CustomerListHierarchyDetailItem> {

  private final List<CustomerListHierarchyDetailItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final CustomerListHierarchyDetailParams originalParams;
  private final boolean isAutoPaginate;

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
    this.isAutoPaginate = false;
  }

  private CustomerListHierarchyDetailResponse(
      List<CustomerListHierarchyDetailItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      CustomerListHierarchyDetailParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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
   * pagination (enables nextPage(), autoPaginate()).
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

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CustomerListHierarchyDetailResponse autoPaginate() {
    return new CustomerListHierarchyDetailResponse(
        list, nextOffset, customerId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CustomerListHierarchyDetailItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CustomerListHierarchyDetailItem> {
    private CustomerListHierarchyDetailResponse currentPage =
        CustomerListHierarchyDetailResponse.this;
    private Iterator<CustomerListHierarchyDetailItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public CustomerListHierarchyDetailItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
