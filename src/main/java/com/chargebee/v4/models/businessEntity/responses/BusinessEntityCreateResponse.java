package com.chargebee.v4.models.businessEntity.responses;

import com.chargebee.v4.models.businessEntity.BusinessEntity;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessEntityCreate operation. Contains the response data from the
 * API.
 */
public final class BusinessEntityCreateResponse extends BaseResponse {
  private final BusinessEntity businessEntity;

  private BusinessEntityCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessEntity = builder.businessEntity;
  }

  /** Parse JSON response into BusinessEntityCreateResponse object. */
  public static BusinessEntityCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessEntityCreateResponse object with HTTP response. */
  public static BusinessEntityCreateResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse BusinessEntityCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityCreateResponse. */
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

    public BusinessEntityCreateResponse build() {
      return new BusinessEntityCreateResponse(this);
    }
  }

  /** Get the businessEntity from the response. */
  public BusinessEntity getBusinessEntity() {
    return businessEntity;
  }

  @Override
  public String toString() {
    return "BusinessEntityCreateResponse{" + "businessEntity=" + businessEntity + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessEntityCreateResponse that = (BusinessEntityCreateResponse) o;
    return java.util.Objects.equals(businessEntity, that.businessEntity);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessEntity);
  }
}
