package com.chargebee.v4.models.preferenceDefinition.responses;

import com.chargebee.v4.models.preferenceDefinition.PreferenceDefinition;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PreferenceDefinitionListDefinitions operation. Contains the
 * response data from a single resource get operation.
 */
public final class PreferenceDefinitionListDefinitionsResponse extends BaseResponse {
  private final PreferenceDefinition preferenceDefinition;

  private PreferenceDefinitionListDefinitionsResponse(Builder builder) {
    super(builder.httpResponse);

    this.preferenceDefinition = builder.preferenceDefinition;
  }

  /** Parse JSON response into PreferenceDefinitionListDefinitionsResponse object. */
  public static PreferenceDefinitionListDefinitionsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into PreferenceDefinitionListDefinitionsResponse object with HTTP response.
   */
  public static PreferenceDefinitionListDefinitionsResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __preferenceDefinitionObj =
          JsonUtil.getJsonObject(jsonObj, "preference_definition");
      if (__preferenceDefinitionObj != null) {
        builder.preferenceDefinition(PreferenceDefinition.fromJson(__preferenceDefinitionObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PreferenceDefinitionListDefinitionsResponse from JSON", e);
    }
  }

  /** Create a new builder for PreferenceDefinitionListDefinitionsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PreferenceDefinitionListDefinitionsResponse. */
  public static class Builder {

    private PreferenceDefinition preferenceDefinition;

    private Response httpResponse;

    private Builder() {}

    public Builder preferenceDefinition(PreferenceDefinition preferenceDefinition) {
      this.preferenceDefinition = preferenceDefinition;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PreferenceDefinitionListDefinitionsResponse build() {
      return new PreferenceDefinitionListDefinitionsResponse(this);
    }
  }

  /** Get the preferenceDefinition from the response. */
  public PreferenceDefinition getPreferenceDefinition() {
    return preferenceDefinition;
  }

  @Override
  public String toString() {
    return "PreferenceDefinitionListDefinitionsResponse{"
        + "preferenceDefinition="
        + preferenceDefinition
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PreferenceDefinitionListDefinitionsResponse that =
        (PreferenceDefinitionListDefinitionsResponse) o;
    return java.util.Objects.equals(preferenceDefinition, that.preferenceDefinition);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(preferenceDefinition);
  }
}
