package com.chargebee.v4.core.responses.address;

import com.chargebee.v4.core.models.address.Address;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for AddressUpdate operation. Contains the response data from the API.
 */
public final class AddressUpdateResponse {

  private final Address address;

  private AddressUpdateResponse(Builder builder) {

    this.address = builder.address;
  }

  /** Parse JSON response into AddressUpdateResponse object. */
  public static AddressUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __addressJson = JsonUtil.getObject(json, "address");
      if (__addressJson != null) {
        builder.address(Address.fromJson(__addressJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for AddressUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddressUpdateResponse. */
  public static class Builder {

    private Address address;

    private Builder() {}

    public Builder address(Address address) {
      this.address = address;
      return this;
    }

    public AddressUpdateResponse build() {
      return new AddressUpdateResponse(this);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
