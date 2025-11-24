package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CollectPaymentForCustomer operation. Contains the response data
 * from the API.
 */
public final class CollectPaymentForCustomerResponse extends BaseResponse {
  private final Customer customer;

  private final Transaction transaction;

  private CollectPaymentForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into CollectPaymentForCustomerResponse object. */
  public static CollectPaymentForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CollectPaymentForCustomerResponse object with HTTP response. */
  public static CollectPaymentForCustomerResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CollectPaymentForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CollectPaymentForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CollectPaymentForCustomerResponse. */
  public static class Builder {

    private Customer customer;

    private Transaction transaction;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CollectPaymentForCustomerResponse build() {
      return new CollectPaymentForCustomerResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
