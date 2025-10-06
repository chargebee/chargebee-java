package com.chargebee.v4.core.responses.businessProfile;

/**
 * Immutable response object for BusinessProfileRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class BusinessProfileRetrieveResponse {

  private BusinessProfileRetrieveResponse() {}

  /** Parse JSON response into BusinessProfileRetrieveResponse object. */
  public static BusinessProfileRetrieveResponse fromJson(String json) {
    try {

      return new BusinessProfileRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessProfileRetrieveResponse from JSON", e);
    }
  }
}
