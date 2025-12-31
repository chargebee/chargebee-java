package com.chargebee.v4.models.customer.responses;

import java.util.List;

import com.chargebee.v4.models.contact.Contact;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CustomerService;
import com.chargebee.v4.models.customer.params.ContactsForCustomerParams;

/** Immutable response object for ContactsForCustomer operation. Contains paginated list data. */
public final class ContactsForCustomerResponse {

  private final List<CustomerContactsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerService service;
  private final ContactsForCustomerParams originalParams;
  private final Response httpResponse;

  private ContactsForCustomerResponse(
      List<CustomerContactsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerService service,
      ContactsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ContactsForCustomerResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static ContactsForCustomerResponse fromJson(String json) {
    try {

      List<CustomerContactsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerContactsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ContactsForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ContactsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ContactsForCustomerResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ContactsForCustomerResponse fromJson(
      String json,
      CustomerService service,
      ContactsForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<CustomerContactsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerContactsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ContactsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ContactsForCustomerResponse from JSON", e);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public ContactsForCustomerResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ContactsForCustomerParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ContactsForCustomerParams.builder())
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

  @Override
  public String toString() {
    return "ContactsForCustomerResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsForCustomerResponse that = (ContactsForCustomerResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "CustomerContactsForCustomerItem{" + "contact=" + contact + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CustomerContactsForCustomerItem that = (CustomerContactsForCustomerItem) o;
      return java.util.Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(contact);
    }
  }
}
