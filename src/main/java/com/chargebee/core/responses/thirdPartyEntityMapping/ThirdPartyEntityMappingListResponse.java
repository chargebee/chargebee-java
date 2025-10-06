package com.chargebee.core.responses.thirdPartyEntityMapping;

import java.util.List;

import com.chargebee.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ThirdPartyEntityMappingService;
import com.chargebee.core.models.thirdPartyEntityMapping.params.ThirdPartyEntityMappingListParams;

/**
 * Immutable response object for ThirdPartyEntityMappingList operation. Contains paginated list
 * data.
 */
public final class ThirdPartyEntityMappingListResponse {

  private final List<ThirdPartyEntityMappingListItem> list;

  private final String nextOffset;

  private final ThirdPartyEntityMappingService service;
  private final ThirdPartyEntityMappingListParams originalParams;

  private ThirdPartyEntityMappingListResponse(
      List<ThirdPartyEntityMappingListItem> list,
      String nextOffset,
      ThirdPartyEntityMappingService service,
      ThirdPartyEntityMappingListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into ThirdPartyEntityMappingListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static ThirdPartyEntityMappingListResponse fromJson(String json) {
    try {

      List<ThirdPartyEntityMappingListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ThirdPartyEntityMappingListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ThirdPartyEntityMappingListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ThirdPartyEntityMappingListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ThirdPartyEntityMappingListResponse fromJson(
      String json,
      ThirdPartyEntityMappingService service,
      ThirdPartyEntityMappingListParams originalParams) {
    try {

      List<ThirdPartyEntityMappingListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ThirdPartyEntityMappingListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ThirdPartyEntityMappingListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ThirdPartyEntityMappingListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ThirdPartyEntityMappingListItem> items() {
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
  public ThirdPartyEntityMappingListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ThirdPartyEntityMappingListParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class ThirdPartyEntityMappingListItem {

    private ThirdPartyEntityMapping thirdPartyEntityMapping;

    public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
      return thirdPartyEntityMapping;
    }

    public static ThirdPartyEntityMappingListItem fromJson(String json) {
      ThirdPartyEntityMappingListItem item = new ThirdPartyEntityMappingListItem();

      String __thirdPartyEntityMappingJson = JsonUtil.getObject(json, "third_party_entity_mapping");
      if (__thirdPartyEntityMappingJson != null) {
        item.thirdPartyEntityMapping =
            ThirdPartyEntityMapping.fromJson(__thirdPartyEntityMappingJson);
      }

      return item;
    }
  }
}
