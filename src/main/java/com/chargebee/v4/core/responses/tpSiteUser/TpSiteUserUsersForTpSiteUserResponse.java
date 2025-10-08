package com.chargebee.v4.core.responses.tpSiteUser;

import java.util.List;

import com.chargebee.v4.core.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.TpSiteUserService;
import com.chargebee.v4.core.models.tpSiteUser.params.TpSiteUserUsersForTpSiteUserParams;

/**
 * Immutable response object for TpSiteUserUsersForTpSiteUser operation. Contains paginated list
 * data.
 */
public final class TpSiteUserUsersForTpSiteUserResponse {

  private final List<TpSiteUserUsersForTpSiteUserItem> list;

  private final String nextOffset;

  private final String tpSiteUserDomain;

  private final TpSiteUserService service;
  private final TpSiteUserUsersForTpSiteUserParams originalParams;
  private final Response httpResponse;

  private TpSiteUserUsersForTpSiteUserResponse(
      List<TpSiteUserUsersForTpSiteUserItem> list,
      String nextOffset,
      String tpSiteUserDomain,
      TpSiteUserService service,
      TpSiteUserUsersForTpSiteUserParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.tpSiteUserDomain = tpSiteUserDomain;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new TpSiteUserUsersForTpSiteUserResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TpSiteUserUsersForTpSiteUserResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TpSiteUserUsersForTpSiteUserResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static TpSiteUserUsersForTpSiteUserResponse fromJson(
      String json,
      TpSiteUserService service,
      TpSiteUserUsersForTpSiteUserParams originalParams,
      String tpSiteUserDomain,
      Response httpResponse) {
    try {

      List<TpSiteUserUsersForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserUsersForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TpSiteUserUsersForTpSiteUserResponse(
          list, nextOffset, tpSiteUserDomain, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    TpSiteUserUsersForTpSiteUserParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : TpSiteUserUsersForTpSiteUserParams.builder())
            .offset(nextOffset)
            .build();

    return service.usersForTpSiteUser(tpSiteUserDomain, nextParams);
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
