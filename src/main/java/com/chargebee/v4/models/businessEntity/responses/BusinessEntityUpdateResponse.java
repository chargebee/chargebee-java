package com.chargebee.v4.models.businessEntity.responses;

import com.chargebee.v4.models.businessEntity.BusinessEntity;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessEntityUpdate operation. Contains the response data from the
 * API.
 */
public final class BusinessEntityUpdateResponse extends BaseResponse {
  private final BusinessEntity businessEntity;

  private BusinessEntityUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessEntity = builder.businessEntity;
  }

  /** Parse JSON response into BusinessEntityUpdateResponse object. */
  public static BusinessEntityUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessEntityUpdateResponse object with HTTP response. */
  public static BusinessEntityUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __businessEntityObj = JsonUtil.getJsonObject(jsonObj, "business_entity");
      if (__businessEntityObj != null) {
        builder.businessEntity(BusinessEntity.fromJson(__businessEntityObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityUpdateResponse. */
  public static class Builder {

    private BusinessEntity businessEntity;

    private Response httpResponse;

    private Builder() {}

    public Builder businessEntity(BusinessEntity businessEntity) {
      this.businessEntity = businessEntity;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessEntityUpdateResponse build() {
      return new BusinessEntityUpdateResponse(this);
    }
  }

  /** Get the businessEntity from the response. */
  public BusinessEntity getBusinessEntity() {
    return businessEntity;
  }

  @Override
  public String toString() {
    return "BusinessEntityUpdateResponse{" + "businessEntity=" + businessEntity + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessEntityUpdateResponse that = (BusinessEntityUpdateResponse) o;
    return java.util.Objects.equals(businessEntity, that.businessEntity);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessEntity);
  }
}
