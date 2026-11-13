package com.chargebee.v4.models.businessEntity.responses;

import java.util.List;

import com.chargebee.v4.models.businessEntity.BusinessEntity;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.BusinessEntityService;
import com.chargebee.v4.models.businessEntity.params.BusinessEntityListParams;

/** Immutable response object for BusinessEntityList operation. Contains paginated list data. */
public final class BusinessEntityListResponse {

  private final List<BusinessEntityListItem> list;

  private final String nextOffset;

  private final BusinessEntityService service;
  private final BusinessEntityListParams originalParams;
  private final Response httpResponse;

  private BusinessEntityListResponse(
      List<BusinessEntityListItem> list,
      String nextOffset,
      BusinessEntityService service,
      BusinessEntityListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into BusinessEntityListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static BusinessEntityListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<BusinessEntityListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), BusinessEntityListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new BusinessEntityListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into BusinessEntityListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static BusinessEntityListResponse fromJson(
      String json,
      BusinessEntityService service,
      BusinessEntityListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<BusinessEntityListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), BusinessEntityListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new BusinessEntityListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<BusinessEntityListItem> getList() {
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
  public BusinessEntityListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    BusinessEntityListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : BusinessEntityListParams.builder())
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
    return "BusinessEntityListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessEntityListResponse that = (BusinessEntityListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class BusinessEntityListItem {

    private BusinessEntity businessEntity;

    public BusinessEntity getBusinessEntity() {
      return businessEntity;
    }

    public static BusinessEntityListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static BusinessEntityListItem fromJson(JsonObject jsonObj) {
      BusinessEntityListItem item = new BusinessEntityListItem();

      JsonObject __businessEntityObj = JsonUtil.getJsonObject(jsonObj, "business_entity");
      if (__businessEntityObj != null) {
        item.businessEntity = BusinessEntity.fromJson(__businessEntityObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "BusinessEntityListItem{" + "businessEntity=" + businessEntity + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      BusinessEntityListItem that = (BusinessEntityListItem) o;
      return java.util.Objects.equals(businessEntity, that.businessEntity);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(businessEntity);
    }
  }
}
