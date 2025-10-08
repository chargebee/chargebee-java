package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountCreate operation. Contains the response data from
 * the API.
 */
public final class VirtualBankAccountCreateResponse {

  private final VirtualBankAccount virtualBankAccount;

  private final Customer customer;

  private final Response httpResponse;

  private VirtualBankAccountCreateResponse(Builder builder) {

    this.virtualBankAccount = builder.virtualBankAccount;

    this.customer = builder.customer;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
