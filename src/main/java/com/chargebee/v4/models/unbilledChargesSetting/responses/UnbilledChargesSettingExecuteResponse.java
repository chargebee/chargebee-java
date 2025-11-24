package com.chargebee.v4.models.unbilledChargesSetting.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargesSettingExecute operation. Contains the response data
 * from a single resource get operation.
 */
public final class UnbilledChargesSettingExecuteResponse extends BaseResponse {
  private final Object unbilledChargesSetting;

  private UnbilledChargesSettingExecuteResponse(
      Object unbilledChargesSetting, Response httpResponse) {
    super(httpResponse);

    this.unbilledChargesSetting = unbilledChargesSetting;
  }

  /** Parse JSON response into UnbilledChargesSettingExecuteResponse object. */
  public static UnbilledChargesSettingExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UnbilledChargesSettingExecuteResponse object with HTTP response. */
  public static UnbilledChargesSettingExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      Object unbilledChargesSetting = JsonUtil.getObject(json, "unbilled_charges_setting");

      return new UnbilledChargesSettingExecuteResponse(unbilledChargesSetting, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargesSettingExecuteResponse from JSON", e);
    }
  }

  /** Get the unbilledChargesSetting from the response. */
  public Object getUnbilledChargesSetting() {
    return unbilledChargesSetting;
  }
}
