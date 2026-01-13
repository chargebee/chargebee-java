package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.contractTerm.ContractTerm;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.SubscriptionService;
import com.chargebee.v4.models.subscription.params.ContractTermsForSubscriptionParams;

/**
 * Immutable response object for ContractTermsForSubscription operation. Contains paginated list
 * data.
 */
public final class ContractTermsForSubscriptionResponse {

  private final List<SubscriptionContractTermsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionService service;
  private final ContractTermsForSubscriptionParams originalParams;
  private final Response httpResponse;

  private ContractTermsForSubscriptionResponse(
      List<SubscriptionContractTermsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionService service,
      ContractTermsForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ContractTermsForSubscriptionResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static ContractTermsForSubscriptionResponse fromJson(String json) {
    try {

      List<SubscriptionContractTermsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionContractTermsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ContractTermsForSubscriptionResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ContractTermsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ContractTermsForSubscriptionResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ContractTermsForSubscriptionResponse fromJson(
      String json,
      SubscriptionService service,
      ContractTermsForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<SubscriptionContractTermsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionContractTermsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ContractTermsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ContractTermsForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionContractTermsForSubscriptionItem> getList() {
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
  public ContractTermsForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ContractTermsForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ContractTermsForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.contractTermsForSubscription(subscriptionId, nextParams);
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
    return "ContractTermsForSubscriptionResponse{"
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

    ContractTermsForSubscriptionResponse that = (ContractTermsForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class SubscriptionContractTermsForSubscriptionItem {

    private ContractTerm contractTerm;

    public ContractTerm getContractTerm() {
      return contractTerm;
    }

    public static SubscriptionContractTermsForSubscriptionItem fromJson(String json) {
      SubscriptionContractTermsForSubscriptionItem item =
          new SubscriptionContractTermsForSubscriptionItem();

      String __contractTermJson = JsonUtil.getObject(json, "contract_term");
      if (__contractTermJson != null) {
        item.contractTerm = ContractTerm.fromJson(__contractTermJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "SubscriptionContractTermsForSubscriptionItem{" + "contractTerm=" + contractTerm + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      SubscriptionContractTermsForSubscriptionItem that =
          (SubscriptionContractTermsForSubscriptionItem) o;
      return java.util.Objects.equals(contractTerm, that.contractTerm);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(contractTerm);
    }
  }
}
