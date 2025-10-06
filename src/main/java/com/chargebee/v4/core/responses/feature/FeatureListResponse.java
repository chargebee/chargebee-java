package com.chargebee.v4.core.responses.feature;

import java.util.List;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.FeatureService;
import com.chargebee.v4.core.models.feature.params.FeatureListParams;

/** Immutable response object for FeatureList operation. Contains paginated list data. */
public final class FeatureListResponse {

  private final List<FeatureListItem> list;

  private final String nextOffset;

  private final FeatureService service;
  private final FeatureListParams originalParams;

  private FeatureListResponse(
      List<FeatureListItem> list,
      String nextOffset,
      FeatureService service,
      FeatureListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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

      return new FeatureListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into FeatureListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static FeatureListResponse fromJson(
      String json, FeatureService service, FeatureListParams originalParams) {
    try {

      List<FeatureListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(FeatureListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new FeatureListResponse(list, nextOffset, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<FeatureListItem> items() {
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
  public FeatureListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    FeatureListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
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
