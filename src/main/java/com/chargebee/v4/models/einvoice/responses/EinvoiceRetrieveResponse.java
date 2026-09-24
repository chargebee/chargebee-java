package com.chargebee.v4.models.einvoice.responses;

import com.chargebee.v4.models.einvoice.Einvoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EinvoiceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class EinvoiceRetrieveResponse extends BaseResponse {
  private final Einvoice einvoice;

  private EinvoiceRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.einvoice = builder.einvoice;
  }

  /** Parse JSON response into EinvoiceRetrieveResponse object. */
  public static EinvoiceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EinvoiceRetrieveResponse object with HTTP response. */
  public static EinvoiceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __einvoiceObj = JsonUtil.getJsonObject(jsonObj, "einvoice");
      if (__einvoiceObj != null) {
        builder.einvoice(Einvoice.fromJson(__einvoiceObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EinvoiceRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for EinvoiceRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EinvoiceRetrieveResponse. */
  public static class Builder {

    private Einvoice einvoice;

    private Response httpResponse;

    private Builder() {}

    public Builder einvoice(Einvoice einvoice) {
      this.einvoice = einvoice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EinvoiceRetrieveResponse build() {
      return new EinvoiceRetrieveResponse(this);
    }
  }

  /** Get the einvoice from the response. */
  public Einvoice getEinvoice() {
    return einvoice;
  }

  @Override
  public String toString() {
    return "EinvoiceRetrieveResponse{" + "einvoice=" + einvoice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EinvoiceRetrieveResponse that = (EinvoiceRetrieveResponse) o;
    return java.util.Objects.equals(einvoice, that.einvoice);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(einvoice);
  }
}
