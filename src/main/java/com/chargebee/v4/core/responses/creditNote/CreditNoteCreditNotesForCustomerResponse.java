package com.chargebee.v4.core.responses.creditNote;

import java.util.List;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.CreditNoteService;
import com.chargebee.v4.core.models.creditNote.params.CreditNoteCreditNotesForCustomerParams;

/**
 * Immutable response object for CreditNoteCreditNotesForCustomer operation. Contains paginated list
 * data.
 */
public final class CreditNoteCreditNotesForCustomerResponse {

  private final List<CreditNoteCreditNotesForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final CreditNoteService service;
  private final CreditNoteCreditNotesForCustomerParams originalParams;
  private final Response httpResponse;

  private CreditNoteCreditNotesForCustomerResponse(
      List<CreditNoteCreditNotesForCustomerItem> list,
      String nextOffset,
      String customerId,
      CreditNoteService service,
      CreditNoteCreditNotesForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into CreditNoteCreditNotesForCustomerResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static CreditNoteCreditNotesForCustomerResponse fromJson(String json) {
    try {

      List<CreditNoteCreditNotesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CreditNoteCreditNotesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CreditNoteCreditNotesForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CreditNoteCreditNotesForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CreditNoteCreditNotesForCustomerResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static CreditNoteCreditNotesForCustomerResponse fromJson(
      String json,
      CreditNoteService service,
      CreditNoteCreditNotesForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<CreditNoteCreditNotesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CreditNoteCreditNotesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CreditNoteCreditNotesForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CreditNoteCreditNotesForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CreditNoteCreditNotesForCustomerItem> getList() {
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
   * @throws Exception if unable to fetch next page
   */
  public CreditNoteCreditNotesForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CreditNoteCreditNotesForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : CreditNoteCreditNotesForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.creditNotesForCustomer(customerId, nextParams);
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

  public static class CreditNoteCreditNotesForCustomerItem {

    private CreditNote creditNote;

    public CreditNote getCreditNote() {
      return creditNote;
    }

    public static CreditNoteCreditNotesForCustomerItem fromJson(String json) {
      CreditNoteCreditNotesForCustomerItem item = new CreditNoteCreditNotesForCustomerItem();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        item.creditNote = CreditNote.fromJson(__creditNoteJson);
      }

      return item;
    }
  }
}
