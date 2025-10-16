package com.chargebee.v4.core.responses.itemEntitlement;

import com.chargebee.v4.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemEntitlementAddItemEntitlements operation. Contains the response
 * data from the API.
 */
public final class ItemEntitlementAddItemEntitlementsResponse extends BaseResponse {
  private final ItemEntitlement itemEntitlement;

  private ItemEntitlementAddItemEntitlementsResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemEntitlement = builder.itemEntitlement;
  }

  /** Parse JSON response into ItemEntitlementAddItemEntitlementsResponse object. */
  public static ItemEntitlementAddItemEntitlementsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ItemEntitlementAddItemEntitlementsResponse object with HTTP response.
   */
  public static ItemEntitlementAddItemEntitlementsResponse fromJson(
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
          "Failed to parse ItemEntitlementAddItemEntitlementsResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemEntitlementAddItemEntitlementsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemEntitlementAddItemEntitlementsResponse. */
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

    public ItemEntitlementAddItemEntitlementsResponse build() {
      return new ItemEntitlementAddItemEntitlementsResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }
}
