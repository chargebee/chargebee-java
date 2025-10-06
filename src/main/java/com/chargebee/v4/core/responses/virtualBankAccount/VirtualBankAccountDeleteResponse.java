package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for VirtualBankAccountDelete operation. Contains the response data from
 * the API.
 */
public final class VirtualBankAccountDeleteResponse {

  private final VirtualBankAccount virtualBankAccount;

  private VirtualBankAccountDeleteResponse(Builder builder) {

    this.virtualBankAccount = builder.virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountDeleteResponse object. */
  public static VirtualBankAccountDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountDeleteResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public VirtualBankAccountDeleteResponse build() {
      return new VirtualBankAccountDeleteResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
