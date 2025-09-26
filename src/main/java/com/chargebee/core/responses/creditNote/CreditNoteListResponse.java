package com.chargebee.core.responses.creditNote;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CreditNoteService;
import com.chargebee.core.models.creditNote.params.CreditNoteListParams;

/**
 * Immutable response object for CreditNoteList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class CreditNoteListResponse
    implements Iterable<CreditNoteListResponse.CreditNoteListItem> {

  private final List<CreditNoteListItem> list;

  private final String nextOffset;

  private final CreditNoteService service;
  private final CreditNoteListParams originalParams;
  private final boolean isAutoPaginate;

  private CreditNoteListResponse(
      List<CreditNoteListItem> list,
      String nextOffset,
      CreditNoteService service,
      CreditNoteListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CreditNoteListResponse(
      List<CreditNoteListItem> list,
      String nextOffset,
      CreditNoteService service,
      CreditNoteListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into CreditNoteListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CreditNoteListResponse fromJson(String json) {
    try {

      List<CreditNoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CreditNoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CreditNoteListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CreditNoteListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static CreditNoteListResponse fromJson(
      String json, CreditNoteService service, CreditNoteListParams originalParams) {
    try {

      List<CreditNoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CreditNoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CreditNoteListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CreditNoteListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CreditNoteListItem> items() {
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
  public CreditNoteListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CreditNoteListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CreditNoteListResponse autoPaginate() {
    return new CreditNoteListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CreditNoteListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CreditNoteListItem> {
    private CreditNoteListResponse currentPage = CreditNoteListResponse.this;
    private Iterator<CreditNoteListItem> currentIterator = currentPage.list.iterator();

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
    public CreditNoteListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class CreditNoteListItem {

    private CreditNote creditNote;

    public CreditNote getCreditNote() {
      return creditNote;
    }

    public static CreditNoteListItem fromJson(String json) {
      CreditNoteListItem item = new CreditNoteListItem();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        item.creditNote = CreditNote.fromJson(__creditNoteJson);
      }

      return item;
    }
  }
}
