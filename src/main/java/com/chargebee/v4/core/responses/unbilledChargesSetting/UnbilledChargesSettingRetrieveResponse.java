package com.chargebee.v4.core.responses.unbilledChargesSetting;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargesSettingRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class UnbilledChargesSettingRetrieveResponse extends BaseResponse {
  private final Object unbilledChargesSetting;

  private UnbilledChargesSettingRetrieveResponse(
      Object unbilledChargesSetting, Response httpResponse) {
    super(httpResponse);

    this.unbilledChargesSetting = unbilledChargesSetting;
  }

  /** Parse JSON response into UnbilledChargesSettingRetrieveResponse object. */
  public static UnbilledChargesSettingRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UnbilledChargesSettingRetrieveResponse object with HTTP response. */
  public static UnbilledChargesSettingRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      Object unbilledChargesSetting = JsonUtil.getObject(json, "unbilled_charges_setting");

      return new UnbilledChargesSettingRetrieveResponse(unbilledChargesSetting, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargesSettingRetrieveResponse from JSON", e);
    }
  }

  /** Get the unbilledChargesSetting from the response. */
  public Object getUnbilledChargesSetting() {
    return unbilledChargesSetting;
  }
}
