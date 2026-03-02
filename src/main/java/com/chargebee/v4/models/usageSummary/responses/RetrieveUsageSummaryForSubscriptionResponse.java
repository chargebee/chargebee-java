package com.chargebee.v4.models.usageSummary.responses;

import java.util.List;

import com.chargebee.v4.models.usageSummary.UsageSummary;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.UsageSummaryService;
import com.chargebee.v4.models.usageSummary.params.RetrieveUsageSummaryForSubscriptionParams;

/**
 * Immutable response object for RetrieveUsageSummaryForSubscription operation. Contains paginated
 * list data.
 */
public final class RetrieveUsageSummaryForSubscriptionResponse {

  private final List<UsageSummaryRetrieveUsageSummaryForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final UsageSummaryService service;
  private final RetrieveUsageSummaryForSubscriptionParams originalParams;
  private final Response httpResponse;

  private RetrieveUsageSummaryForSubscriptionResponse(
      List<UsageSummaryRetrieveUsageSummaryForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      UsageSummaryService service,
      RetrieveUsageSummaryForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into RetrieveUsageSummaryForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static RetrieveUsageSummaryForSubscriptionResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<UsageSummaryRetrieveUsageSummaryForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              UsageSummaryRetrieveUsageSummaryForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new RetrieveUsageSummaryForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveUsageSummaryForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into RetrieveUsageSummaryForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static RetrieveUsageSummaryForSubscriptionResponse fromJson(
      String json,
      UsageSummaryService service,
      RetrieveUsageSummaryForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<UsageSummaryRetrieveUsageSummaryForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              UsageSummaryRetrieveUsageSummaryForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new RetrieveUsageSummaryForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveUsageSummaryForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<UsageSummaryRetrieveUsageSummaryForSubscriptionItem> getList() {
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
  public RetrieveUsageSummaryForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    RetrieveUsageSummaryForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : RetrieveUsageSummaryForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.retrieveUsageSummaryForSubscription(subscriptionId, nextParams);
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
    return "RetrieveUsageSummaryForSubscriptionResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RetrieveUsageSummaryForSubscriptionResponse that =
        (RetrieveUsageSummaryForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class UsageSummaryRetrieveUsageSummaryForSubscriptionItem {

    private UsageSummary usageSummary;

    public UsageSummary getUsageSummary() {
      return usageSummary;
    }

    public static UsageSummaryRetrieveUsageSummaryForSubscriptionItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static UsageSummaryRetrieveUsageSummaryForSubscriptionItem fromJson(JsonObject jsonObj) {
      UsageSummaryRetrieveUsageSummaryForSubscriptionItem item =
          new UsageSummaryRetrieveUsageSummaryForSubscriptionItem();

      JsonObject __usageSummaryObj = JsonUtil.getJsonObject(jsonObj, "usage_summary");
      if (__usageSummaryObj != null) {
        item.usageSummary = UsageSummary.fromJson(__usageSummaryObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "UsageSummaryRetrieveUsageSummaryForSubscriptionItem{"
          + "usageSummary="
          + usageSummary
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      UsageSummaryRetrieveUsageSummaryForSubscriptionItem that =
          (UsageSummaryRetrieveUsageSummaryForSubscriptionItem) o;
      return java.util.Objects.equals(usageSummary, that.usageSummary);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(usageSummary);
    }
  }
}
