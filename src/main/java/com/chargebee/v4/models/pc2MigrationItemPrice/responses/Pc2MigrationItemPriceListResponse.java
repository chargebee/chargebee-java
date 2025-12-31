package com.chargebee.v4.models.pc2MigrationItemPrice.responses;

import java.util.List;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.Pc2MigrationItemPriceService;
import com.chargebee.v4.models.pc2MigrationItemPrice.params.Pc2MigrationItemPriceListParams;

/**
 * Immutable response object for Pc2MigrationItemPriceList operation. Contains paginated list data.
 */
public final class Pc2MigrationItemPriceListResponse {

  private final List<java.util.Map<String, Object>> list;

  private final String nextOffset;

  private final Pc2MigrationItemPriceService service;
  private final Pc2MigrationItemPriceListParams originalParams;
  private final Response httpResponse;

  private Pc2MigrationItemPriceListResponse(
      List<java.util.Map<String, Object>> list,
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

      List<java.util.Map<String, Object>> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(JsonUtil::parseJsonObjectToMap)
              .collect(java.util.stream.Collectors.toList());

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

      List<java.util.Map<String, Object>> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(JsonUtil::parseJsonObjectToMap)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemPriceListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemPriceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<java.util.Map<String, Object>> getList() {
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
  public Pc2MigrationItemPriceListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

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

  @Override
  public String toString() {
    return "Pc2MigrationItemPriceListResponse{"
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
    Pc2MigrationItemPriceListResponse that = (Pc2MigrationItemPriceListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }
}
