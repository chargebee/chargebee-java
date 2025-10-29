package com.chargebee.v4.core.responses.businessEntity;

import java.util.List;

import com.chargebee.v4.core.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.BusinessEntityService;
import com.chargebee.v4.core.models.businessEntity.params.BusinessEntityGetTransfersParams;

/**
 * Immutable response object for BusinessEntityGetTransfers operation. Contains paginated list data.
 */
public final class BusinessEntityGetTransfersResponse {

  private final List<BusinessEntityGetTransfersItem> list;

  private final String nextOffset;

  private final BusinessEntityService service;
  private final BusinessEntityGetTransfersParams originalParams;
  private final Response httpResponse;

  private BusinessEntityGetTransfersResponse(
      List<BusinessEntityGetTransfersItem> list,
      String nextOffset,
      BusinessEntityService service,
      BusinessEntityGetTransfersParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into BusinessEntityGetTransfersResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static BusinessEntityGetTransfersResponse fromJson(String json) {
    try {

      List<BusinessEntityGetTransfersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(BusinessEntityGetTransfersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new BusinessEntityGetTransfersResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityGetTransfersResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into BusinessEntityGetTransfersResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static BusinessEntityGetTransfersResponse fromJson(
      String json,
      BusinessEntityService service,
      BusinessEntityGetTransfersParams originalParams,
      Response httpResponse) {
    try {

      List<BusinessEntityGetTransfersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(BusinessEntityGetTransfersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new BusinessEntityGetTransfersResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityGetTransfersResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<BusinessEntityGetTransfersItem> getList() {
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
  public BusinessEntityGetTransfersResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    BusinessEntityGetTransfersParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : BusinessEntityGetTransfersParams.builder())
            .offset(nextOffset)
            .build();

    return service.getTransfers(nextParams);
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

  public static class BusinessEntityGetTransfersItem {

    private BusinessEntityTransfer businessEntityTransfer;

    public BusinessEntityTransfer getBusinessEntityTransfer() {
      return businessEntityTransfer;
    }

    public static BusinessEntityGetTransfersItem fromJson(String json) {
      BusinessEntityGetTransfersItem item = new BusinessEntityGetTransfersItem();

      String __businessEntityTransferJson = JsonUtil.getObject(json, "business_entity_transfer");
      if (__businessEntityTransferJson != null) {
        item.businessEntityTransfer = BusinessEntityTransfer.fromJson(__businessEntityTransferJson);
      }

      return item;
    }
  }
}
