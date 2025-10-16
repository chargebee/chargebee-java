package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountCreateUsingPermanentToken operation. Contains the
 * response data from the API.
 */
public final class VirtualBankAccountCreateUsingPermanentTokenResponse extends BaseResponse {
  private final VirtualBankAccount virtualBankAccount;

  private final Customer customer;

  private VirtualBankAccountCreateUsingPermanentTokenResponse(Builder builder) {
    super(builder.httpResponse);

    this.virtualBankAccount = builder.virtualBankAccount;

    this.customer = builder.customer;
  }

  /** Parse JSON response into VirtualBankAccountCreateUsingPermanentTokenResponse object. */
  public static VirtualBankAccountCreateUsingPermanentTokenResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into VirtualBankAccountCreateUsingPermanentTokenResponse object with HTTP
   * response.
   */
  public static VirtualBankAccountCreateUsingPermanentTokenResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse VirtualBankAccountCreateUsingPermanentTokenResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountCreateUsingPermanentTokenResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountCreateUsingPermanentTokenResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Customer customer;

    private Response httpResponse;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public VirtualBankAccountCreateUsingPermanentTokenResponse build() {
      return new VirtualBankAccountCreateUsingPermanentTokenResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
