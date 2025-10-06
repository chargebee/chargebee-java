package com.chargebee.v4.core.responses.businessEntity;

import com.chargebee.v4.core.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for BusinessEntityCreateTransfers operation. Contains the response data
 * from the API.
 */
public final class BusinessEntityCreateTransfersResponse {

  private final BusinessEntityTransfer businessEntityTransfer;

  private BusinessEntityCreateTransfersResponse(Builder builder) {

    this.businessEntityTransfer = builder.businessEntityTransfer;
  }

  /** Parse JSON response into BusinessEntityCreateTransfersResponse object. */
  public static BusinessEntityCreateTransfersResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __businessEntityTransferJson = JsonUtil.getObject(json, "business_entity_transfer");
      if (__businessEntityTransferJson != null) {
        builder.businessEntityTransfer(
            BusinessEntityTransfer.fromJson(__businessEntityTransferJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse BusinessEntityCreateTransfersResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityCreateTransfersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityCreateTransfersResponse. */
  public static class Builder {

    private BusinessEntityTransfer businessEntityTransfer;

    private Builder() {}

    public Builder businessEntityTransfer(BusinessEntityTransfer businessEntityTransfer) {
      this.businessEntityTransfer = businessEntityTransfer;
      return this;
    }

    public BusinessEntityCreateTransfersResponse build() {
      return new BusinessEntityCreateTransfersResponse(this);
    }
  }

  /** Get the businessEntityTransfer from the response. */
  public BusinessEntityTransfer getBusinessEntityTransfer() {
    return businessEntityTransfer;
  }
}
