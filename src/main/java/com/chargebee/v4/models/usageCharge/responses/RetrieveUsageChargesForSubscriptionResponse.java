package com.chargebee.v4.models.usageCharge.responses;

import java.util.List;

import com.chargebee.v4.models.usageCharge.UsageCharge;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.UsageChargeService;
import com.chargebee.v4.models.usageCharge.params.RetrieveUsageChargesForSubscriptionParams;

/**
 * Immutable response object for RetrieveUsageChargesForSubscription operation. Contains paginated
 * list data.
 */
public final class RetrieveUsageChargesForSubscriptionResponse {

  private final List<UsageChargeRetrieveUsageChargesForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final UsageChargeService service;
  private final RetrieveUsageChargesForSubscriptionParams originalParams;
  private final Response httpResponse;

  private RetrieveUsageChargesForSubscriptionResponse(
      List<UsageChargeRetrieveUsageChargesForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      UsageChargeService service,
      RetrieveUsageChargesForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into RetrieveUsageChargesForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static RetrieveUsageChargesForSubscriptionResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<UsageChargeRetrieveUsageChargesForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              UsageChargeRetrieveUsageChargesForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new RetrieveUsageChargesForSubscriptionResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveUsageChargesForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into RetrieveUsageChargesForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static RetrieveUsageChargesForSubscriptionResponse fromJson(
      String json,
      UsageChargeService service,
      RetrieveUsageChargesForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<UsageChargeRetrieveUsageChargesForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              UsageChargeRetrieveUsageChargesForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new RetrieveUsageChargesForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveUsageChargesForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<UsageChargeRetrieveUsageChargesForSubscriptionItem> getList() {
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
  public RetrieveUsageChargesForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    RetrieveUsageChargesForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : RetrieveUsageChargesForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.retrieveUsageChargesForSubscription(subscriptionId, nextParams);
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
    return "RetrieveUsageChargesForSubscriptionResponse{"
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

    RetrieveUsageChargesForSubscriptionResponse that =
        (RetrieveUsageChargesForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class UsageChargeRetrieveUsageChargesForSubscriptionItem {

    private UsageCharge usageCharge;

    public UsageCharge getUsageCharge() {
      return usageCharge;
    }

    public static UsageChargeRetrieveUsageChargesForSubscriptionItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static UsageChargeRetrieveUsageChargesForSubscriptionItem fromJson(JsonObject jsonObj) {
      UsageChargeRetrieveUsageChargesForSubscriptionItem item =
          new UsageChargeRetrieveUsageChargesForSubscriptionItem();

      JsonObject __usageChargeObj = JsonUtil.getJsonObject(jsonObj, "usage_charge");
      if (__usageChargeObj != null) {
        item.usageCharge = UsageCharge.fromJson(__usageChargeObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "UsageChargeRetrieveUsageChargesForSubscriptionItem{"
          + "usageCharge="
          + usageCharge
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      UsageChargeRetrieveUsageChargesForSubscriptionItem that =
          (UsageChargeRetrieveUsageChargesForSubscriptionItem) o;
      return java.util.Objects.equals(usageCharge, that.usageCharge);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(usageCharge);
    }
  }
}
