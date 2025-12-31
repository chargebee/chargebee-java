package com.chargebee.v4.models.promotionalCredit.responses;

import com.chargebee.v4.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalCreditRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PromotionalCreditRetrieveResponse extends BaseResponse {
  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.promotionalCredit = builder.promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object. */
  public static PromotionalCreditRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object with HTTP response. */
  public static PromotionalCreditRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __promotionalCreditJson = JsonUtil.getObject(json, "promotional_credit");
      if (__promotionalCreditJson != null) {
        builder.promotionalCredit(PromotionalCredit.fromJson(__promotionalCreditJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PromotionalCreditRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PromotionalCreditRetrieveResponse. */
  public static class Builder {

    private PromotionalCredit promotionalCredit;

    private Response httpResponse;

    private Builder() {}

    public Builder promotionalCredit(PromotionalCredit promotionalCredit) {
      this.promotionalCredit = promotionalCredit;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PromotionalCreditRetrieveResponse build() {
      return new PromotionalCreditRetrieveResponse(this);
    }
  }

  /** Get the promotionalCredit from the response. */
  public PromotionalCredit getPromotionalCredit() {
    return promotionalCredit;
  }

  @Override
  public String toString() {
    return "PromotionalCreditRetrieveResponse{" + "promotionalCredit=" + promotionalCredit + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PromotionalCreditRetrieveResponse that = (PromotionalCreditRetrieveResponse) o;
    return java.util.Objects.equals(promotionalCredit, that.promotionalCredit);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(promotionalCredit);
  }
}
