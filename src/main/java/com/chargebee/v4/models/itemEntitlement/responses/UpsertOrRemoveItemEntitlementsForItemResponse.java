package com.chargebee.v4.models.itemEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpsertOrRemoveItemEntitlementsForItem operation. Contains the
 * response data from the API.
 */
public final class UpsertOrRemoveItemEntitlementsForItemResponse extends BaseResponse {
  private final List<ItemEntitlement> list;

  private UpsertOrRemoveItemEntitlementsForItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
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
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.list(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), ItemEntitlement::fromJson));

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

    private List<ItemEntitlement> list;

    private Response httpResponse;

    private Builder() {}

    public Builder list(List<ItemEntitlement> list) {
      this.list = list;
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

  /** Get the list from the response. */
  public List<ItemEntitlement> getList() {
    return list;
  }

  @Override
  public String toString() {
    return "UpsertOrRemoveItemEntitlementsForItemResponse{" + "list=" + list + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UpsertOrRemoveItemEntitlementsForItemResponse that =
        (UpsertOrRemoveItemEntitlementsForItemResponse) o;
    return java.util.Objects.equals(list, that.list);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list);
  }
}
