package com.chargebee.v4.models.omnichannelOneTimeOrder.responses;

import java.util.List;

import com.chargebee.v4.models.omnichannelOneTimeOrder.OmnichannelOneTimeOrder;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.OmnichannelOneTimeOrderService;
import com.chargebee.v4.models.omnichannelOneTimeOrder.params.OmnichannelOneTimeOrderListParams;

/**
 * Immutable response object for OmnichannelOneTimeOrderList operation. Contains paginated list
 * data.
 */
public final class OmnichannelOneTimeOrderListResponse {

  private final List<OmnichannelOneTimeOrderListItem> list;

  private final String nextOffset;

  private final OmnichannelOneTimeOrderService service;
  private final OmnichannelOneTimeOrderListParams originalParams;
  private final Response httpResponse;

  private OmnichannelOneTimeOrderListResponse(
      List<OmnichannelOneTimeOrderListItem> list,
      String nextOffset,
      OmnichannelOneTimeOrderService service,
      OmnichannelOneTimeOrderListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into OmnichannelOneTimeOrderListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelOneTimeOrderListResponse fromJson(String json) {
    try {

      List<OmnichannelOneTimeOrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelOneTimeOrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelOneTimeOrderListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OmnichannelOneTimeOrderListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static OmnichannelOneTimeOrderListResponse fromJson(
      String json,
      OmnichannelOneTimeOrderService service,
      OmnichannelOneTimeOrderListParams originalParams,
      Response httpResponse) {
    try {

      List<OmnichannelOneTimeOrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelOneTimeOrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelOneTimeOrderListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelOneTimeOrderListItem> getList() {
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
  public OmnichannelOneTimeOrderListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OmnichannelOneTimeOrderListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OmnichannelOneTimeOrderListParams.builder())
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
    return "OmnichannelOneTimeOrderListResponse{"
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
    OmnichannelOneTimeOrderListResponse that = (OmnichannelOneTimeOrderListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class OmnichannelOneTimeOrderListItem {

    private OmnichannelOneTimeOrder omnichannelOneTimeOrder;

    public OmnichannelOneTimeOrder getOmnichannelOneTimeOrder() {
      return omnichannelOneTimeOrder;
    }

    public static OmnichannelOneTimeOrderListItem fromJson(String json) {
      OmnichannelOneTimeOrderListItem item = new OmnichannelOneTimeOrderListItem();

      String __omnichannelOneTimeOrderJson = JsonUtil.getObject(json, "omnichannel_one_time_order");
      if (__omnichannelOneTimeOrderJson != null) {
        item.omnichannelOneTimeOrder =
            OmnichannelOneTimeOrder.fromJson(__omnichannelOneTimeOrderJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "OmnichannelOneTimeOrderListItem{"
          + "omnichannelOneTimeOrder="
          + omnichannelOneTimeOrder
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OmnichannelOneTimeOrderListItem that = (OmnichannelOneTimeOrderListItem) o;
      return java.util.Objects.equals(omnichannelOneTimeOrder, that.omnichannelOneTimeOrder);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(omnichannelOneTimeOrder);
    }
  }
}
