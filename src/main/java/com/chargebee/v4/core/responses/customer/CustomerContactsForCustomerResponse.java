package com.chargebee.v4.core.responses.customer;

import java.util.List;

import com.chargebee.v4.core.models.contact.Contact;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.CustomerService;
import com.chargebee.v4.core.models.customer.params.CustomerContactsForCustomerParams;

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
  private final Response httpResponse;

  private CustomerContactsForCustomerResponse(
      List<CustomerContactsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      CustomerContactsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new CustomerContactsForCustomerResponse(list, nextOffset, null, null, null, null);
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
      String customerId,
      Response httpResponse) {
    try {

      List<CustomerContactsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerContactsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerContactsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CustomerContactsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : CustomerContactsForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.contactsForCustomer(customerId, nextParams);
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
