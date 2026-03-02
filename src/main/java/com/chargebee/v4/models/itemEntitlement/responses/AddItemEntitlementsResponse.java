package com.chargebee.v4.models.itemEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddItemEntitlements operation. Contains the response data from the
 * API.
 */
public final class AddItemEntitlementsResponse extends BaseResponse {
  private final List<ItemEntitlement> list;

  private AddItemEntitlementsResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /** Parse JSON response into AddItemEntitlementsResponse object. */
  public static AddItemEntitlementsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddItemEntitlementsResponse object with HTTP response. */
  public static AddItemEntitlementsResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.list(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), ItemEntitlement::fromJson));

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

    public AddItemEntitlementsResponse build() {
      return new AddItemEntitlementsResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<ItemEntitlement> getList() {
    return list;
  }

  @Override
  public String toString() {
    return "AddItemEntitlementsResponse{" + "list=" + list + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddItemEntitlementsResponse that = (AddItemEntitlementsResponse) o;
    return java.util.Objects.equals(list, that.list);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list);
  }
}
