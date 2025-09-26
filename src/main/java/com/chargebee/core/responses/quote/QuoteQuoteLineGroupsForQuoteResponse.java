package com.chargebee.core.responses.quote;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.quoteLineGroup.QuoteLineGroup;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.QuoteService;
import com.chargebee.core.models.quote.params.QuoteQuoteLineGroupsForQuoteParams;

/**
 * Immutable response object for QuoteQuoteLineGroupsForQuote operation. Contains paginated list
 * data with auto-pagination support.
 */
public final class QuoteQuoteLineGroupsForQuoteResponse
    implements Iterable<QuoteQuoteLineGroupsForQuoteResponse.QuoteQuoteLineGroupsForQuoteItem> {

  private final List<QuoteQuoteLineGroupsForQuoteItem> list;

  private final String nextOffset;

  private final String quoteId;

  private final QuoteService service;
  private final QuoteQuoteLineGroupsForQuoteParams originalParams;
  private final boolean isAutoPaginate;

  private QuoteQuoteLineGroupsForQuoteResponse(
      List<QuoteQuoteLineGroupsForQuoteItem> list,
      String nextOffset,
      String quoteId,
      QuoteService service,
      QuoteQuoteLineGroupsForQuoteParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.quoteId = quoteId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private QuoteQuoteLineGroupsForQuoteResponse(
      List<QuoteQuoteLineGroupsForQuoteItem> list,
      String nextOffset,
      String quoteId,
      QuoteService service,
      QuoteQuoteLineGroupsForQuoteParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.quoteId = quoteId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into QuoteQuoteLineGroupsForQuoteResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static QuoteQuoteLineGroupsForQuoteResponse fromJson(String json) {
    try {

      List<QuoteQuoteLineGroupsForQuoteItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteQuoteLineGroupsForQuoteItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteQuoteLineGroupsForQuoteResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteQuoteLineGroupsForQuoteResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into QuoteQuoteLineGroupsForQuoteResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static QuoteQuoteLineGroupsForQuoteResponse fromJson(
      String json,
      QuoteService service,
      QuoteQuoteLineGroupsForQuoteParams originalParams,
      String quoteId) {
    try {

      List<QuoteQuoteLineGroupsForQuoteItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteQuoteLineGroupsForQuoteItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteQuoteLineGroupsForQuoteResponse(
          list, nextOffset, quoteId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteQuoteLineGroupsForQuoteResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<QuoteQuoteLineGroupsForQuoteItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<QuoteQuoteLineGroupsForQuoteItem> items() {
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
  public QuoteQuoteLineGroupsForQuoteResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    QuoteQuoteLineGroupsForQuoteParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.quoteLineGroupsForQuote(quoteId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public QuoteQuoteLineGroupsForQuoteResponse autoPaginate() {
    return new QuoteQuoteLineGroupsForQuoteResponse(
        list, nextOffset, quoteId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<QuoteQuoteLineGroupsForQuoteItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<QuoteQuoteLineGroupsForQuoteItem> {
    private QuoteQuoteLineGroupsForQuoteResponse currentPage =
        QuoteQuoteLineGroupsForQuoteResponse.this;
    private Iterator<QuoteQuoteLineGroupsForQuoteItem> currentIterator =
        currentPage.list.iterator();

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
    public QuoteQuoteLineGroupsForQuoteItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class QuoteQuoteLineGroupsForQuoteItem {

    private QuoteLineGroup quoteLineGroup;

    public QuoteLineGroup getQuoteLineGroup() {
      return quoteLineGroup;
    }

    public static QuoteQuoteLineGroupsForQuoteItem fromJson(String json) {
      QuoteQuoteLineGroupsForQuoteItem item = new QuoteQuoteLineGroupsForQuoteItem();

      String __quoteLineGroupJson = JsonUtil.getObject(json, "quote_line_group");
      if (__quoteLineGroupJson != null) {
        item.quoteLineGroup = QuoteLineGroup.fromJson(__quoteLineGroupJson);
      }

      return item;
    }
  }
}
