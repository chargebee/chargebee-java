package com.chargebee.v4.models.creditNote.responses;

import java.util.List;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CreditNoteService;
import com.chargebee.v4.models.creditNote.params.CreditNoteListParams;

/** Immutable response object for CreditNoteList operation. Contains paginated list data. */
public final class CreditNoteListResponse {

  private final List<CreditNoteListItem> list;

  private final String nextOffset;

  private final CreditNoteService service;
  private final CreditNoteListParams originalParams;
  private final Response httpResponse;

  private CreditNoteListResponse(
      List<CreditNoteListItem> list,
      String nextOffset,
      CreditNoteService service,
      CreditNoteListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into CreditNoteListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CreditNoteListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CreditNoteListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), CreditNoteListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new CreditNoteListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CreditNoteListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static CreditNoteListResponse fromJson(
      String json,
      CreditNoteService service,
      CreditNoteListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CreditNoteListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), CreditNoteListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new CreditNoteListResponse(list, nextOffset, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public CreditNoteListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CreditNoteListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : CreditNoteListParams.builder())
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
    return "CreditNoteListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreditNoteListResponse that = (CreditNoteListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class CreditNoteListItem {

    private CreditNote creditNote;

    public CreditNote getCreditNote() {
      return creditNote;
    }

    public static CreditNoteListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static CreditNoteListItem fromJson(JsonObject jsonObj) {
      CreditNoteListItem item = new CreditNoteListItem();

      JsonObject __creditNoteObj = JsonUtil.getJsonObject(jsonObj, "credit_note");
      if (__creditNoteObj != null) {
        item.creditNote = CreditNote.fromJson(__creditNoteObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "CreditNoteListItem{" + "creditNote=" + creditNote + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CreditNoteListItem that = (CreditNoteListItem) o;
      return java.util.Objects.equals(creditNote, that.creditNote);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(creditNote);
    }
  }
}
