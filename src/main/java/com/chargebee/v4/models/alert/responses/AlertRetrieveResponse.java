package com.chargebee.v4.models.alert.responses;

import com.chargebee.v4.models.alert.Alert;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AlertRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AlertRetrieveResponse extends BaseResponse {
  private final Alert alert;

  private AlertRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.alert = builder.alert;
  }

  /** Parse JSON response into AlertRetrieveResponse object. */
  public static AlertRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AlertRetrieveResponse object with HTTP response. */
  public static AlertRetrieveResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse AlertRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for AlertRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AlertRetrieveResponse. */
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

    public AlertRetrieveResponse build() {
      return new AlertRetrieveResponse(this);
    }
  }

  /** Get the alert from the response. */
  public Alert getAlert() {
    return alert;
  }

  @Override
  public String toString() {
    return "AlertRetrieveResponse{" + "alert=" + alert + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AlertRetrieveResponse that = (AlertRetrieveResponse) o;
    return java.util.Objects.equals(alert, that.alert);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(alert);
  }
}
