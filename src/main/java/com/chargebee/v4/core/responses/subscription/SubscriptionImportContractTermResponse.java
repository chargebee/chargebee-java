package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.contractTerm.ContractTerm;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionImportContractTerm operation. Contains the response
 * data from the API.
 */
public final class SubscriptionImportContractTermResponse {

  private final ContractTerm contractTerm;

  private final Response httpResponse;

  private SubscriptionImportContractTermResponse(Builder builder) {

    this.contractTerm = builder.contractTerm;

    this.httpResponse = builder.httpResponse;
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
