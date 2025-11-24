package com.chargebee.v4.models.businessEntity.responses;

import java.util.List;

import com.chargebee.v4.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.BusinessEntityService;
import com.chargebee.v4.models.businessEntity.params.BusinessEntityListTransfersParams;

/**
 * Immutable response object for BusinessEntityListTransfers operation. Contains paginated list
 * data.
 */
public final class BusinessEntityListTransfersResponse {

  private final List<BusinessEntityListTransfersItem> list;

  private final String nextOffset;

  private final BusinessEntityService service;
  private final BusinessEntityListTransfersParams originalParams;
  private final Response httpResponse;

  private BusinessEntityListTransfersResponse(
      List<BusinessEntityListTransfersItem> list,
      String nextOffset,
      BusinessEntityService service,
      BusinessEntityListTransfersParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into BusinessEntityListTransfersResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static BusinessEntityListTransfersResponse fromJson(String json) {
    try {

      List<BusinessEntityListTransfersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(BusinessEntityListTransfersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new BusinessEntityListTransfersResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse BusinessEntityListTransfersResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into BusinessEntityListTransfersResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static BusinessEntityListTransfersResponse fromJson(
      String json,
      BusinessEntityService service,
      BusinessEntityListTransfersParams originalParams,
      Response httpResponse) {
    try {

      List<BusinessEntityListTransfersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(BusinessEntityListTransfersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new BusinessEntityListTransfersResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse BusinessEntityListTransfersResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<BusinessEntityListTransfersItem> getList() {
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
  public BusinessEntityListTransfersResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    BusinessEntityListTransfersParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : BusinessEntityListTransfersParams.builder())
            .offset(nextOffset)
            .build();

    return service.listTransfers(nextParams);
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

  public static class BusinessEntityListTransfersItem {

    private BusinessEntityTransfer businessEntityTransfer;

    public BusinessEntityTransfer getBusinessEntityTransfer() {
      return businessEntityTransfer;
    }

    public static BusinessEntityListTransfersItem fromJson(String json) {
      BusinessEntityListTransfersItem item = new BusinessEntityListTransfersItem();

      String __businessEntityTransferJson = JsonUtil.getObject(json, "business_entity_transfer");
      if (__businessEntityTransferJson != null) {
        item.businessEntityTransfer = BusinessEntityTransfer.fromJson(__businessEntityTransferJson);
      }

      return item;
    }
  }
}
