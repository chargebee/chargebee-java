package com.chargebee.v4.models.priceRamp.responses;

import java.util.List;

import com.chargebee.v4.models.priceRamp.PriceRamp;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.PriceRampService;
import com.chargebee.v4.models.priceRamp.params.PriceRampListParams;

/** Immutable response object for PriceRampList operation. Contains paginated list data. */
public final class PriceRampListResponse {

  private final List<PriceRampListItem> list;

  private final String nextOffset;

  private final PriceRampService service;
  private final PriceRampListParams originalParams;
  private final Response httpResponse;

  private PriceRampListResponse(
      List<PriceRampListItem> list,
      String nextOffset,
      PriceRampService service,
      PriceRampListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PriceRampListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static PriceRampListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PriceRampListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), PriceRampListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new PriceRampListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceRampListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PriceRampListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static PriceRampListResponse fromJson(
      String json,
      PriceRampService service,
      PriceRampListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PriceRampListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), PriceRampListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new PriceRampListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceRampListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PriceRampListItem> getList() {
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
  public PriceRampListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    PriceRampListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : PriceRampListParams.builder())
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
    return "PriceRampListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PriceRampListResponse that = (PriceRampListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class PriceRampListItem {

    private PriceRamp priceRamp;

    public PriceRamp getPriceRamp() {
      return priceRamp;
    }

    public static PriceRampListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static PriceRampListItem fromJson(JsonObject jsonObj) {
      PriceRampListItem item = new PriceRampListItem();

      JsonObject __priceRampObj = JsonUtil.getJsonObject(jsonObj, "price_ramp");
      if (__priceRampObj != null) {
        item.priceRamp = PriceRamp.fromJson(__priceRampObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "PriceRampListItem{" + "priceRamp=" + priceRamp + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PriceRampListItem that = (PriceRampListItem) o;
      return java.util.Objects.equals(priceRamp, that.priceRamp);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(priceRamp);
    }
  }
}
