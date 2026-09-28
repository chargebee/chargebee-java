package com.chargebee.v4.models.preference.responses;

import com.chargebee.v4.models.preference.Preference;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PreferenceUpsert operation. Contains the response data from the
 * API.
 */
public final class PreferenceUpsertResponse extends BaseResponse {
  private final Preference preference;

  private PreferenceUpsertResponse(Builder builder) {
    super(builder.httpResponse);

    this.preference = builder.preference;
  }

  /** Parse JSON response into PreferenceUpsertResponse object. */
  public static PreferenceUpsertResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PreferenceUpsertResponse object with HTTP response. */
  public static PreferenceUpsertResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __preferenceObj = JsonUtil.getJsonObject(jsonObj, "preference");
      if (__preferenceObj != null) {
        builder.preference(Preference.fromJson(__preferenceObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PreferenceUpsertResponse from JSON", e);
    }
  }

  /** Create a new builder for PreferenceUpsertResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PreferenceUpsertResponse. */
  public static class Builder {

    private Preference preference;

    private Response httpResponse;

    private Builder() {}

    public Builder preference(Preference preference) {
      this.preference = preference;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PreferenceUpsertResponse build() {
      return new PreferenceUpsertResponse(this);
    }
  }

  /** Get the preference from the response. */
  public Preference getPreference() {
    return preference;
  }

  @Override
  public String toString() {
    return "PreferenceUpsertResponse{" + "preference=" + preference + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PreferenceUpsertResponse that = (PreferenceUpsertResponse) o;
    return java.util.Objects.equals(preference, that.preference);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(preference);
  }
}
