package com.chargebee.v4.core.responses.address;

import com.chargebee.v4.core.models.address.Address;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddressRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddressRetrieveResponse {

  private final Address address;

  private final Response httpResponse;

  private AddressRetrieveResponse(Address address, Response httpResponse) {

    this.address = address;

    this.httpResponse = httpResponse;
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
