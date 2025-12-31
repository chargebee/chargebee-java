package com.chargebee.v4.models.addon.responses;

import java.util.List;

import com.chargebee.v4.models.addon.Addon;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.AddonService;
import com.chargebee.v4.models.addon.params.AddonListParams;

/** Immutable response object for AddonList operation. Contains paginated list data. */
public final class AddonListResponse {

  private final List<AddonListItem> list;

  private final String nextOffset;

  private final AddonService service;
  private final AddonListParams originalParams;
  private final Response httpResponse;

  private AddonListResponse(
      List<AddonListItem> list,
      String nextOffset,
      AddonService service,
      AddonListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into AddonListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static AddonListResponse fromJson(String json) {
    try {

      List<AddonListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AddonListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AddonListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AddonListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static AddonListResponse fromJson(
      String json, AddonService service, AddonListParams originalParams, Response httpResponse) {
    try {

      List<AddonListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AddonListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AddonListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AddonListItem> getList() {
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
  public AddonListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    AddonListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : AddonListParams.builder())
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
    return "AddonListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddonListResponse that = (AddonListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class AddonListItem {

    private Addon addon;

    public Addon getAddon() {
      return addon;
    }

    public static AddonListItem fromJson(String json) {
      AddonListItem item = new AddonListItem();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        item.addon = Addon.fromJson(__addonJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "AddonListItem{" + "addon=" + addon + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AddonListItem that = (AddonListItem) o;
      return java.util.Objects.equals(addon, that.addon);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(addon);
    }
  }
}
