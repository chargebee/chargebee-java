package com.chargebee.v4.core.responses.priceVariant;

import java.util.List;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.PriceVariantService;
import com.chargebee.v4.core.models.priceVariant.params.PriceVariantListParams;

/** Immutable response object for PriceVariantList operation. Contains paginated list data. */
public final class PriceVariantListResponse {

  private final List<PriceVariantListItem> list;

  private final String nextOffset;

  private final PriceVariantService service;
  private final PriceVariantListParams originalParams;
  private final Response httpResponse;

  private PriceVariantListResponse(
      List<PriceVariantListItem> list,
      String nextOffset,
      PriceVariantService service,
      PriceVariantListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PriceVariantListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static PriceVariantListResponse fromJson(String json) {
    try {

      List<PriceVariantListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PriceVariantListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PriceVariantListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PriceVariantListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static PriceVariantListResponse fromJson(
      String json,
      PriceVariantService service,
      PriceVariantListParams originalParams,
      Response httpResponse) {
    try {

      List<PriceVariantListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PriceVariantListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PriceVariantListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PriceVariantListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PriceVariantListItem> items() {
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
  public PriceVariantListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    PriceVariantListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : PriceVariantListParams.builder())
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

  public static class PriceVariantListItem {

    private PriceVariant priceVariant;

    public PriceVariant getPriceVariant() {
      return priceVariant;
    }

    public static PriceVariantListItem fromJson(String json) {
      PriceVariantListItem item = new PriceVariantListItem();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        item.priceVariant = PriceVariant.fromJson(__priceVariantJson);
      }

      return item;
    }
  }
}
