package com.chargebee.v4.models.hostedPage.responses;

import java.util.List;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.HostedPageService;
import com.chargebee.v4.models.hostedPage.params.HostedPageListParams;

/** Immutable response object for HostedPageList operation. Contains paginated list data. */
public final class HostedPageListResponse {

  private final List<HostedPageListItem> list;

  private final String nextOffset;

  private final HostedPageService service;
  private final HostedPageListParams originalParams;
  private final Response httpResponse;

  private HostedPageListResponse(
      List<HostedPageListItem> list,
      String nextOffset,
      HostedPageService service,
      HostedPageListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into HostedPageListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static HostedPageListResponse fromJson(String json) {
    try {

      List<HostedPageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(HostedPageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HostedPageListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into HostedPageListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static HostedPageListResponse fromJson(
      String json,
      HostedPageService service,
      HostedPageListParams originalParams,
      Response httpResponse) {
    try {

      List<HostedPageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(HostedPageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HostedPageListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<HostedPageListItem> getList() {
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
  public HostedPageListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    HostedPageListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : HostedPageListParams.builder())
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
    return "HostedPageListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HostedPageListResponse that = (HostedPageListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class HostedPageListItem {

    private HostedPage hostedPage;

    public HostedPage getHostedPage() {
      return hostedPage;
    }

    public static HostedPageListItem fromJson(String json) {
      HostedPageListItem item = new HostedPageListItem();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        item.hostedPage = HostedPage.fromJson(__hostedPageJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "HostedPageListItem{" + "hostedPage=" + hostedPage + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      HostedPageListItem that = (HostedPageListItem) o;
      return java.util.Objects.equals(hostedPage, that.hostedPage);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(hostedPage);
    }
  }
}
