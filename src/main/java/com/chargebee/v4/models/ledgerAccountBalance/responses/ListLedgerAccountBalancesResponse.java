package com.chargebee.v4.models.ledgerAccountBalance.responses;

import java.util.List;

import com.chargebee.v4.models.ledgerAccountBalance.LedgerAccountBalance;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.LedgerAccountBalanceService;
import com.chargebee.v4.models.ledgerAccountBalance.params.ListLedgerAccountBalancesParams;

/**
 * Immutable response object for ListLedgerAccountBalances operation. Contains paginated list data.
 */
public final class ListLedgerAccountBalancesResponse {

  private final List<LedgerAccountBalanceListLedgerAccountBalancesItem> list;

  private final String nextOffset;

  private final LedgerAccountBalanceService service;
  private final ListLedgerAccountBalancesParams originalParams;
  private final Response httpResponse;

  private ListLedgerAccountBalancesResponse(
      List<LedgerAccountBalanceListLedgerAccountBalancesItem> list,
      String nextOffset,
      LedgerAccountBalanceService service,
      ListLedgerAccountBalancesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListLedgerAccountBalancesResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static ListLedgerAccountBalancesResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<LedgerAccountBalanceListLedgerAccountBalancesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              LedgerAccountBalanceListLedgerAccountBalancesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListLedgerAccountBalancesResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListLedgerAccountBalancesResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListLedgerAccountBalancesResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ListLedgerAccountBalancesResponse fromJson(
      String json,
      LedgerAccountBalanceService service,
      ListLedgerAccountBalancesParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<LedgerAccountBalanceListLedgerAccountBalancesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              LedgerAccountBalanceListLedgerAccountBalancesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListLedgerAccountBalancesResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListLedgerAccountBalancesResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<LedgerAccountBalanceListLedgerAccountBalancesItem> getList() {
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
  public ListLedgerAccountBalancesResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListLedgerAccountBalancesParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ListLedgerAccountBalancesParams.builder())
            .offset(nextOffset)
            .build();

    return service.listLedgerAccountBalances(nextParams);
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
    return "ListLedgerAccountBalancesResponse{"
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

    ListLedgerAccountBalancesResponse that = (ListLedgerAccountBalancesResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class LedgerAccountBalanceListLedgerAccountBalancesItem {

    private LedgerAccountBalance ledgerAccountBalance;

    public LedgerAccountBalance getLedgerAccountBalance() {
      return ledgerAccountBalance;
    }

    public static LedgerAccountBalanceListLedgerAccountBalancesItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static LedgerAccountBalanceListLedgerAccountBalancesItem fromJson(JsonObject jsonObj) {
      LedgerAccountBalanceListLedgerAccountBalancesItem item =
          new LedgerAccountBalanceListLedgerAccountBalancesItem();

      JsonObject __ledgerAccountBalanceObj =
          JsonUtil.getJsonObject(jsonObj, "ledger_account_balance");
      if (__ledgerAccountBalanceObj != null) {
        item.ledgerAccountBalance = LedgerAccountBalance.fromJson(__ledgerAccountBalanceObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "LedgerAccountBalanceListLedgerAccountBalancesItem{"
          + "ledgerAccountBalance="
          + ledgerAccountBalance
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      LedgerAccountBalanceListLedgerAccountBalancesItem that =
          (LedgerAccountBalanceListLedgerAccountBalancesItem) o;
      return java.util.Objects.equals(ledgerAccountBalance, that.ledgerAccountBalance);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(ledgerAccountBalance);
    }
  }
}
