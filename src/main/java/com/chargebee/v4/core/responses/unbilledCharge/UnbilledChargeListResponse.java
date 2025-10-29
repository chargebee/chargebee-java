package com.chargebee.v4.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.UnbilledChargeService;
import com.chargebee.v4.core.models.unbilledCharge.params.UnbilledChargeListParams;

/** Immutable response object for UnbilledChargeList operation. Contains paginated list data. */
public final class UnbilledChargeListResponse {

  private final List<UnbilledChargeListItem> list;

  private final String nextOffset;

  private final UnbilledChargeService service;
  private final UnbilledChargeListParams originalParams;
  private final Response httpResponse;

  private UnbilledChargeListResponse(
      List<UnbilledChargeListItem> list,
      String nextOffset,
      UnbilledChargeService service,
      UnbilledChargeListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into UnbilledChargeListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static UnbilledChargeListResponse fromJson(String json) {
    try {

      List<UnbilledChargeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UnbilledChargeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UnbilledChargeListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into UnbilledChargeListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static UnbilledChargeListResponse fromJson(
      String json,
      UnbilledChargeService service,
      UnbilledChargeListParams originalParams,
      Response httpResponse) {
    try {

      List<UnbilledChargeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UnbilledChargeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UnbilledChargeListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<UnbilledChargeListItem> getList() {
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
  public UnbilledChargeListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    UnbilledChargeListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : UnbilledChargeListParams.builder())
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

  public static class UnbilledChargeListItem {

    private UnbilledCharge unbilledCharge;

    public UnbilledCharge getUnbilledCharge() {
      return unbilledCharge;
    }

    public static UnbilledChargeListItem fromJson(String json) {
      UnbilledChargeListItem item = new UnbilledChargeListItem();

      String __unbilledChargeJson = JsonUtil.getObject(json, "unbilled_charge");
      if (__unbilledChargeJson != null) {
        item.unbilledCharge = UnbilledCharge.fromJson(__unbilledChargeJson);
      }

      return item;
    }
  }
}
