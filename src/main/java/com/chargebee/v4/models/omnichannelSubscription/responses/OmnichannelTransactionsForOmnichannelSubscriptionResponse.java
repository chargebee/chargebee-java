package com.chargebee.v4.models.omnichannelSubscription.responses;

import java.util.List;

import com.chargebee.v4.models.omnichannelTransaction.OmnichannelTransaction;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.OmnichannelSubscriptionService;
import com.chargebee.v4.models.omnichannelSubscription.params.OmnichannelTransactionsForOmnichannelSubscriptionParams;

/**
 * Immutable response object for OmnichannelTransactionsForOmnichannelSubscription operation.
 * Contains paginated list data.
 */
public final class OmnichannelTransactionsForOmnichannelSubscriptionResponse {

  private final List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem>
      list;

  private final String nextOffset;

  private final String omnichannelSubscriptionId;

  private final OmnichannelSubscriptionService service;
  private final OmnichannelTransactionsForOmnichannelSubscriptionParams originalParams;
  private final Response httpResponse;

  private OmnichannelTransactionsForOmnichannelSubscriptionResponse(
      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list,
      String nextOffset,
      String omnichannelSubscriptionId,
      OmnichannelSubscriptionService service,
      OmnichannelTransactionsForOmnichannelSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionId = omnichannelSubscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into OmnichannelTransactionsForOmnichannelSubscriptionResponse object (no
   * service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelTransactionsForOmnichannelSubscriptionResponse fromJson(String json) {
    try {

      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(
                  OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
                      ::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelTransactionsForOmnichannelSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelTransactionsForOmnichannelSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OmnichannelTransactionsForOmnichannelSubscriptionResponse object with
   * service context for pagination (enables nextPage()).
   */
  public static OmnichannelTransactionsForOmnichannelSubscriptionResponse fromJson(
      String json,
      OmnichannelSubscriptionService service,
      OmnichannelTransactionsForOmnichannelSubscriptionParams originalParams,
      String omnichannelSubscriptionId,
      Response httpResponse) {
    try {

      List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(
                  OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
                      ::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelTransactionsForOmnichannelSubscriptionResponse(
          list, nextOffset, omnichannelSubscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelTransactionsForOmnichannelSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem>
      getList() {
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
  public OmnichannelTransactionsForOmnichannelSubscriptionResponse nextPage()
      throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OmnichannelTransactionsForOmnichannelSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OmnichannelTransactionsForOmnichannelSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.omnichannelTransactionsForOmnichannelSubscription(
        omnichannelSubscriptionId, nextParams);
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
    return "OmnichannelTransactionsForOmnichannelSubscriptionResponse{"
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

    OmnichannelTransactionsForOmnichannelSubscriptionResponse that =
        (OmnichannelTransactionsForOmnichannelSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem {

    private OmnichannelTransaction omnichannelTransaction;

    public OmnichannelTransaction getOmnichannelTransaction() {
      return omnichannelTransaction;
    }

    public static OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem
        fromJson(String json) {
      OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem item =
          new OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem();

      String __omnichannelTransactionJson = JsonUtil.getObject(json, "omnichannel_transaction");
      if (__omnichannelTransactionJson != null) {
        item.omnichannelTransaction = OmnichannelTransaction.fromJson(__omnichannelTransactionJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem{"
          + "omnichannelTransaction="
          + omnichannelTransaction
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem that =
          (OmnichannelSubscriptionOmnichannelTransactionsForOmnichannelSubscriptionItem) o;
      return java.util.Objects.equals(omnichannelTransaction, that.omnichannelTransaction);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(omnichannelTransaction);
    }
  }
}
