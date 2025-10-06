package com.chargebee.core.responses.customer;

import java.util.List;

import com.chargebee.core.models.contact.Contact;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerContactsForCustomerParams;

/**
 * Immutable response object for CustomerContactsForCustomer operation. Contains paginated list
 * data.
 */
public final class CustomerContactsForCustomerResponse {

  private final List<CustomerContactsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final CustomerContactsForCustomerParams originalParams;

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
   * pagination (enables nextPage()).
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
