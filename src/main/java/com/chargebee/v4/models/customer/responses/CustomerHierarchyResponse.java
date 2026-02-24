package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.hierarchy.Hierarchy;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for CustomerHierarchy operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerHierarchyResponse extends BaseResponse {
  private final List<Hierarchy> hierarchies;

  private CustomerHierarchyResponse(Builder builder) {
    super(builder.httpResponse);

    this.hierarchies = builder.hierarchies;
  }

  /** Parse JSON response into CustomerHierarchyResponse object. */
  public static CustomerHierarchyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerHierarchyResponse object with HTTP response. */
  public static CustomerHierarchyResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.hierarchies(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "hierarchies"), Hierarchy::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerHierarchyResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerHierarchyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerHierarchyResponse. */
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

    public CustomerHierarchyResponse build() {
      return new CustomerHierarchyResponse(this);
    }
  }

  /** Get the hierarchies from the response. */
  public List<Hierarchy> getHierarchies() {
    return hierarchies;
  }

  @Override
  public String toString() {
    return "CustomerHierarchyResponse{" + "hierarchies=" + hierarchies + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CustomerHierarchyResponse that = (CustomerHierarchyResponse) o;
    return java.util.Objects.equals(hierarchies, that.hierarchies);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(hierarchies);
  }
}
