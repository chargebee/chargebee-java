package com.chargebee.v4.models.unbilledChargesSetting.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargesSettingRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class UnbilledChargesSettingRetrieveResponse extends BaseResponse {
  private final Object unbilledChargesSetting;

  private UnbilledChargesSettingRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.unbilledChargesSetting = builder.unbilledChargesSetting;
  }

  /** Parse JSON response into UnbilledChargesSettingRetrieveResponse object. */
  public static UnbilledChargesSettingRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UnbilledChargesSettingRetrieveResponse object with HTTP response. */
  public static UnbilledChargesSettingRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.unbilledChargesSetting(JsonUtil.getObject(json, "unbilled_charges_setting"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargesSettingRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargesSettingRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargesSettingRetrieveResponse. */
  public static class Builder {

    private Object unbilledChargesSetting;

    private Response httpResponse;

    private Builder() {}

    public Builder unbilledChargesSetting(Object unbilledChargesSetting) {
      this.unbilledChargesSetting = unbilledChargesSetting;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UnbilledChargesSettingRetrieveResponse build() {
      return new UnbilledChargesSettingRetrieveResponse(this);
    }
  }

  /** Get the unbilledChargesSetting from the response. */
  public Object getUnbilledChargesSetting() {
    return unbilledChargesSetting;
  }
}
