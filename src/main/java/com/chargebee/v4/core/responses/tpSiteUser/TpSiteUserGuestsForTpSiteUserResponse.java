package com.chargebee.v4.core.responses.tpSiteUser;

import java.util.List;

import com.chargebee.v4.core.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.TpSiteUserService;
import com.chargebee.v4.core.models.tpSiteUser.params.TpSiteUserGuestsForTpSiteUserParams;

/**
 * Immutable response object for TpSiteUserGuestsForTpSiteUser operation. Contains paginated list
 * data.
 */
public final class TpSiteUserGuestsForTpSiteUserResponse {

  private final List<TpSiteUserGuestsForTpSiteUserItem> list;

  private final String nextOffset;

  private final String tpSiteUserDomain;

  private final TpSiteUserService service;
  private final TpSiteUserGuestsForTpSiteUserParams originalParams;
  private final Response httpResponse;

  private TpSiteUserGuestsForTpSiteUserResponse(
      List<TpSiteUserGuestsForTpSiteUserItem> list,
      String nextOffset,
      String tpSiteUserDomain,
      TpSiteUserService service,
      TpSiteUserGuestsForTpSiteUserParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.tpSiteUserDomain = tpSiteUserDomain;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into TpSiteUserGuestsForTpSiteUserResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static TpSiteUserGuestsForTpSiteUserResponse fromJson(String json) {
    try {

      List<TpSiteUserGuestsForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserGuestsForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TpSiteUserGuestsForTpSiteUserResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TpSiteUserGuestsForTpSiteUserResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TpSiteUserGuestsForTpSiteUserResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static TpSiteUserGuestsForTpSiteUserResponse fromJson(
      String json,
      TpSiteUserService service,
      TpSiteUserGuestsForTpSiteUserParams originalParams,
      String tpSiteUserDomain,
      Response httpResponse) {
    try {

      List<TpSiteUserGuestsForTpSiteUserItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TpSiteUserGuestsForTpSiteUserItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TpSiteUserGuestsForTpSiteUserResponse(
          list, nextOffset, tpSiteUserDomain, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TpSiteUserGuestsForTpSiteUserResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TpSiteUserGuestsForTpSiteUserItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TpSiteUserGuestsForTpSiteUserItem> items() {
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
  public TpSiteUserGuestsForTpSiteUserResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    TpSiteUserGuestsForTpSiteUserParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : TpSiteUserGuestsForTpSiteUserParams.builder())
            .offset(nextOffset)
            .build();

    return service.guestsForTpSiteUser(tpSiteUserDomain, nextParams);
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

  public static class TpSiteUserGuestsForTpSiteUserItem {

    private TpSiteUser tpSiteUser;

    public TpSiteUser getTpSiteUser() {
      return tpSiteUser;
    }

    public static TpSiteUserGuestsForTpSiteUserItem fromJson(String json) {
      TpSiteUserGuestsForTpSiteUserItem item = new TpSiteUserGuestsForTpSiteUserItem();

      String __tpSiteUserJson = JsonUtil.getObject(json, "tp_site_user");
      if (__tpSiteUserJson != null) {
        item.tpSiteUser = TpSiteUser.fromJson(__tpSiteUserJson);
      }

      return item;
    }
  }
}
