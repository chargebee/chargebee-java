package com.chargebee.v4.models.virtualBankAccount.responses;

import java.util.List;

import com.chargebee.v4.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.VirtualBankAccountService;
import com.chargebee.v4.models.virtualBankAccount.params.VirtualBankAccountListParams;

/** Immutable response object for VirtualBankAccountList operation. Contains paginated list data. */
public final class VirtualBankAccountListResponse {

  private final List<VirtualBankAccountListItem> list;

  private final String nextOffset;

  private final VirtualBankAccountService service;
  private final VirtualBankAccountListParams originalParams;
  private final Response httpResponse;

  private VirtualBankAccountListResponse(
      List<VirtualBankAccountListItem> list,
      String nextOffset,
      VirtualBankAccountService service,
      VirtualBankAccountListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into VirtualBankAccountListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static VirtualBankAccountListResponse fromJson(String json) {
    try {

      List<VirtualBankAccountListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VirtualBankAccountListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new VirtualBankAccountListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into VirtualBankAccountListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static VirtualBankAccountListResponse fromJson(
      String json,
      VirtualBankAccountService service,
      VirtualBankAccountListParams originalParams,
      Response httpResponse) {
    try {

      List<VirtualBankAccountListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VirtualBankAccountListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new VirtualBankAccountListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<VirtualBankAccountListItem> getList() {
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
  public VirtualBankAccountListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    VirtualBankAccountListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : VirtualBankAccountListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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
    return "VirtualBankAccountListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VirtualBankAccountListResponse that = (VirtualBankAccountListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class VirtualBankAccountListItem {

    private VirtualBankAccount virtualBankAccount;

    public VirtualBankAccount getVirtualBankAccount() {
      return virtualBankAccount;
    }

    public static VirtualBankAccountListItem fromJson(String json) {
      VirtualBankAccountListItem item = new VirtualBankAccountListItem();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        item.virtualBankAccount = VirtualBankAccount.fromJson(__virtualBankAccountJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "VirtualBankAccountListItem{" + "virtualBankAccount=" + virtualBankAccount + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      VirtualBankAccountListItem that = (VirtualBankAccountListItem) o;
      return java.util.Objects.equals(virtualBankAccount, that.virtualBankAccount);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(virtualBankAccount);
    }
  }
}
