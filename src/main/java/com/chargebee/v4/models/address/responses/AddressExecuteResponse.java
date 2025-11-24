package com.chargebee.v4.models.address.responses;

import com.chargebee.v4.models.address.Address;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddressExecute operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddressExecuteResponse extends BaseResponse {
  private final Address address;

  private AddressExecuteResponse(Address address, Response httpResponse) {
    super(httpResponse);

    this.address = address;
  }

  /** Parse JSON response into AddressExecuteResponse object. */
  public static AddressExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddressExecuteResponse object with HTTP response. */
  public static AddressExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      Address address = Address.fromJson(JsonUtil.getObject(json, "address"));

      return new AddressExecuteResponse(address, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressExecuteResponse from JSON", e);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
