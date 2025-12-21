package com.chargebee.v4.models.address.responses;

import com.chargebee.v4.models.address.Address;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddressRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddressRetrieveResponse extends BaseResponse {
  private final Address address;

  private AddressRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.address = builder.address;
  }

  /** Parse JSON response into AddressRetrieveResponse object. */
  public static AddressRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddressRetrieveResponse object with HTTP response. */
  public static AddressRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addressJson = JsonUtil.getObject(json, "address");
      if (__addressJson != null) {
        builder.address(Address.fromJson(__addressJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddressRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for AddressRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddressRetrieveResponse. */
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

    public AddressRetrieveResponse build() {
      return new AddressRetrieveResponse(this);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }
}
