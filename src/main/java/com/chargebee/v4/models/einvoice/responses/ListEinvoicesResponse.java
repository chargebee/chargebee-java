package com.chargebee.v4.models.einvoice.responses;

import java.util.List;

import com.chargebee.v4.models.einvoice.Einvoice;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.EinvoiceService;
import com.chargebee.v4.models.einvoice.params.ListEinvoicesParams;

/** Immutable response object for ListEinvoices operation. Contains paginated list data. */
public final class ListEinvoicesResponse {

  private final List<EinvoiceListEinvoicesItem> list;

  private final String nextOffset;

  private final EinvoiceService service;
  private final ListEinvoicesParams originalParams;
  private final Response httpResponse;

  private ListEinvoicesResponse(
      List<EinvoiceListEinvoicesItem> list,
      String nextOffset,
      EinvoiceService service,
      ListEinvoicesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListEinvoicesResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ListEinvoicesResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<EinvoiceListEinvoicesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), EinvoiceListEinvoicesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListEinvoicesResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListEinvoicesResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListEinvoicesResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ListEinvoicesResponse fromJson(
      String json,
      EinvoiceService service,
      ListEinvoicesParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<EinvoiceListEinvoicesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), EinvoiceListEinvoicesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListEinvoicesResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListEinvoicesResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EinvoiceListEinvoicesItem> getList() {
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
  public ListEinvoicesResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListEinvoicesParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ListEinvoicesParams.builder())
            .offset(nextOffset)
            .build();

    return service.listEinvoices(nextParams);
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
    return "ListEinvoicesResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListEinvoicesResponse that = (ListEinvoicesResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class EinvoiceListEinvoicesItem {

    private Einvoice einvoice;

    public Einvoice getEinvoice() {
      return einvoice;
    }

    public static EinvoiceListEinvoicesItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static EinvoiceListEinvoicesItem fromJson(JsonObject jsonObj) {
      EinvoiceListEinvoicesItem item = new EinvoiceListEinvoicesItem();

      JsonObject __einvoiceObj = JsonUtil.getJsonObject(jsonObj, "einvoice");
      if (__einvoiceObj != null) {
        item.einvoice = Einvoice.fromJson(__einvoiceObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "EinvoiceListEinvoicesItem{" + "einvoice=" + einvoice + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      EinvoiceListEinvoicesItem that = (EinvoiceListEinvoicesItem) o;
      return java.util.Objects.equals(einvoice, that.einvoice);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(einvoice);
    }
  }
}
