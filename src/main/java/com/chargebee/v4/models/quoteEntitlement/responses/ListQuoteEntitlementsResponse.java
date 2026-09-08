package com.chargebee.v4.models.quoteEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.quoteEntitlement.QuoteEntitlement;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.QuoteEntitlementService;
import com.chargebee.v4.models.quoteEntitlement.params.ListQuoteEntitlementsParams;

/** Immutable response object for ListQuoteEntitlements operation. Contains paginated list data. */
public final class ListQuoteEntitlementsResponse {

  private final List<QuoteEntitlementListQuoteEntitlementsItem> list;

  private final String nextOffset;

  private final String quoteId;

  private final QuoteEntitlementService service;
  private final ListQuoteEntitlementsParams originalParams;
  private final Response httpResponse;

  private ListQuoteEntitlementsResponse(
      List<QuoteEntitlementListQuoteEntitlementsItem> list,
      String nextOffset,
      String quoteId,
      QuoteEntitlementService service,
      ListQuoteEntitlementsParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.quoteId = quoteId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListQuoteEntitlementsResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static ListQuoteEntitlementsResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<QuoteEntitlementListQuoteEntitlementsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              QuoteEntitlementListQuoteEntitlementsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListQuoteEntitlementsResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListQuoteEntitlementsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListQuoteEntitlementsResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ListQuoteEntitlementsResponse fromJson(
      String json,
      QuoteEntitlementService service,
      ListQuoteEntitlementsParams originalParams,
      String quoteId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<QuoteEntitlementListQuoteEntitlementsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              QuoteEntitlementListQuoteEntitlementsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListQuoteEntitlementsResponse(
          list, nextOffset, quoteId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListQuoteEntitlementsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<QuoteEntitlementListQuoteEntitlementsItem> getList() {
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
  public ListQuoteEntitlementsResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListQuoteEntitlementsParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ListQuoteEntitlementsParams.builder())
            .offset(nextOffset)
            .build();

    return service.listQuoteEntitlements(quoteId, nextParams);
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
    return "ListQuoteEntitlementsResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListQuoteEntitlementsResponse that = (ListQuoteEntitlementsResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class QuoteEntitlementListQuoteEntitlementsItem {

    private QuoteEntitlement quoteEntitlement;

    public QuoteEntitlement getQuoteEntitlement() {
      return quoteEntitlement;
    }

    public static QuoteEntitlementListQuoteEntitlementsItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static QuoteEntitlementListQuoteEntitlementsItem fromJson(JsonObject jsonObj) {
      QuoteEntitlementListQuoteEntitlementsItem item =
          new QuoteEntitlementListQuoteEntitlementsItem();

      JsonObject __quoteEntitlementObj = JsonUtil.getJsonObject(jsonObj, "quote_entitlement");
      if (__quoteEntitlementObj != null) {
        item.quoteEntitlement = QuoteEntitlement.fromJson(__quoteEntitlementObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "QuoteEntitlementListQuoteEntitlementsItem{"
          + "quoteEntitlement="
          + quoteEntitlement
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      QuoteEntitlementListQuoteEntitlementsItem that =
          (QuoteEntitlementListQuoteEntitlementsItem) o;
      return java.util.Objects.equals(quoteEntitlement, that.quoteEntitlement);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(quoteEntitlement);
    }
  }
}
