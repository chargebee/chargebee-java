package com.chargebee.core.responses.businessEntity;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.BusinessEntityService;
import com.chargebee.core.models.businessEntity.params.BusinessEntityGetTransfersParams;

/**
 * Immutable response object for BusinessEntityGetTransfers operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class BusinessEntityGetTransfersResponse
    implements Iterable<BusinessEntityGetTransfersResponse.BusinessEntityGetTransfersItem> {

  private final List<BusinessEntityGetTransfersItem> list;

  private final String nextOffset;

  private final BusinessEntityService service;
  private final BusinessEntityGetTransfersParams originalParams;
  private final boolean isAutoPaginate;

  private BusinessEntityGetTransfersResponse(
      List<BusinessEntityGetTransfersItem> list,
      String nextOffset,
      BusinessEntityService service,
      BusinessEntityGetTransfersParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private BusinessEntityGetTransfersResponse(
      List<BusinessEntityGetTransfersItem> list,
      String nextOffset,
      BusinessEntityService service,
      BusinessEntityGetTransfersParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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

      return new BusinessEntityGetTransfersResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityGetTransfersResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into BusinessEntityGetTransfersResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static BusinessEntityGetTransfersResponse fromJson(
      String json, BusinessEntityService service, BusinessEntityGetTransfersParams originalParams) {
    try {

      List<BusinessEntityGetTransfersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(BusinessEntityGetTransfersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new BusinessEntityGetTransfersResponse(list, nextOffset, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<BusinessEntityGetTransfersItem> items() {
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
  public BusinessEntityGetTransfersResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    BusinessEntityGetTransfersParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.getTransfers(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public BusinessEntityGetTransfersResponse autoPaginate() {
    return new BusinessEntityGetTransfersResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<BusinessEntityGetTransfersItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<BusinessEntityGetTransfersItem> {
    private BusinessEntityGetTransfersResponse currentPage =
        BusinessEntityGetTransfersResponse.this;
    private Iterator<BusinessEntityGetTransfersItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public BusinessEntityGetTransfersItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
