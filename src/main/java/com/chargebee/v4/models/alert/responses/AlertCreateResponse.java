package com.chargebee.v4.models.alert.responses;

import com.chargebee.v4.models.alert.Alert;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/** Immutable response object for AlertCreate operation. Contains the response data from the API. */
public final class AlertCreateResponse extends BaseResponse {
  private final Alert alert;

  private AlertCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.alert = builder.alert;
  }

  /** Parse JSON response into AlertCreateResponse object. */
  public static AlertCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AlertCreateResponse object with HTTP response. */
  public static AlertCreateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __alertObj = JsonUtil.getJsonObject(jsonObj, "alert");
      if (__alertObj != null) {
        builder.alert(Alert.fromJson(__alertObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AlertCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for AlertCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AlertCreateResponse. */
  public static class Builder {

    private Alert alert;

    private Response httpResponse;

    private Builder() {}

    public Builder alert(Alert alert) {
      this.alert = alert;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AlertCreateResponse build() {
      return new AlertCreateResponse(this);
    }
  }

  /** Get the alert from the response. */
  public Alert getAlert() {
    return alert;
  }

  @Override
  public String toString() {
    return "AlertCreateResponse{" + "alert=" + alert + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AlertCreateResponse that = (AlertCreateResponse) o;
    return java.util.Objects.equals(alert, that.alert);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(alert);
  }
}
