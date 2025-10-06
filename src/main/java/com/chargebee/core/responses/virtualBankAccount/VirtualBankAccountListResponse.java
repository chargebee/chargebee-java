package com.chargebee.core.responses.virtualBankAccount;

import java.util.List;

import com.chargebee.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.VirtualBankAccountService;
import com.chargebee.core.models.virtualBankAccount.params.VirtualBankAccountListParams;

/** Immutable response object for VirtualBankAccountList operation. Contains paginated list data. */
public final class VirtualBankAccountListResponse {

  private final List<VirtualBankAccountListItem> list;

  private final String nextOffset;

  private final VirtualBankAccountService service;
  private final VirtualBankAccountListParams originalParams;

  private VirtualBankAccountListResponse(
      List<VirtualBankAccountListItem> list,
      String nextOffset,
      VirtualBankAccountService service,
      VirtualBankAccountListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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

      return new VirtualBankAccountListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into VirtualBankAccountListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static VirtualBankAccountListResponse fromJson(
      String json, VirtualBankAccountService service, VirtualBankAccountListParams originalParams) {
    try {

      List<VirtualBankAccountListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VirtualBankAccountListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new VirtualBankAccountListResponse(list, nextOffset, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<VirtualBankAccountListItem> items() {
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
  public VirtualBankAccountListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    VirtualBankAccountListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
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
  }
}
