package com.chargebee.v4.core.responses.unbilledChargesSetting;

/**
 * Immutable response object for UnbilledChargesSettingRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class UnbilledChargesSettingRetrieveResponse {

  private UnbilledChargesSettingRetrieveResponse() {}

  /** Parse JSON response into UnbilledChargesSettingRetrieveResponse object. */
  public static UnbilledChargesSettingRetrieveResponse fromJson(String json) {
    try {

      return new UnbilledChargesSettingRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargesSettingRetrieveResponse from JSON", e);
    }
  }
}
