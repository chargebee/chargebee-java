package com.chargebee.v4.models.businessEntity.responses;

import com.chargebee.v4.models.businessEntity.BusinessEntity;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessEntityRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class BusinessEntityRetrieveResponse extends BaseResponse {
  private final BusinessEntity businessEntity;

  private BusinessEntityRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessEntity = builder.businessEntity;
  }

  /** Parse JSON response into BusinessEntityRetrieveResponse object. */
  public static BusinessEntityRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessEntityRetrieveResponse object with HTTP response. */
  public static BusinessEntityRetrieveResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse BusinessEntityRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityRetrieveResponse. */
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

    public BusinessEntityRetrieveResponse build() {
      return new BusinessEntityRetrieveResponse(this);
    }
  }

  /** Get the businessEntity from the response. */
  public BusinessEntity getBusinessEntity() {
    return businessEntity;
  }

  @Override
  public String toString() {
    return "BusinessEntityRetrieveResponse{" + "businessEntity=" + businessEntity + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessEntityRetrieveResponse that = (BusinessEntityRetrieveResponse) o;
    return java.util.Objects.equals(businessEntity, that.businessEntity);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessEntity);
  }
}
