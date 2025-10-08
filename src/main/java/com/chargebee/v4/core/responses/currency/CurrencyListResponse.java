package com.chargebee.v4.core.responses.currency;

import java.util.List;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.CurrencyService;
import com.chargebee.v4.core.models.currency.params.CurrencyListParams;

/** Immutable response object for CurrencyList operation. Contains paginated list data. */
public final class CurrencyListResponse {

  private final List<CurrencyListItem> list;

  private final String nextOffset;

  private final CurrencyService service;
  private final CurrencyListParams originalParams;
  private final Response httpResponse;

  private CurrencyListResponse(
      List<CurrencyListItem> list,
      String nextOffset,
      CurrencyService service,
      CurrencyListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into CurrencyListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CurrencyListResponse fromJson(String json) {
    try {

      List<CurrencyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CurrencyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CurrencyListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CurrencyListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static CurrencyListResponse fromJson(
      String json,
      CurrencyService service,
      CurrencyListParams originalParams,
      Response httpResponse) {
    try {

      List<CurrencyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CurrencyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CurrencyListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CurrencyListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CurrencyListItem> items() {
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
  public CurrencyListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    CurrencyListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : CurrencyListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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

  public static class CurrencyListItem {

    private Currency currency;

    public Currency getCurrency() {
      return currency;
    }

    public static CurrencyListItem fromJson(String json) {
      CurrencyListItem item = new CurrencyListItem();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        item.currency = Currency.fromJson(__currencyJson);
      }

      return item;
    }
  }
}
