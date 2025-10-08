package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountDelete operation. Contains the response data from
 * the API.
 */
public final class VirtualBankAccountDeleteResponse {

  private final VirtualBankAccount virtualBankAccount;

  private final Response httpResponse;

  private VirtualBankAccountDeleteResponse(Builder builder) {

    this.virtualBankAccount = builder.virtualBankAccount;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into VirtualBankAccountDeleteResponse object. */
  public static VirtualBankAccountDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountDeleteResponse object with HTTP response. */
  public static VirtualBankAccountDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __virtualBankAccountJson = JsonUtil.getObject(json, "virtual_bank_account");
      if (__virtualBankAccountJson != null) {
        builder.virtualBankAccount(VirtualBankAccount.fromJson(__virtualBankAccountJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountDeleteResponse. */
  public static class Builder {

    private VirtualBankAccount virtualBankAccount;

    private Response httpResponse;

    private Builder() {}

    public Builder virtualBankAccount(VirtualBankAccount virtualBankAccount) {
      this.virtualBankAccount = virtualBankAccount;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public VirtualBankAccountDeleteResponse build() {
      return new VirtualBankAccountDeleteResponse(this);
    }
  }

  /** Get the virtualBankAccount from the response. */
  public VirtualBankAccount getVirtualBankAccount() {
    return virtualBankAccount;
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
