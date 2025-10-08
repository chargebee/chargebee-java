package com.chargebee.v4.core.responses.virtualBankAccount;

import com.chargebee.v4.core.models.virtualBankAccount.VirtualBankAccount;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VirtualBankAccountRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class VirtualBankAccountRetrieveResponse {

  private final VirtualBankAccount virtualBankAccount;

  private final Response httpResponse;

  private VirtualBankAccountRetrieveResponse(
      VirtualBankAccount virtualBankAccount, Response httpResponse) {

    this.virtualBankAccount = virtualBankAccount;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VirtualBankAccountRetrieveResponse object with HTTP response. */
  public static VirtualBankAccountRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      VirtualBankAccount virtualBankAccount =
          VirtualBankAccount.fromJson(JsonUtil.getObject(json, "virtual_bank_account"));

      return new VirtualBankAccountRetrieveResponse(virtualBankAccount, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountRetrieveResponse from JSON", e);
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
