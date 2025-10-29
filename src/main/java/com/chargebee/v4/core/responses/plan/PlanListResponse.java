package com.chargebee.v4.core.responses.plan;

import java.util.List;

import com.chargebee.v4.core.models.plan.Plan;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.PlanService;
import com.chargebee.v4.core.models.plan.params.PlanListParams;

/** Immutable response object for PlanList operation. Contains paginated list data. */
public final class PlanListResponse {

  private final List<PlanListItem> list;

  private final String nextOffset;

  private final PlanService service;
  private final PlanListParams originalParams;
  private final Response httpResponse;

  private PlanListResponse(
      List<PlanListItem> list,
      String nextOffset,
      PlanService service,
      PlanListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PlanListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static PlanListResponse fromJson(String json) {
    try {

      List<PlanListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PlanListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PlanListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PlanListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static PlanListResponse fromJson(
      String json, PlanService service, PlanListParams originalParams, Response httpResponse) {
    try {

      List<PlanListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PlanListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PlanListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PlanListItem> getList() {
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
  public PlanListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    PlanListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : PlanListParams.builder())
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

  public static class PlanListItem {

    private Plan plan;

    public Plan getPlan() {
      return plan;
    }

    public static PlanListItem fromJson(String json) {
      PlanListItem item = new PlanListItem();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        item.plan = Plan.fromJson(__planJson);
      }

      return item;
    }
  }
}
