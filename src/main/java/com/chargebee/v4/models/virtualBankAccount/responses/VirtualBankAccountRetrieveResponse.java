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

  private VirtualBankAccountRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.virtualBankAccount = builder.virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object with HTTP response. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountRetrieveResponse. */
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

    public VirtualBankAccountRetrieveResponse build() {
      return new VirtualBankAccountRetrieveResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }
}
