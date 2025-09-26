package com.chargebee.core.responses.tpSiteUser;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.tpSiteUser.TpSiteUser;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.TpSiteUserService;
import com.chargebee.core.models.tpSiteUser.params.TpSiteUserUsersForTpSiteUserParams;

/**
 * Immutable response object for TpSiteUserUsersForTpSiteUser operation. Contains paginated list
 * data with auto-pagination support.
 */
public final class TpSiteUserUsersForTpSiteUserResponse
    implements Iterable<TpSiteUserUsersForTpSiteUserResponse.TpSiteUserUsersForTpSiteUserItem> {

  private final List<TpSiteUserUsersForTpSiteUserItem> list;

  private final String nextOffset;

  private final String tpSiteUserDomain;

  private final TpSiteUserService service;
  private final TpSiteUserUsersForTpSiteUserParams originalParams;
  private final boolean isAutoPaginate;

  private TpSiteUserUsersForTpSiteUserResponse(
      List<TpSiteUserUsersForTpSiteUserItem> list,
      String nextOffset,
      String tpSiteUserDomain,
      TpSiteUserService service,
      TpSiteUserUsersForTpSiteUserParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.tpSiteUserDomain = tpSiteUserDomain;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private TpSiteUserUsersForTpSiteUserResponse(
      List<TpSiteUserUsersForTpSiteUserItem> list,
      String nextOffset,
      String tpSiteUserDomain,
      TpSiteUserService service,
      TpSiteUserUsersForTpSiteUserParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.tpSiteUserDomain = tpSiteUserDomain;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into TpSiteUserUsersForTpSiteUserResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static TpSiteUserUsersForTpSiteUserResponse fromJson(String json) {
    try {

      List<TpSiteUserUsersForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserUsersForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TpSiteUserUsersForTpSiteUserResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TpSiteUserUsersForTpSiteUserResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TpSiteUserUsersForTpSiteUserResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static TpSiteUserUsersForTpSiteUserResponse fromJson(
      String json,
      TpSiteUserService service,
      TpSiteUserUsersForTpSiteUserParams originalParams,
      String tpSiteUserDomain) {
    try {

      List<TpSiteUserUsersForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserUsersForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TpSiteUserUsersForTpSiteUserResponse(
          list, nextOffset, tpSiteUserDomain, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TpSiteUserUsersForTpSiteUserResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TpSiteUserUsersForTpSiteUserItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TpSiteUserUsersForTpSiteUserItem> items() {
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
  public TpSiteUserUsersForTpSiteUserResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    TpSiteUserUsersForTpSiteUserParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.usersForTpSiteUser(tpSiteUserDomain, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public TpSiteUserUsersForTpSiteUserResponse autoPaginate() {
    return new TpSiteUserUsersForTpSiteUserResponse(
        list, nextOffset, tpSiteUserDomain, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<TpSiteUserUsersForTpSiteUserItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<TpSiteUserUsersForTpSiteUserItem> {
    private TpSiteUserUsersForTpSiteUserResponse currentPage =
        TpSiteUserUsersForTpSiteUserResponse.this;
    private Iterator<TpSiteUserUsersForTpSiteUserItem> currentIterator =
        currentPage.list.iterator();

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
    public TpSiteUserUsersForTpSiteUserItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class TpSiteUserUsersForTpSiteUserItem {

    private TpSiteUser tpSiteUser;

    public TpSiteUser getTpSiteUser() {
      return tpSiteUser;
    }

    public static TpSiteUserUsersForTpSiteUserItem fromJson(String json) {
      TpSiteUserUsersForTpSiteUserItem item = new TpSiteUserUsersForTpSiteUserItem();

      String __tpSiteUserJson = JsonUtil.getObject(json, "tp_site_user");
      if (__tpSiteUserJson != null) {
        item.tpSiteUser = TpSiteUser.fromJson(__tpSiteUserJson);
      }

      return item;
    }
  }
}
