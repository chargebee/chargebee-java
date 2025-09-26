package com.chargebee.core.responses.currency;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.currency.Currency;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CurrencyService;
import com.chargebee.core.models.currency.params.CurrencyListParams;

/**
 * Immutable response object for CurrencyList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class CurrencyListResponse implements Iterable<CurrencyListResponse.CurrencyListItem> {

  private final List<CurrencyListItem> list;

  private final String nextOffset;

  private final CurrencyService service;
  private final CurrencyListParams originalParams;
  private final boolean isAutoPaginate;

  private CurrencyListResponse(
      List<CurrencyListItem> list,
      String nextOffset,
      CurrencyService service,
      CurrencyListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CurrencyListResponse(
      List<CurrencyListItem> list,
      String nextOffset,
      CurrencyService service,
      CurrencyListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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

      return new CurrencyListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CurrencyListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static CurrencyListResponse fromJson(
      String json, CurrencyService service, CurrencyListParams originalParams) {
    try {

      List<CurrencyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CurrencyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CurrencyListResponse(list, nextOffset, service, originalParams);
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
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CurrencyListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CurrencyListResponse autoPaginate() {
    return new CurrencyListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CurrencyListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CurrencyListItem> {
    private CurrencyListResponse currentPage = CurrencyListResponse.this;
    private Iterator<CurrencyListItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public CurrencyListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
