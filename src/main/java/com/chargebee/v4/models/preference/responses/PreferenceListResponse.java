package com.chargebee.v4.models.preference.responses;

import com.chargebee.v4.models.preference.Preference;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PreferenceList operation. Contains the response data from a single
 * resource get operation.
 */
public final class PreferenceListResponse extends BaseResponse {
  private final Preference preference;

  private PreferenceListResponse(Builder builder) {
    super(builder.httpResponse);

    this.preference = builder.preference;
  }

  /** Parse JSON response into PreferenceListResponse object. */
  public static PreferenceListResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PreferenceListResponse object with HTTP response. */
  public static PreferenceListResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse PreferenceListResponse from JSON", e);
    }
  }

  /** Create a new builder for PreferenceListResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PreferenceListResponse. */
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

    public PreferenceListResponse build() {
      return new PreferenceListResponse(this);
    }
  }

  /** Get the preference from the response. */
  public Preference getPreference() {
    return preference;
  }

  @Override
  public String toString() {
    return "PreferenceListResponse{" + "preference=" + preference + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PreferenceListResponse that = (PreferenceListResponse) o;
    return java.util.Objects.equals(preference, that.preference);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(preference);
  }
}
