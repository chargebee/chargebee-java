package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for VirtualBankAccountCreate operation. Contains the response data from
 * the API.
 */
public final class VirtualBankAccountCreateResponse {

  private final VirtualBankAccount virtualBankAccount;

  private final Customer customer;

  private VirtualBankAccountCreateResponse(Builder builder) {

    this.virtualBankAccount = builder.virtualBankAccount;

    this.customer = builder.customer;
  }

  /** Parse JSON response into VirtualBankAccountCreateResponse object. */
  public static VirtualBankAccountCreateResponse fromJson(String json) {
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

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountCreateResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Customer customer;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public VirtualBankAccountCreateResponse build() {
      return new VirtualBankAccountCreateResponse(this);
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
