package com.chargebee.v4.core.responses.businessEntity;

import com.chargebee.v4.core.models.businessEntityTransfer.BusinessEntityTransfer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessEntityCreateTransfers operation. Contains the response data
 * from the API.
 */
public final class BusinessEntityCreateTransfersResponse {

  private final BusinessEntityTransfer businessEntityTransfer;

  private final Response httpResponse;

  private BusinessEntityCreateTransfersResponse(Builder builder) {

    this.businessEntityTransfer = builder.businessEntityTransfer;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into BusinessEntityCreateTransfersResponse object. */
  public static BusinessEntityCreateTransfersResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessEntityCreateTransfersResponse object with HTTP response. */
  public static BusinessEntityCreateTransfersResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __businessEntityTransferJson = JsonUtil.getObject(json, "business_entity_transfer");
      if (__businessEntityTransferJson != null) {
        builder.businessEntityTransfer(
            BusinessEntityTransfer.fromJson(__businessEntityTransferJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse BusinessEntityCreateTransfersResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessEntityCreateTransfersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessEntityCreateTransfersResponse. */
  public static class Builder {

    private BusinessEntityTransfer businessEntityTransfer;

    private Response httpResponse;

    private Builder() {}

    public Builder businessEntityTransfer(BusinessEntityTransfer businessEntityTransfer) {
      this.businessEntityTransfer = businessEntityTransfer;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessEntityCreateTransfersResponse build() {
      return new BusinessEntityCreateTransfersResponse(this);
    }
  }

  /** Get the businessEntityTransfer from the response. */
  public BusinessEntityTransfer getBusinessEntityTransfer() {
    return businessEntityTransfer;
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
