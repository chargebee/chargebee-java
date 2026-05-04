package com.chargebee.v4.models.alert.responses;

import com.chargebee.v4.models.alert.Alert;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/** Immutable response object for AlertDelete operation. Contains the response data from the API. */
public final class AlertDeleteResponse extends BaseResponse {
  private final Alert alert;

  private AlertDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.alert = builder.alert;
  }

  /** Parse JSON response into AlertDeleteResponse object. */
  public static AlertDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AlertDeleteResponse object with HTTP response. */
  public static AlertDeleteResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse AlertDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for AlertDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AlertDeleteResponse. */
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

    public AlertDeleteResponse build() {
      return new AlertDeleteResponse(this);
    }
  }

  /** Get the alert from the response. */
  public Alert getAlert() {
    return alert;
  }

  @Override
  public String toString() {
    return "AlertDeleteResponse{" + "alert=" + alert + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AlertDeleteResponse that = (AlertDeleteResponse) o;
    return java.util.Objects.equals(alert, that.alert);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(alert);
  }
}
