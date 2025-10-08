package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CardCopyCardForCustomer operation. Contains the response data from
 * the API.
 */
public final class CardCopyCardForCustomerResponse {

  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private final Response httpResponse;

  private CardCopyCardForCustomerResponse(Builder builder) {

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CardCopyCardForCustomerResponse object. */
  public static CardCopyCardForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CardCopyCardForCustomerResponse object with HTTP response. */
  public static CardCopyCardForCustomerResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __thirdPartyPaymentMethodJson = JsonUtil.getObject(json, "third_party_payment_method");
      if (__thirdPartyPaymentMethodJson != null) {
        builder.thirdPartyPaymentMethod(
            ThirdPartyPaymentMethod.fromJson(__thirdPartyPaymentMethodJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardCopyCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CardCopyCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardCopyCardForCustomerResponse. */
  public static class Builder {

    private ThirdPartyPaymentMethod thirdPartyPaymentMethod;

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartyPaymentMethod(ThirdPartyPaymentMethod thirdPartyPaymentMethod) {
      this.thirdPartyPaymentMethod = thirdPartyPaymentMethod;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CardCopyCardForCustomerResponse build() {
      return new CardCopyCardForCustomerResponse(this);
    }
  }

  /** Get the thirdPartyPaymentMethod from the response. */
  public ThirdPartyPaymentMethod getThirdPartyPaymentMethod() {
    return thirdPartyPaymentMethod;
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
