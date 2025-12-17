package com.chargebee.v4.models.itemEntitlement.responses;

import com.chargebee.v4.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddItemEntitlementForItem operation. Contains the response data
 * from the API.
 */
public final class AddItemEntitlementForItemResponse extends BaseResponse {
  private final ItemEntitlement itemEntitlement;

  private AddItemEntitlementForItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemEntitlement = builder.itemEntitlement;
  }

  /** Parse JSON response into AddItemEntitlementForItemResponse object. */
  public static AddItemEntitlementForItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddItemEntitlementForItemResponse object with HTTP response. */
  public static AddItemEntitlementForItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        builder.itemEntitlement(ItemEntitlement.fromJson(__itemEntitlementJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddItemEntitlementForItemResponse from JSON", e);
    }
  }

  /** Create a new builder for AddItemEntitlementForItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddItemEntitlementForItemResponse. */
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

    public AddItemEntitlementForItemResponse build() {
      return new AddItemEntitlementForItemResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }
}
