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

  private AddressExecuteResponse(Builder builder) {
    super(builder.httpResponse);

    this.address = builder.address;
  }

  /** Parse JSON response into AddressExecuteResponse object. */
  public static AddressExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddressExecuteResponse object with HTTP response. */
  public static AddressExecuteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addressJson = JsonUtil.getObject(json, "address");
      if (__addressJson != null) {
        builder.address(Address.fromJson(__addressJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressExecuteResponse from JSON", e);
    }
  }

  /** Create a new builder for AddressExecuteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddressExecuteResponse. */
  public static class Builder {

    private Address address;

    private Response httpResponse;

    private Builder() {}

    public Builder address(Address address) {
      this.address = address;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AddressExecuteResponse build() {
      return new AddressExecuteResponse(this);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
