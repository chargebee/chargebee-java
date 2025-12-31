package com.chargebee.v4.models.additionalBillingLogiq.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AdditionalBillingLogiqRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class AdditionalBillingLogiqRetrieveResponse extends BaseResponse {
  private final Object additionalBillingLogiq;

  private AdditionalBillingLogiqRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.additionalBillingLogiq = builder.additionalBillingLogiq;
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object with HTTP response. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.additionalBillingLogiq(JsonUtil.getObject(json, "additional_billing_logiq"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AdditionalBillingLogiqRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for AdditionalBillingLogiqRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AdditionalBillingLogiqRetrieveResponse. */
  public static class Builder {

    private Object additionalBillingLogiq;

    private Response httpResponse;

    private Builder() {}

    public Builder additionalBillingLogiq(Object additionalBillingLogiq) {
      this.additionalBillingLogiq = additionalBillingLogiq;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AdditionalBillingLogiqRetrieveResponse build() {
      return new AdditionalBillingLogiqRetrieveResponse(this);
    }
  }

  /** Get the additionalBillingLogiq from the response. */
  public Object getAdditionalBillingLogiq() {
    return additionalBillingLogiq;
  }

  @Override
  public String toString() {
    return "AdditionalBillingLogiqRetrieveResponse{"
        + "additionalBillingLogiq="
        + additionalBillingLogiq
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AdditionalBillingLogiqRetrieveResponse that = (AdditionalBillingLogiqRetrieveResponse) o;
    return java.util.Objects.equals(additionalBillingLogiq, that.additionalBillingLogiq);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(additionalBillingLogiq);
  }
}
