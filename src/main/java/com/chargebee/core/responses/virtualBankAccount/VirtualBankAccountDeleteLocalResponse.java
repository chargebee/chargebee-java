package com.chargebee.core.responses.virtualBankAccount;

import com.chargebee.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for VirtualBankAccountDeleteLocal operation. Contains the response data
 * from the API.
 */
public final class VirtualBankAccountDeleteLocalResponse {

  private final VirtualBankAccount virtualBankAccount;

  private VirtualBankAccountDeleteLocalResponse(Builder builder) {

    this.virtualBankAccount = builder.virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountDeleteLocalResponse object. */
  public static VirtualBankAccountDeleteLocalResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse VirtualBankAccountDeleteLocalResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountDeleteLocalResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountDeleteLocalResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public VirtualBankAccountDeleteLocalResponse build() {
      return new VirtualBankAccountDeleteLocalResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
