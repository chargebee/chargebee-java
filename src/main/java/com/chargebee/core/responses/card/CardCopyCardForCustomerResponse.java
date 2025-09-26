package com.chargebee.core.responses.card;

import com.chargebee.core.models.thirdPartyPaymentMethod.ThirdPartyPaymentMethod;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CardCopyCardForCustomer operation. Contains the response data from
 * the API.
 */
public final class CardCopyCardForCustomerResponse {

  private final ThirdPartyPaymentMethod thirdPartyPaymentMethod;

  private CardCopyCardForCustomerResponse(Builder builder) {

    this.thirdPartyPaymentMethod = builder.thirdPartyPaymentMethod;
  }

  /** Parse JSON response into CardCopyCardForCustomerResponse object. */
  public static CardCopyCardForCustomerResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __thirdPartyPaymentMethodJson = JsonUtil.getObject(json, "third_party_payment_method");
      if (__thirdPartyPaymentMethodJson != null) {
        builder.thirdPartyPaymentMethod(
            ThirdPartyPaymentMethod.fromJson(__thirdPartyPaymentMethodJson));
      }

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

    private Builder() {}

    public Builder thirdPartyPaymentMethod(ThirdPartyPaymentMethod thirdPartyPaymentMethod) {
      this.thirdPartyPaymentMethod = thirdPartyPaymentMethod;
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
}
