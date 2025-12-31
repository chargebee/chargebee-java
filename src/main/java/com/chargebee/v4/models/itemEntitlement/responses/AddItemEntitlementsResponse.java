package com.chargebee.v4.models.itemEntitlement.responses;

import com.chargebee.v4.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddItemEntitlements operation. Contains the response data from the
 * API.
 */
public final class AddItemEntitlementsResponse extends BaseResponse {
  private final ItemEntitlement itemEntitlement;

  private AddItemEntitlementsResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemEntitlement = builder.itemEntitlement;
  }

  /** Parse JSON response into AddItemEntitlementsResponse object. */
  public static AddItemEntitlementsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddItemEntitlementsResponse object with HTTP response. */
  public static AddItemEntitlementsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        builder.itemEntitlement(ItemEntitlement.fromJson(__itemEntitlementJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddItemEntitlementsResponse from JSON", e);
    }
  }

  /** Create a new builder for AddItemEntitlementsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddItemEntitlementsResponse. */
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

    public AddItemEntitlementsResponse build() {
      return new AddItemEntitlementsResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }

  @Override
  public String toString() {
    return "AddItemEntitlementsResponse{" + "itemEntitlement=" + itemEntitlement + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddItemEntitlementsResponse that = (AddItemEntitlementsResponse) o;
    return java.util.Objects.equals(itemEntitlement, that.itemEntitlement);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(itemEntitlement);
  }
}
