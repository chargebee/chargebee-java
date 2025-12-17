package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import java.util.List;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.Pc2MigrationItemFamilyService;
import com.chargebee.v4.models.pc2MigrationItemFamily.params.Pc2MigrationItemFamilyListParams;

/**
 * Immutable response object for Pc2MigrationItemFamilyList operation. Contains paginated list data.
 */
public final class Pc2MigrationItemFamilyListResponse {

  private final List<java.util.Map<String, Object>> list;

  private final String nextOffset;

  private final Pc2MigrationItemFamilyService service;
  private final Pc2MigrationItemFamilyListParams originalParams;
  private final Response httpResponse;

  private Pc2MigrationItemFamilyListResponse(
      List<java.util.Map<String, Object>> list,
      String nextOffset,
      Pc2MigrationItemFamilyService service,
      Pc2MigrationItemFamilyListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into Pc2MigrationItemFamilyListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static Pc2MigrationItemFamilyListResponse fromJson(String json) {
    try {

      List<java.util.Map<String, Object>> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(JsonUtil::parseJsonObjectToMap)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemFamilyListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemFamilyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into Pc2MigrationItemFamilyListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static Pc2MigrationItemFamilyListResponse fromJson(
      String json,
      Pc2MigrationItemFamilyService service,
      Pc2MigrationItemFamilyListParams originalParams,
      Response httpResponse) {
    try {

      List<java.util.Map<String, Object>> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(JsonUtil::parseJsonObjectToMap)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemFamilyListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemFamilyListResponse from JSON", e);
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
   * @throws Exception if unable to fetch next page
   */
  public Pc2MigrationItemFamilyListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    Pc2MigrationItemFamilyListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : Pc2MigrationItemFamilyListParams.builder())
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
