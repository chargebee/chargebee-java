package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.hierarchy.Hierarchy;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for HierarchyForCustomer operation. Contains the response data from a
 * single resource get operation.
 */
public final class HierarchyForCustomerResponse extends BaseResponse {
  private final List<Hierarchy> hierarchies;

  private HierarchyForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.hierarchies = builder.hierarchies;
  }

  /** Parse JSON response into HierarchyForCustomerResponse object. */
  public static HierarchyForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HierarchyForCustomerResponse object with HTTP response. */
  public static HierarchyForCustomerResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hierarchiesJson = JsonUtil.getArray(json, "hierarchies");
      if (__hierarchiesJson != null) {
        builder.hierarchies(
            JsonUtil.parseObjectArray(__hierarchiesJson).stream()
                .map(Hierarchy::fromJson)
                .collect(java.util.stream.Collectors.toList()));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HierarchyForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for HierarchyForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HierarchyForCustomerResponse. */
  public static class Builder {

    private List<Hierarchy> hierarchies;

    private Response httpResponse;

    private Builder() {}

    public Builder hierarchies(List<Hierarchy> hierarchies) {
      this.hierarchies = hierarchies;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public HierarchyForCustomerResponse build() {
      return new HierarchyForCustomerResponse(this);
    }
  }

  /** Get the hierarchies from the response. */
  public List<Hierarchy> getHierarchies() {
    return hierarchies;
  }
}
