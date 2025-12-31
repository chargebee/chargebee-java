package com.chargebee.v4.models.customerEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.customerEntitlement.CustomerEntitlement;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CustomerEntitlementService;
import com.chargebee.v4.models.customerEntitlement.params.CustomerEntitlementEntitlementsForCustomerParams;

/**
 * Immutable response object for CustomerEntitlementEntitlementsForCustomer operation. Contains
 * paginated list data.
 */
public final class CustomerEntitlementEntitlementsForCustomerResponse {

  private final List<CustomerEntitlementEntitlementsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerEntitlementService service;
  private final CustomerEntitlementEntitlementsForCustomerParams originalParams;
  private final Response httpResponse;

  private CustomerEntitlementEntitlementsForCustomerResponse(
      List<CustomerEntitlementEntitlementsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerEntitlementService service,
      CustomerEntitlementEntitlementsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerEntitlementEntitlementsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerEntitlementEntitlementsForCustomerResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static CustomerEntitlementEntitlementsForCustomerResponse fromJson(
      String json,
      CustomerEntitlementService service,
      CustomerEntitlementEntitlementsForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<CustomerEntitlementEntitlementsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerEntitlementEntitlementsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerEntitlementEntitlementsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public CustomerEntitlementEntitlementsForCustomerResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CustomerEntitlementEntitlementsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : CustomerEntitlementEntitlementsForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.entitlementsForCustomer(customerId, nextParams);
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
    return "CustomerEntitlementEntitlementsForCustomerResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CustomerEntitlementEntitlementsForCustomerResponse that =
        (CustomerEntitlementEntitlementsForCustomerResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "CustomerEntitlementEntitlementsForCustomerItem{"
          + "customerEntitlement="
          + customerEntitlement
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CustomerEntitlementEntitlementsForCustomerItem that =
          (CustomerEntitlementEntitlementsForCustomerItem) o;
      return java.util.Objects.equals(customerEntitlement, that.customerEntitlement);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(customerEntitlement);
    }
  }
}
