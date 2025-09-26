package com.chargebee.core.responses.customer;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.contact.Contact;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerContactsForCustomerParams;

/**
 * Immutable response object for CustomerContactsForCustomer operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class CustomerContactsForCustomerResponse
    implements Iterable<CustomerContactsForCustomerResponse.CustomerContactsForCustomerItem> {

  private final List<CustomerContactsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final CustomerContactsForCustomerParams originalParams;
  private final boolean isAutoPaginate;

  private CustomerContactsForCustomerResponse(
      List<CustomerContactsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      CustomerContactsForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CustomerContactsForCustomerResponse(
      List<CustomerContactsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      CustomerContactsForCustomerParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into CustomerContactsForCustomerResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static CustomerContactsForCustomerResponse fromJson(String json) {
    try {

      List<CustomerContactsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerContactsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerContactsForCustomerResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerContactsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerContactsForCustomerResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static CustomerContactsForCustomerResponse fromJson(
      String json,
      CustomerService service,
      CustomerContactsForCustomerParams originalParams,
      String customerId) {
    try {

      List<CustomerContactsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerContactsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerContactsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerContactsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerContactsForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CustomerContactsForCustomerItem> items() {
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
  public CustomerContactsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CustomerContactsForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.contactsForCustomer(customerId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CustomerContactsForCustomerResponse autoPaginate() {
    return new CustomerContactsForCustomerResponse(
        list, nextOffset, customerId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CustomerContactsForCustomerItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CustomerContactsForCustomerItem> {
    private CustomerContactsForCustomerResponse currentPage =
        CustomerContactsForCustomerResponse.this;
    private Iterator<CustomerContactsForCustomerItem> currentIterator = currentPage.list.iterator();

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
    public CustomerContactsForCustomerItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class CustomerContactsForCustomerItem {

    private Contact contact;

    public Contact getContact() {
      return contact;
    }

    public static CustomerContactsForCustomerItem fromJson(String json) {
      CustomerContactsForCustomerItem item = new CustomerContactsForCustomerItem();

      String __contactJson = JsonUtil.getObject(json, "contact");
      if (__contactJson != null) {
        item.contact = Contact.fromJson(__contactJson);
      }

      return item;
    }
  }
}
