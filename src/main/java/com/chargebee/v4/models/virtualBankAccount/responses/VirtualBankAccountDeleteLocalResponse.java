package com.chargebee.v4.models.virtualBankAccount.responses;

import com.chargebee.v4.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountDeleteLocal operation. Contains the response data
 * from the API.
 */
public final class VirtualBankAccountDeleteLocalResponse extends BaseResponse {
  private final VirtualBankAccount virtualBankAccount;

  private VirtualBankAccountDeleteLocalResponse(Builder builder) {
    super(builder.httpResponse);

    this.virtualBankAccount = builder.virtualBankAccount;
  }

  /** Parse JSON response into VirtualBankAccountDeleteLocalResponse object. */
  public static VirtualBankAccountDeleteLocalResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountDeleteLocalResponse object with HTTP response. */
  public static VirtualBankAccountDeleteLocalResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      builder.httpResponse(httpResponse);
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

    public VirtualBankAccountDeleteLocalResponse build() {
      return new VirtualBankAccountDeleteLocalResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }

  @Override
  public String toString() {
    return "VirtualBankAccountDeleteLocalResponse{"
        + "virtualBankAccount="
        + virtualBankAccount
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VirtualBankAccountDeleteLocalResponse that = (VirtualBankAccountDeleteLocalResponse) o;
    return java.util.Objects.equals(virtualBankAccount, that.virtualBankAccount);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(virtualBankAccount);
  }
}
