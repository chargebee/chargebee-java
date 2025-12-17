package com.chargebee.v4.models.virtualBankAccount.responses;

import com.chargebee.v4.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteVirtualBankAccount operation. Contains the response data from
 * the API.
 */
public final class DeleteVirtualBankAccountResponse extends BaseResponse {
  private final VirtualBankAccount virtualBankAccount;

  private DeleteVirtualBankAccountResponse(Builder builder) {
    super(builder.httpResponse);

    this.virtualBankAccount = builder.virtualBankAccount;
  }

  /** Parse JSON response into DeleteVirtualBankAccountResponse object. */
  public static DeleteVirtualBankAccountResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteVirtualBankAccountResponse object with HTTP response. */
  public static DeleteVirtualBankAccountResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteVirtualBankAccountResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteVirtualBankAccountResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteVirtualBankAccountResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Response httpResponse;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteVirtualBankAccountResponse build() {
      return new DeleteVirtualBankAccountResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
