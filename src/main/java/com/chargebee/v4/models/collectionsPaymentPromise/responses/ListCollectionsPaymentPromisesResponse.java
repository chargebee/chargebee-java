package com.chargebee.v4.models.collectionsPaymentPromise.responses;

import java.util.List;

import com.chargebee.v4.models.collectionsPaymentPromise.CollectionsPaymentPromise;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CollectionsPaymentPromiseService;
import com.chargebee.v4.models.collectionsPaymentPromise.params.ListCollectionsPaymentPromisesParams;

/**
 * Immutable response object for ListCollectionsPaymentPromises operation. Contains paginated list
 * data.
 */
public final class ListCollectionsPaymentPromisesResponse {

  private final List<CollectionsPaymentPromiseListCollectionsPaymentPromisesItem> list;

  private final String nextOffset;

  private final CollectionsPaymentPromiseService service;
  private final ListCollectionsPaymentPromisesParams originalParams;
  private final Response httpResponse;

  private ListCollectionsPaymentPromisesResponse(
      List<CollectionsPaymentPromiseListCollectionsPaymentPromisesItem> list,
      String nextOffset,
      CollectionsPaymentPromiseService service,
      ListCollectionsPaymentPromisesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListCollectionsPaymentPromisesResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static ListCollectionsPaymentPromisesResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CollectionsPaymentPromiseListCollectionsPaymentPromisesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              CollectionsPaymentPromiseListCollectionsPaymentPromisesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListCollectionsPaymentPromisesResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListCollectionsPaymentPromisesResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListCollectionsPaymentPromisesResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ListCollectionsPaymentPromisesResponse fromJson(
      String json,
      CollectionsPaymentPromiseService service,
      ListCollectionsPaymentPromisesParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<CollectionsPaymentPromiseListCollectionsPaymentPromisesItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              CollectionsPaymentPromiseListCollectionsPaymentPromisesItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListCollectionsPaymentPromisesResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListCollectionsPaymentPromisesResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CollectionsPaymentPromiseListCollectionsPaymentPromisesItem> getList() {
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
  public ListCollectionsPaymentPromisesResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListCollectionsPaymentPromisesParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ListCollectionsPaymentPromisesParams.builder())
            .offset(nextOffset)
            .build();

    return service.listCollectionsPaymentPromises(nextParams);
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
    return "ListCollectionsPaymentPromisesResponse{"
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

    ListCollectionsPaymentPromisesResponse that = (ListCollectionsPaymentPromisesResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class CollectionsPaymentPromiseListCollectionsPaymentPromisesItem {

    private CollectionsPaymentPromise collectionsPaymentPromise;

    public CollectionsPaymentPromise getCollectionsPaymentPromise() {
      return collectionsPaymentPromise;
    }

    public static CollectionsPaymentPromiseListCollectionsPaymentPromisesItem fromJson(
        String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static CollectionsPaymentPromiseListCollectionsPaymentPromisesItem fromJson(
        JsonObject jsonObj) {
      CollectionsPaymentPromiseListCollectionsPaymentPromisesItem item =
          new CollectionsPaymentPromiseListCollectionsPaymentPromisesItem();

      JsonObject __collectionsPaymentPromiseObj =
          JsonUtil.getJsonObject(jsonObj, "collections_payment_promise");
      if (__collectionsPaymentPromiseObj != null) {
        item.collectionsPaymentPromise =
            CollectionsPaymentPromise.fromJson(__collectionsPaymentPromiseObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "CollectionsPaymentPromiseListCollectionsPaymentPromisesItem{"
          + "collectionsPaymentPromise="
          + collectionsPaymentPromise
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CollectionsPaymentPromiseListCollectionsPaymentPromisesItem that =
          (CollectionsPaymentPromiseListCollectionsPaymentPromisesItem) o;
      return java.util.Objects.equals(collectionsPaymentPromise, that.collectionsPaymentPromise);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(collectionsPaymentPromise);
    }
  }
}
