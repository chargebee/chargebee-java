package com.chargebee.v4.models.creditUnit.responses;

import com.chargebee.v4.models.creditUnit.CreditUnit;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditUnitCreate operation. Contains the response data from the
 * API.
 */
public final class CreditUnitCreateResponse extends BaseResponse {
  private final CreditUnit creditUnit;

  private CreditUnitCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditUnit = builder.creditUnit;
  }

  /** Parse JSON response into CreditUnitCreateResponse object. */
  public static CreditUnitCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditUnitCreateResponse object with HTTP response. */
  public static CreditUnitCreateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __creditUnitObj = JsonUtil.getJsonObject(jsonObj, "credit_unit");
      if (__creditUnitObj != null) {
        builder.creditUnit(CreditUnit.fromJson(__creditUnitObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditUnitCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditUnitCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditUnitCreateResponse. */
  public static class Builder {

    private CreditUnit creditUnit;

    private Response httpResponse;

    private Builder() {}

    public Builder creditUnit(CreditUnit creditUnit) {
      this.creditUnit = creditUnit;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreditUnitCreateResponse build() {
      return new CreditUnitCreateResponse(this);
    }
  }

  /** Get the creditUnit from the response. */
  public CreditUnit getCreditUnit() {
    return creditUnit;
  }

  @Override
  public String toString() {
    return "CreditUnitCreateResponse{" + "creditUnit=" + creditUnit + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreditUnitCreateResponse that = (CreditUnitCreateResponse) o;
    return java.util.Objects.equals(creditUnit, that.creditUnit);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(creditUnit);
  }
}
