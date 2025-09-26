package com.chargebee.core.responses.customer;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.card.Card;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerListParams;

/**
 * Immutable response object for CustomerList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class CustomerListResponse implements Iterable<CustomerListResponse.CustomerListItem> {

  private final List<CustomerListItem> list;

  private final String nextOffset;

  private final CustomerService service;
  private final CustomerListParams originalParams;
  private final boolean isAutoPaginate;

  private CustomerListResponse(
      List<CustomerListItem> list,
      String nextOffset,
      CustomerService service,
      CustomerListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CustomerListResponse(
      List<CustomerListItem> list,
      String nextOffset,
      CustomerService service,
      CustomerListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into CustomerListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CustomerListResponse fromJson(String json) {
    try {

      List<CustomerListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static CustomerListResponse fromJson(
      String json, CustomerService service, CustomerListParams originalParams) {
    try {

      List<CustomerListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CustomerListItem> items() {
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
  public CustomerListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CustomerListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CustomerListResponse autoPaginate() {
    return new CustomerListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CustomerListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CustomerListItem> {
    private CustomerListResponse currentPage = CustomerListResponse.this;
    private Iterator<CustomerListItem> currentIterator = currentPage.list.iterator();

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
    public CustomerListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class CustomerListItem {

    private Customer customer;

    private Card card;

    public Customer getCustomer() {
      return customer;
    }

    public Card getCard() {
      return card;
    }

    public static CustomerListItem fromJson(String json) {
      CustomerListItem item = new CustomerListItem();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        item.customer = Customer.fromJson(__customerJson);
      }

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        item.card = Card.fromJson(__cardJson);
      }

      return item;
    }
  }
}
