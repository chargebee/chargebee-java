package com.chargebee.v4.models.card.responses;

import com.chargebee.v4.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CopyCardForCustomer operation. Contains the response data from the
 * API.
 */
public final class CopyCardForCustomerResponse extends BaseResponse {
  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private CopyCardForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into CopyCardForCustomerResponse object. */
  public static CopyCardForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CopyCardForCustomerResponse object with HTTP response. */
  public static CopyCardForCustomerResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse CopyCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CopyCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CopyCardForCustomerResponse. */
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

    public CopyCardForCustomerResponse build() {
      return new CopyCardForCustomerResponse(this);
    }
  }

  /** Get the thirdPartyPaymentMethod from the response. */
  public ThirdPartyPaymentMethod getThirdPartyPaymentMethod() {
    return thirdPartyPaymentMethod;
  }

  @Override
  public String toString() {
    return "CopyCardForCustomerResponse{"
        + "thirdPartyPaymentMethod="
        + thirdPartyPaymentMethod
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CopyCardForCustomerResponse that = (CopyCardForCustomerResponse) o;
    return java.util.Objects.equals(thirdPartyPaymentMethod, that.thirdPartyPaymentMethod);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(thirdPartyPaymentMethod);
  }
}
