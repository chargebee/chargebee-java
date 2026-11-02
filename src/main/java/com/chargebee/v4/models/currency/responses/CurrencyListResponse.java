package com.chargebee.v4.models.currency.responses;

import java.util.List;

import com.chargebee.v4.models.currency.Currency;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CurrencyService;
import com.chargebee.v4.models.currency.params.CurrencyListParams;

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
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CurrencyListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), CurrencyListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

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
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CurrencyListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), CurrencyListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public CurrencyListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

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

  @Override
  public String toString() {
    return "CurrencyListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CurrencyListResponse that = (CurrencyListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class CurrencyListItem {

    private Currency currency;

    public Currency getCurrency() {
      return currency;
    }

    public static CurrencyListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static CurrencyListItem fromJson(JsonObject jsonObj) {
      CurrencyListItem item = new CurrencyListItem();

      JsonObject __currencyObj = JsonUtil.getJsonObject(jsonObj, "currency");
      if (__currencyObj != null) {
        item.currency = Currency.fromJson(__currencyObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "CurrencyListItem{" + "currency=" + currency + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CurrencyListItem that = (CurrencyListItem) o;
      return java.util.Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(currency);
    }
  }
}
