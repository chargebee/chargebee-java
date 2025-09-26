package com.chargebee.core.responses.subscription;

import com.chargebee.core.models.contractTerm.ContractTerm;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionImportContractTerm operation. Contains the response
 * data from the API.
 */
public final class SubscriptionImportContractTermResponse {

  private final ContractTerm contractTerm;

  private SubscriptionImportContractTermResponse(Builder builder) {

    this.contractTerm = builder.contractTerm;
  }

  /** Parse JSON response into SubscriptionImportContractTermResponse object. */
  public static SubscriptionImportContractTermResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __contractTermJson = JsonUtil.getObject(json, "contract_term");
      if (__contractTermJson != null) {
        builder.contractTerm(ContractTerm.fromJson(__contractTermJson));
      }

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

    private Builder() {}

    public Builder contractTerm(ContractTerm contractTerm) {
      this.contractTerm = contractTerm;
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
}
