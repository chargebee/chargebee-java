package com.chargebee.v4.models.siteMigrationDetail.responses;

import java.util.List;

import com.chargebee.v4.models.siteMigrationDetail.SiteMigrationDetail;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.SiteMigrationDetailService;
import com.chargebee.v4.models.siteMigrationDetail.params.SiteMigrationDetailListParams;

/**
 * Immutable response object for SiteMigrationDetailList operation. Contains paginated list data.
 */
public final class SiteMigrationDetailListResponse {

  private final List<SiteMigrationDetailListItem> list;

  private final String nextOffset;

  private final SiteMigrationDetailService service;
  private final SiteMigrationDetailListParams originalParams;
  private final Response httpResponse;

  private SiteMigrationDetailListResponse(
      List<SiteMigrationDetailListItem> list,
      String nextOffset,
      SiteMigrationDetailService service,
      SiteMigrationDetailListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into SiteMigrationDetailListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static SiteMigrationDetailListResponse fromJson(String json) {
    try {

      List<SiteMigrationDetailListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SiteMigrationDetailListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SiteMigrationDetailListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SiteMigrationDetailListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SiteMigrationDetailListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static SiteMigrationDetailListResponse fromJson(
      String json,
      SiteMigrationDetailService service,
      SiteMigrationDetailListParams originalParams,
      Response httpResponse) {
    try {

      List<SiteMigrationDetailListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SiteMigrationDetailListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SiteMigrationDetailListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SiteMigrationDetailListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SiteMigrationDetailListItem> getList() {
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
  public SiteMigrationDetailListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    SiteMigrationDetailListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : SiteMigrationDetailListParams.builder())
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
    return "SiteMigrationDetailListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SiteMigrationDetailListResponse that = (SiteMigrationDetailListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class SiteMigrationDetailListItem {

    private SiteMigrationDetail siteMigrationDetail;

    public SiteMigrationDetail getSiteMigrationDetail() {
      return siteMigrationDetail;
    }

    public static SiteMigrationDetailListItem fromJson(String json) {
      SiteMigrationDetailListItem item = new SiteMigrationDetailListItem();

      String __siteMigrationDetailJson = JsonUtil.getObject(json, "site_migration_detail");
      if (__siteMigrationDetailJson != null) {
        item.siteMigrationDetail = SiteMigrationDetail.fromJson(__siteMigrationDetailJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "SiteMigrationDetailListItem{" + "siteMigrationDetail=" + siteMigrationDetail + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      SiteMigrationDetailListItem that = (SiteMigrationDetailListItem) o;
      return java.util.Objects.equals(siteMigrationDetail, that.siteMigrationDetail);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(siteMigrationDetail);
    }
  }
}
