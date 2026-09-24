package com.chargebee.v4.models.dispute.responses;

import java.util.List;

import com.chargebee.v4.models.dispute.Dispute;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.DisputeService;
import com.chargebee.v4.models.dispute.params.DisputeListParams;

/** Immutable response object for DisputeList operation. Contains paginated list data. */
public final class DisputeListResponse {

  private final List<DisputeListItem> list;

  private final String nextOffset;

  private final DisputeService service;
  private final DisputeListParams originalParams;
  private final Response httpResponse;

  private DisputeListResponse(
      List<DisputeListItem> list,
      String nextOffset,
      DisputeService service,
      DisputeListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into DisputeListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static DisputeListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DisputeListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), DisputeListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DisputeListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DisputeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into DisputeListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static DisputeListResponse fromJson(
      String json,
      DisputeService service,
      DisputeListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<DisputeListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), DisputeListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new DisputeListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DisputeListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<DisputeListItem> getList() {
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
  public DisputeListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    DisputeListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : DisputeListParams.builder())
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
    return "DisputeListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DisputeListResponse that = (DisputeListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class DisputeListItem {

    private Dispute dispute;

    public Dispute getDispute() {
      return dispute;
    }

    public static DisputeListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static DisputeListItem fromJson(JsonObject jsonObj) {
      DisputeListItem item = new DisputeListItem();

      JsonObject __disputeObj = JsonUtil.getJsonObject(jsonObj, "dispute");
      if (__disputeObj != null) {
        item.dispute = Dispute.fromJson(__disputeObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "DisputeListItem{" + "dispute=" + dispute + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DisputeListItem that = (DisputeListItem) o;
      return java.util.Objects.equals(dispute, that.dispute);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(dispute);
    }
  }
}
