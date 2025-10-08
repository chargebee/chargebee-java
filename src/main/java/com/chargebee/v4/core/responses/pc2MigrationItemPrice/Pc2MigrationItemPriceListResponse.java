package com.chargebee.v4.core.responses.pc2MigrationItemPrice;

import java.util.List;
import java.util.ArrayList;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.Pc2MigrationItemPriceService;
import com.chargebee.v4.core.models.pc2MigrationItemPrice.params.Pc2MigrationItemPriceListParams;

/**
 * Immutable response object for Pc2MigrationItemPriceList operation. Contains paginated list data.
 */
public final class Pc2MigrationItemPriceListResponse {

  private final List<Object> list;

  private final String nextOffset;

  private final Pc2MigrationItemPriceService service;
  private final Pc2MigrationItemPriceListParams originalParams;
  private final Response httpResponse;

  private Pc2MigrationItemPriceListResponse(
      List<Object> list,
      String nextOffset,
      Pc2MigrationItemPriceService service,
      Pc2MigrationItemPriceListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into Pc2MigrationItemPriceListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static Pc2MigrationItemPriceListResponse fromJson(String json) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemPriceListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemPriceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into Pc2MigrationItemPriceListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static Pc2MigrationItemPriceListResponse fromJson(
      String json,
      Pc2MigrationItemPriceService service,
      Pc2MigrationItemPriceListParams originalParams,
      Response httpResponse) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemPriceListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemPriceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<Object> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<Object> items() {
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
  public Pc2MigrationItemPriceListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    Pc2MigrationItemPriceListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : Pc2MigrationItemPriceListParams.builder())
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
}
