package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.contractTerm.ContractTerm;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionImportContractTerm operation. Contains the response
 * data from the API.
 */
public final class SubscriptionImportContractTermResponse extends BaseResponse {
  private final ContractTerm contractTerm;

  private SubscriptionImportContractTermResponse(Builder builder) {
    super(builder.httpResponse);

    this.contractTerm = builder.contractTerm;
  }

  /** Parse JSON response into SubscriptionImportContractTermResponse object. */
  public static SubscriptionImportContractTermResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionImportContractTermResponse object with HTTP response. */
  public static SubscriptionImportContractTermResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __contractTermJson = JsonUtil.getObject(json, "contract_term");
      if (__contractTermJson != null) {
        builder.contractTerm(ContractTerm.fromJson(__contractTermJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionImportContractTermResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionImportContractTermResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionImportContractTermResponse. */
  public static class Builder {

    private ContractTerm contractTerm;

    private Response httpResponse;

    private Builder() {}

    public Builder contractTerm(ContractTerm contractTerm) {
      this.contractTerm = contractTerm;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionImportContractTermResponse build() {
      return new SubscriptionImportContractTermResponse(this);
    }
  }

  /** Get the contractTerm from the response. */
  public ContractTerm getContractTerm() {
    return contractTerm;
  }

  @Override
  public String toString() {
    return "SubscriptionImportContractTermResponse{" + "contractTerm=" + contractTerm + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionImportContractTermResponse that = (SubscriptionImportContractTermResponse) o;
    return java.util.Objects.equals(contractTerm, that.contractTerm);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(contractTerm);
  }
}
