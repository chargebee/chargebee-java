package com.chargebee.v4.models.tpSiteUser.responses;

import java.util.List;

import com.chargebee.v4.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.TpSiteUserService;
import com.chargebee.v4.models.tpSiteUser.params.UsersForTpSiteUserParams;

/** Immutable response object for UsersForTpSiteUser operation. Contains paginated list data. */
public final class UsersForTpSiteUserResponse {

  private final List<TpSiteUserUsersForTpSiteUserItem> list;

  private final String nextOffset;

  private final String tpSiteUserDomain;

  private final TpSiteUserService service;
  private final UsersForTpSiteUserParams originalParams;
  private final Response httpResponse;

  private UsersForTpSiteUserResponse(
      List<TpSiteUserUsersForTpSiteUserItem> list,
      String nextOffset,
      String tpSiteUserDomain,
      TpSiteUserService service,
      UsersForTpSiteUserParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.tpSiteUserDomain = tpSiteUserDomain;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into UsersForTpSiteUserResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static UsersForTpSiteUserResponse fromJson(String json) {
    try {

      List<TpSiteUserUsersForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserUsersForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UsersForTpSiteUserResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsersForTpSiteUserResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into UsersForTpSiteUserResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static UsersForTpSiteUserResponse fromJson(
      String json,
      TpSiteUserService service,
      UsersForTpSiteUserParams originalParams,
      String tpSiteUserDomain,
      Response httpResponse) {
    try {

      List<TpSiteUserUsersForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserUsersForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UsersForTpSiteUserResponse(
          list, nextOffset, tpSiteUserDomain, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsersForTpSiteUserResponse from JSON", e);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public UsersForTpSiteUserResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    UsersForTpSiteUserParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : UsersForTpSiteUserParams.builder())
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

  @Override
  public String toString() {
    return "UsersForTpSiteUserResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UsersForTpSiteUserResponse that = (UsersForTpSiteUserResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "TpSiteUserUsersForTpSiteUserItem{" + "tpSiteUser=" + tpSiteUser + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      TpSiteUserUsersForTpSiteUserItem that = (TpSiteUserUsersForTpSiteUserItem) o;
      return java.util.Objects.equals(tpSiteUser, that.tpSiteUser);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(tpSiteUser);
    }
  }
}
