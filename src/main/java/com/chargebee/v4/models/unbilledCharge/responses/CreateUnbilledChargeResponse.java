package com.chargebee.v4.models.unbilledCharge.responses;

import java.util.List;

import com.chargebee.v4.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateUnbilledCharge operation. Contains the response data from the
 * API.
 */
public final class CreateUnbilledChargeResponse extends BaseResponse {
  private final List<UnbilledCharge> unbilledCharges;

  private CreateUnbilledChargeResponse(Builder builder) {
    super(builder.httpResponse);

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into CreateUnbilledChargeResponse object. */
  public static CreateUnbilledChargeResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreateUnbilledChargeResponse object with HTTP response. */
  public static CreateUnbilledChargeResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreateUnbilledChargeResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateUnbilledChargeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateUnbilledChargeResponse. */
  public static class Builder {

    private List<UnbilledCharge> unbilledCharges;

    private Response httpResponse;

    private Builder() {}

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreateUnbilledChargeResponse build() {
      return new CreateUnbilledChargeResponse(this);
    }
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }
}
