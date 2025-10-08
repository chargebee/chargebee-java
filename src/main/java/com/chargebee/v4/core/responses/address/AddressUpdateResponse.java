package com.chargebee.v4.core.responses.address;

import com.chargebee.v4.core.models.address.Address;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddressUpdate operation. Contains the response data from the API.
 */
public final class AddressUpdateResponse {

  private final Address address;

  private final Response httpResponse;

  private AddressUpdateResponse(Builder builder) {

    this.address = builder.address;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into AddressUpdateResponse object. */
  public static AddressUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddressUpdateResponse object with HTTP response. */
  public static AddressUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addressJson = JsonUtil.getObject(json, "address");
      if (__addressJson != null) {
        builder.address(Address.fromJson(__addressJson));
      }

      builder.httpResponse(httpResponse);
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

    public AddressUpdateResponse build() {
      return new AddressUpdateResponse(this);
    }
  }

  /** Get the address from the response. */
  public Address getAddress() {
    return address;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
