package com.chargebee.v4.models.customerEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.customerEntitlement.CustomerEntitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CustomerEntitlementService;
import com.chargebee.v4.models.customerEntitlement.params.CustomerEntitlementsForCustomerParams;

/**
 * Immutable response object for CustomerEntitlementsForCustomer operation. Contains paginated list
 * data.
 */
public final class CustomerEntitlementsForCustomerResponse {

  private final List<CustomerEntitlementCustomerEntitlementsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CustomerEntitlementService service;
  private final CustomerEntitlementsForCustomerParams originalParams;
  private final Response httpResponse;

  private CustomerEntitlementsForCustomerResponse(
      List<CustomerEntitlementCustomerEntitlementsForCustomerItem> list,
      String nextOffset,
      String customerId,
      CustomerEntitlementService service,
      CustomerEntitlementsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into CustomerEntitlementsForCustomerResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static CustomerEntitlementsForCustomerResponse fromJson(String json) {
    try {

      List<CustomerEntitlementCustomerEntitlementsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerEntitlementCustomerEntitlementsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerEntitlementsForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerEntitlementsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CustomerEntitlementsForCustomerResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static CustomerEntitlementsForCustomerResponse fromJson(
      String json,
      CustomerEntitlementService service,
      CustomerEntitlementsForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<CustomerEntitlementCustomerEntitlementsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CustomerEntitlementCustomerEntitlementsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CustomerEntitlementsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerEntitlementsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CustomerEntitlementCustomerEntitlementsForCustomerItem> getList() {
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
  public CustomerEntitlementsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CustomerEntitlementsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : CustomerEntitlementsForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.customerEntitlementsForCustomer(customerId, nextParams);
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

  public static class CustomerEntitlementCustomerEntitlementsForCustomerItem {

    private CustomerEntitlement customerEntitlement;

    public CustomerEntitlement getCustomerEntitlement() {
      return customerEntitlement;
    }

    public static CustomerEntitlementCustomerEntitlementsForCustomerItem fromJson(String json) {
      CustomerEntitlementCustomerEntitlementsForCustomerItem item =
          new CustomerEntitlementCustomerEntitlementsForCustomerItem();

      String __customerEntitlementJson = JsonUtil.getObject(json, "customer_entitlement");
      if (__customerEntitlementJson != null) {
        item.customerEntitlement = CustomerEntitlement.fromJson(__customerEntitlementJson);
      }

      return item;
    }
  }
}
