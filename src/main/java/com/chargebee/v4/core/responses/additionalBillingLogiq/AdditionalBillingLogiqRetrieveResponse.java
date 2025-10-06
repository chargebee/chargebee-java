package com.chargebee.v4.core.responses.additionalBillingLogiq;

/**
 * Immutable response object for AdditionalBillingLogiqRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class AdditionalBillingLogiqRetrieveResponse {

  private AdditionalBillingLogiqRetrieveResponse() {}

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(String json) {
    try {

      return new AdditionalBillingLogiqRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AdditionalBillingLogiqRetrieveResponse from JSON", e);
    }
  }
}
