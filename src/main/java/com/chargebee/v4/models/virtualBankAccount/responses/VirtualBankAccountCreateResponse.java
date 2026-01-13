package com.chargebee.v4.models.virtualBankAccount.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountCreate operation. Contains the response data from
 * the API.
 */
public final class VirtualBankAccountCreateResponse extends BaseResponse {
  private final VirtualBankAccount virtualBankAccount;

  private final Customer customer;

  private VirtualBankAccountCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.virtualBankAccount = builder.virtualBankAccount;

    this.customer = builder.customer;
  }

  /** Parse JSON response into VirtualBankAccountCreateResponse object. */
  public static VirtualBankAccountCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountCreateResponse object with HTTP response. */
  public static VirtualBankAccountCreateResponse fromJson(String json, Response httpResponse) {
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

  @Override
  public String toString() {
    return "VirtualBankAccountCreateResponse{"
        + "virtualBankAccount="
        + virtualBankAccount
        + ", customer="
        + customer
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VirtualBankAccountCreateResponse that = (VirtualBankAccountCreateResponse) o;
    return java.util.Objects.equals(virtualBankAccount, that.virtualBankAccount)
        && java.util.Objects.equals(customer, that.customer);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(virtualBankAccount, customer);
  }
}
