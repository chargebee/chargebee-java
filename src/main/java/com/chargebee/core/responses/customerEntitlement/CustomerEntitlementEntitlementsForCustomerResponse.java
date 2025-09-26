package com.chargebee.core.responses.customerEntitlement;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.customerEntitlement.CustomerEntitlement;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CustomerEntitlementService;
import com.chargebee.core.models.customerEntitlement.params.CustomerEntitlementEntitlementsForCustomerParams;

/**
 * Immutable response object for CustomerEntitlementEntitlementsForCustomer operation. Contains
 * paginated list data with auto-pagination support.
 */
public final class CustomerEntitlementEntitlementsForCustomerResponse
    implements Iterable<
        CustomerEntitlementEntitlementsForCustomerResponse
            .CustomerEntitlementEntitlementsForCustomerItem> {

  private final List<CustomerEntitlementEntitlementsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerEntitlementService service;
  private final CustomerEntitlementEntitlementsForCustomerParams originalParams;
  private final boolean isAutoPaginate;

  private CustomerEntitlementEntitlementsForCustomerResponse(
      List<CustomerEntitlementEntitlementsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerEntitlementService service,
      CustomerEntitlementEntitlementsForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CustomerEntitlementEntitlementsForCustomerResponse(
      List<CustomerEntitlementEntitlementsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerEntitlementService service,
      CustomerEntitlementEntitlementsForCustomerParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into CustomerEntitlementEntitlementsForCustomerResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static CustomerEntitlementEntitlementsForCustomerResponse fromJson(String json) {
    try {

      List<CustomerEntitlementEntitlementsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerEntitlementEntitlementsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerEntitlementEntitlementsForCustomerResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerEntitlementEntitlementsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerEntitlementEntitlementsForCustomerResponse object with service
   * context for pagination (enables nextPage(), autoPaginate()).
   */
  public static CustomerEntitlementEntitlementsForCustomerResponse fromJson(
      String json,
      CustomerEntitlementService service,
      CustomerEntitlementEntitlementsForCustomerParams originalParams,
      String customerId) {
    try {

      List<CustomerEntitlementEntitlementsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerEntitlementEntitlementsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerEntitlementEntitlementsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerEntitlementEntitlementsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerEntitlementEntitlementsForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CustomerEntitlementEntitlementsForCustomerItem> items() {
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
  public CustomerEntitlementEntitlementsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CustomerEntitlementEntitlementsForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.entitlementsForCustomer(customerId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CustomerEntitlementEntitlementsForCustomerResponse autoPaginate() {
    return new CustomerEntitlementEntitlementsForCustomerResponse(
        list, nextOffset, customerId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CustomerEntitlementEntitlementsForCustomerItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<CustomerEntitlementEntitlementsForCustomerItem> {
    private CustomerEntitlementEntitlementsForCustomerResponse currentPage =
        CustomerEntitlementEntitlementsForCustomerResponse.this;
    private Iterator<CustomerEntitlementEntitlementsForCustomerItem> currentIterator =
        currentPage.list.iterator();

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
    public CustomerEntitlementEntitlementsForCustomerItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class CustomerEntitlementEntitlementsForCustomerItem {

    private CustomerEntitlement customerEntitlement;

    public CustomerEntitlement getCustomerEntitlement() {
      return customerEntitlement;
    }

    public static CustomerEntitlementEntitlementsForCustomerItem fromJson(String json) {
      CustomerEntitlementEntitlementsForCustomerItem item =
          new CustomerEntitlementEntitlementsForCustomerItem();

      String __customerEntitlementJson = JsonUtil.getObject(json, "customer_entitlement");
      if (__customerEntitlementJson != null) {
        item.customerEntitlement = CustomerEntitlement.fromJson(__customerEntitlementJson);
      }

      return item;
    }
  }
}
