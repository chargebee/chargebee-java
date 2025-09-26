package com.chargebee.core.responses.address;

import com.chargebee.core.models.address.Address;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AddressRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddressRetrieveResponse {

  private final Address address;

  private AddressRetrieveResponse(Address address) {

    this.address = address;
  }

  /** Parse JSON response into AddressRetrieveResponse object. */
  public static AddressRetrieveResponse fromJson(String json) {
    try {

      Address address = Address.fromJson(JsonUtil.getObject(json, "address"));

      return new AddressRetrieveResponse(address);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressRetrieveResponse from JSON", e);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
