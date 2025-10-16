package com.chargebee.v4.core.responses.address;

import com.chargebee.v4.core.models.address.Address;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddressRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddressRetrieveResponse extends BaseResponse {
  private final Address address;

  private AddressRetrieveResponse(Address address, Response httpResponse) {
    super(httpResponse);

    this.address = address;
  }

  /** Parse JSON response into AddressRetrieveResponse object. */
  public static AddressRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddressRetrieveResponse object with HTTP response. */
  public static AddressRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Address address = Address.fromJson(JsonUtil.getObject(json, "address"));

      return new AddressRetrieveResponse(address, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressRetrieveResponse from JSON", e);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
