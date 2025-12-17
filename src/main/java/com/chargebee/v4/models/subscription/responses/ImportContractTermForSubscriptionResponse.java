package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.contractTerm.ContractTerm;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ImportContractTermForSubscription operation. Contains the response
 * data from the API.
 */
public final class ImportContractTermForSubscriptionResponse extends BaseResponse {
  private final ContractTerm contractTerm;

  private ImportContractTermForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.contractTerm = builder.contractTerm;
  }

  /** Parse JSON response into ImportContractTermForSubscriptionResponse object. */
  public static ImportContractTermForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ImportContractTermForSubscriptionResponse object with HTTP response.
   */
  public static ImportContractTermForSubscriptionResponse fromJson(
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
          "Failed to parse ImportContractTermForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for ImportContractTermForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ImportContractTermForSubscriptionResponse. */
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

    public ImportContractTermForSubscriptionResponse build() {
      return new ImportContractTermForSubscriptionResponse(this);
    }
  }

  /** Get the contractTerm from the response. */
  public ContractTerm getContractTerm() {
    return contractTerm;
  }
}
