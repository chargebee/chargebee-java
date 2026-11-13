package com.chargebee.v4.models.organizationDetail.responses;

import java.util.List;

import com.chargebee.v4.models.organizationDetail.OrganizationDetail;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.OrganizationDetailService;
import com.chargebee.v4.models.organizationDetail.params.OrganizationDetailListParams;

/** Immutable response object for OrganizationDetailList operation. Contains paginated list data. */
public final class OrganizationDetailListResponse {

  private final List<OrganizationDetailListItem> list;

  private final String nextOffset;

  private final OrganizationDetailService service;
  private final OrganizationDetailListParams originalParams;
  private final Response httpResponse;

  private OrganizationDetailListResponse(
      List<OrganizationDetailListItem> list,
      String nextOffset,
      OrganizationDetailService service,
      OrganizationDetailListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into OrganizationDetailListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static OrganizationDetailListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<OrganizationDetailListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), OrganizationDetailListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new OrganizationDetailListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrganizationDetailListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OrganizationDetailListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static OrganizationDetailListResponse fromJson(
      String json,
      OrganizationDetailService service,
      OrganizationDetailListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<OrganizationDetailListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), OrganizationDetailListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new OrganizationDetailListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrganizationDetailListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OrganizationDetailListItem> getList() {
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
  public OrganizationDetailListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OrganizationDetailListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OrganizationDetailListParams.builder())
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
    return "OrganizationDetailListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrganizationDetailListResponse that = (OrganizationDetailListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class OrganizationDetailListItem {

    private OrganizationDetail organizationDetail;

    public OrganizationDetail getOrganizationDetail() {
      return organizationDetail;
    }

    public static OrganizationDetailListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static OrganizationDetailListItem fromJson(JsonObject jsonObj) {
      OrganizationDetailListItem item = new OrganizationDetailListItem();

      JsonObject __organizationDetailObj = JsonUtil.getJsonObject(jsonObj, "organization_detail");
      if (__organizationDetailObj != null) {
        item.organizationDetail = OrganizationDetail.fromJson(__organizationDetailObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "OrganizationDetailListItem{" + "organizationDetail=" + organizationDetail + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      OrganizationDetailListItem that = (OrganizationDetailListItem) o;
      return java.util.Objects.equals(organizationDetail, that.organizationDetail);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(organizationDetail);
    }
  }
}
