package com.chargebee.v4.models.virtualBankAccount.responses;

import com.chargebee.v4.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class VirtualBankAccountRetrieveResponse extends BaseResponse {
  private final VirtualBankAccount virtualBankAccount;

  private VirtualBankAccountRetrieveResponse(
      VirtualBankAccount virtualBankAccount, Response httpResponse) {
    super(httpResponse);

    this.virtualBankAccount = virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object with HTTP response. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      VirtualBankAccount virtualBankAccount =
          VirtualBankAccount.fromJson(JsonUtil.getObject(json, "virtual_bank_account"));

      return new VirtualBankAccountRetrieveResponse(virtualBankAccount, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountRetrieveResponse from JSON", e);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
