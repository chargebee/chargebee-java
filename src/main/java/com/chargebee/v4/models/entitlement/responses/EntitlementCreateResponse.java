package com.chargebee.v4.models.entitlement.responses;

import java.util.List;

import com.chargebee.v4.models.entitlement.Entitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EntitlementCreate operation. Contains the response data from the
 * API.
 */
public final class EntitlementCreateResponse extends BaseResponse {
  private final List<Entitlement> list;

  private EntitlementCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /** Parse JSON response into EntitlementCreateResponse object. */
  public static EntitlementCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EntitlementCreateResponse object with HTTP response. */
  public static EntitlementCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.list(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(Entitlement::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for EntitlementCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EntitlementCreateResponse. */
  public static class Builder {

    private List<Entitlement> list;

    private Response httpResponse;

    private Builder() {}

    public Builder list(List<Entitlement> list) {
      this.list = list;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EntitlementCreateResponse build() {
      return new EntitlementCreateResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<Entitlement> getList() {
    return list;
  }
}
