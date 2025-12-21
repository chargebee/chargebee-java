package com.chargebee.v4.models.itemEntitlement.responses;

import com.chargebee.v4.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpsertOrRemoveItemEntitlementsForItem operation. Contains the
 * response data from the API.
 */
public final class UpsertOrRemoveItemEntitlementsForItemResponse extends BaseResponse {
  private final ItemEntitlement itemEntitlement;

  private UpsertOrRemoveItemEntitlementsForItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemEntitlement = builder.itemEntitlement;
  }

  /** Parse JSON response into UpsertOrRemoveItemEntitlementsForItemResponse object. */
  public static UpsertOrRemoveItemEntitlementsForItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into UpsertOrRemoveItemEntitlementsForItemResponse object with HTTP
   * response.
   */
  public static UpsertOrRemoveItemEntitlementsForItemResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        builder.itemEntitlement(ItemEntitlement.fromJson(__itemEntitlementJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UpsertOrRemoveItemEntitlementsForItemResponse from JSON", e);
    }
  }

  /** Create a new builder for UpsertOrRemoveItemEntitlementsForItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpsertOrRemoveItemEntitlementsForItemResponse. */
  public static class Builder {

    private ItemEntitlement itemEntitlement;

    private Response httpResponse;

    private Builder() {}

    public Builder itemEntitlement(ItemEntitlement itemEntitlement) {
      this.itemEntitlement = itemEntitlement;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UpsertOrRemoveItemEntitlementsForItemResponse build() {
      return new UpsertOrRemoveItemEntitlementsForItemResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }
}
