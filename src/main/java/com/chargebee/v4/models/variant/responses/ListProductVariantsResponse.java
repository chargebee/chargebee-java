package com.chargebee.v4.models.variant.responses;

import java.util.List;

import com.chargebee.v4.models.variant.Variant;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.VariantService;
import com.chargebee.v4.models.variant.params.ListProductVariantsParams;

/** Immutable response object for ListProductVariants operation. Contains paginated list data. */
public final class ListProductVariantsResponse {

  private final List<VariantListProductVariantsItem> list;

  private final String nextOffset;

  private final String productId;

  private final VariantService service;
  private final ListProductVariantsParams originalParams;
  private final Response httpResponse;

  private ListProductVariantsResponse(
      List<VariantListProductVariantsItem> list,
      String nextOffset,
      String productId,
      VariantService service,
      ListProductVariantsParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.productId = productId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListProductVariantsResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static ListProductVariantsResponse fromJson(String json) {
    try {

      List<VariantListProductVariantsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VariantListProductVariantsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ListProductVariantsResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListProductVariantsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListProductVariantsResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ListProductVariantsResponse fromJson(
      String json,
      VariantService service,
      ListProductVariantsParams originalParams,
      String productId,
      Response httpResponse) {
    try {

      List<VariantListProductVariantsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VariantListProductVariantsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ListProductVariantsResponse(
          list, nextOffset, productId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ListProductVariantsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<VariantListProductVariantsItem> getList() {
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
  public ListProductVariantsResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListProductVariantsParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ListProductVariantsParams.builder())
            .offset(nextOffset)
            .build();

    return service.listProductVariants(productId, nextParams);
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
    return "ListProductVariantsResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListProductVariantsResponse that = (ListProductVariantsResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class VariantListProductVariantsItem {

    private Variant variant;

    public Variant getVariant() {
      return variant;
    }

    public static VariantListProductVariantsItem fromJson(String json) {
      VariantListProductVariantsItem item = new VariantListProductVariantsItem();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        item.variant = Variant.fromJson(__variantJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "VariantListProductVariantsItem{" + "variant=" + variant + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      VariantListProductVariantsItem that = (VariantListProductVariantsItem) o;
      return java.util.Objects.equals(variant, that.variant);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(variant);
    }
  }
}
