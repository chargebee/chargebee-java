package com.chargebee.v4.models.omnichannelOneTimeOrder.responses;

import com.chargebee.v4.models.omnichannelOneTimeOrder.OmnichannelOneTimeOrder;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelOneTimeOrderRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelOneTimeOrderRetrieveResponse extends BaseResponse {
  private final OmnichannelOneTimeOrder omnichannelOneTimeOrder;

  private OmnichannelOneTimeOrderRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.omnichannelOneTimeOrder = builder.omnichannelOneTimeOrder;
  }

  /** Parse JSON response into OmnichannelOneTimeOrderRetrieveResponse object. */
  public static OmnichannelOneTimeOrderRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelOneTimeOrderRetrieveResponse object with HTTP response. */
  public static OmnichannelOneTimeOrderRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __omnichannelOneTimeOrderJson = JsonUtil.getObject(json, "omnichannel_one_time_order");
      if (__omnichannelOneTimeOrderJson != null) {
        builder.omnichannelOneTimeOrder(
            OmnichannelOneTimeOrder.fromJson(__omnichannelOneTimeOrderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for OmnichannelOneTimeOrderRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OmnichannelOneTimeOrderRetrieveResponse. */
  public static class Builder {

    private OmnichannelOneTimeOrder omnichannelOneTimeOrder;

    private Response httpResponse;

    private Builder() {}

    public Builder omnichannelOneTimeOrder(OmnichannelOneTimeOrder omnichannelOneTimeOrder) {
      this.omnichannelOneTimeOrder = omnichannelOneTimeOrder;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OmnichannelOneTimeOrderRetrieveResponse build() {
      return new OmnichannelOneTimeOrderRetrieveResponse(this);
    }
  }

  /** Get the omnichannelOneTimeOrder from the response. */
  public OmnichannelOneTimeOrder getOmnichannelOneTimeOrder() {
    return omnichannelOneTimeOrder;
  }

  @Override
  public String toString() {
    return "OmnichannelOneTimeOrderRetrieveResponse{"
        + "omnichannelOneTimeOrder="
        + omnichannelOneTimeOrder
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OmnichannelOneTimeOrderRetrieveResponse that = (OmnichannelOneTimeOrderRetrieveResponse) o;
    return java.util.Objects.equals(omnichannelOneTimeOrder, that.omnichannelOneTimeOrder);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(omnichannelOneTimeOrder);
  }
}
