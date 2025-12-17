package com.chargebee.v4.models.businessEntity.responses;

import com.chargebee.v4.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessEntityTransfers operation. Contains the response data from
 * the API.
 */
public final class BusinessEntityTransfersResponse extends BaseResponse {
  private final BusinessEntityTransfer businessEntityTransfer;

  private BusinessEntityTransfersResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessEntityTransfer = builder.businessEntityTransfer;
  }

  /** Parse JSON response into BusinessEntityTransfersResponse object. */
  public static BusinessEntityTransfersResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessEntityTransfersResponse object with HTTP response. */
  public static BusinessEntityTransfersResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __businessEntityTransferJson = JsonUtil.getObject(json, "business_entity_transfer");
      if (__businessEntityTransferJson != null) {
        builder.businessEntityTransfer(
            BusinessEntityTransfer.fromJson(__businessEntityTransferJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessEntityTransfersResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityTransfersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityTransfersResponse. */
  public static class Builder {

    private BusinessEntityTransfer businessEntityTransfer;

    private Response httpResponse;

    private Builder() {}

    public Builder businessEntityTransfer(BusinessEntityTransfer businessEntityTransfer) {
      this.businessEntityTransfer = businessEntityTransfer;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessEntityTransfersResponse build() {
      return new BusinessEntityTransfersResponse(this);
    }
  }

  /** Get the businessEntityTransfer from the response. */
  public BusinessEntityTransfer getBusinessEntityTransfer() {
    return businessEntityTransfer;
  }
}
