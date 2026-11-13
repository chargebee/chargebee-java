package com.chargebee.v4.models.draftRequest.responses;

import java.util.List;

import com.chargebee.v4.models.citation.Citation;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.DraftRequestService;
import com.chargebee.v4.models.draftRequest.params.DraftRequestListCitationsParams;

/**
 * Immutable response object for DraftRequestListCitations operation. Contains paginated list data.
 */
public final class DraftRequestListCitationsResponse {

  private final List<DraftRequestListCitationsItem> list;

  private final String nextOffset;

  private final String draftRequestId;

  private final DraftRequestService service;
  private final DraftRequestListCitationsParams originalParams;
  private final Response httpResponse;

  private DraftRequestListCitationsResponse(
      List<DraftRequestListCitationsItem> list,
      String nextOffset,
      String draftRequestId,
      DraftRequestService service,
      DraftRequestListCitationsParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.draftRequestId = draftRequestId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into DraftRequestListCitationsResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static DraftRequestListCitationsResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DraftRequestListCitationsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), DraftRequestListCitationsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DraftRequestListCitationsResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestListCitationsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into DraftRequestListCitationsResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static DraftRequestListCitationsResponse fromJson(
      String json,
      DraftRequestService service,
      DraftRequestListCitationsParams originalParams,
      String draftRequestId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DraftRequestListCitationsItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), DraftRequestListCitationsItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DraftRequestListCitationsResponse(
          list, nextOffset, draftRequestId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestListCitationsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<DraftRequestListCitationsItem> getList() {
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
  public DraftRequestListCitationsResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    DraftRequestListCitationsParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : DraftRequestListCitationsParams.builder())
            .offset(nextOffset)
            .build();

    return service.listCitations(draftRequestId, nextParams);
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
    return "DraftRequestListCitationsResponse{"
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

    DraftRequestListCitationsResponse that = (DraftRequestListCitationsResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class DraftRequestListCitationsItem {

    private Citation citation;

    public Citation getCitation() {
      return citation;
    }

    public static DraftRequestListCitationsItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static DraftRequestListCitationsItem fromJson(JsonObject jsonObj) {
      DraftRequestListCitationsItem item = new DraftRequestListCitationsItem();

      JsonObject __citationObj = JsonUtil.getJsonObject(jsonObj, "citation");
      if (__citationObj != null) {
        item.citation = Citation.fromJson(__citationObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "DraftRequestListCitationsItem{" + "citation=" + citation + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DraftRequestListCitationsItem that = (DraftRequestListCitationsItem) o;
      return java.util.Objects.equals(citation, that.citation);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(citation);
    }
  }
}
