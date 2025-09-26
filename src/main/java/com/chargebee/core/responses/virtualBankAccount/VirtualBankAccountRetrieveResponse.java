package com.chargebee.core.responses.virtualBankAccount;

import com.chargebee.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for VirtualBankAccountRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class VirtualBankAccountRetrieveResponse {

  private final VirtualBankAccount virtualBankAccount;

  private VirtualBankAccountRetrieveResponse(VirtualBankAccount virtualBankAccount) {

    this.virtualBankAccount = virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json) {
    try {

      VirtualBankAccount virtualBankAccount =
          VirtualBankAccount.fromJson(JsonUtil.getObject(json, "virtual_bank_account"));

      return new VirtualBankAccountRetrieveResponse(virtualBankAccount);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountRetrieveResponse from JSON", e);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
