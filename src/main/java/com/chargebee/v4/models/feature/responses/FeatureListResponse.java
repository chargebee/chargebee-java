package com.chargebee.v4.models.feature.responses;

import java.util.List;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.FeatureService;
import com.chargebee.v4.models.feature.params.FeatureListParams;

/** Immutable response object for FeatureList operation. Contains paginated list data. */
public final class FeatureListResponse {

  private final List<FeatureListItem> list;

  private final String nextOffset;

  private final FeatureService service;
  private final FeatureListParams originalParams;
  private final Response httpResponse;

  private FeatureListResponse(
      List<FeatureListItem> list,
      String nextOffset,
      FeatureService service,
      FeatureListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into FeatureListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static FeatureListResponse fromJson(String json) {
    try {

      List<FeatureListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(FeatureListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new FeatureListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into FeatureListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static FeatureListResponse fromJson(
      String json,
      FeatureService service,
      FeatureListParams originalParams,
      Response httpResponse) {
    try {

      List<FeatureListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(FeatureListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new FeatureListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<FeatureListItem> getList() {
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
  public FeatureListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    FeatureListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : FeatureListParams.builder())
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

  public static class FeatureListItem {

    private Feature feature;

    public Feature getFeature() {
      return feature;
    }

    public static FeatureListItem fromJson(String json) {
      FeatureListItem item = new FeatureListItem();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        item.feature = Feature.fromJson(__featureJson);
      }

      return item;
    }
  }
}
