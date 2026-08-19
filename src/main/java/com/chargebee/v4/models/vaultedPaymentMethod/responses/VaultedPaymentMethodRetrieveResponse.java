package com.chargebee.v4.models.vaultedPaymentMethod.responses;

import com.chargebee.v4.models.vaultedPaymentMethod.VaultedPaymentMethod;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VaultedPaymentMethodRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class VaultedPaymentMethodRetrieveResponse extends BaseResponse {
  private final VaultedPaymentMethod vaultedPaymentMethod;

  private VaultedPaymentMethodRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.vaultedPaymentMethod = builder.vaultedPaymentMethod;
  }

  /** Parse JSON response into VaultedPaymentMethodRetrieveResponse object. */
  public static VaultedPaymentMethodRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VaultedPaymentMethodRetrieveResponse object with HTTP response. */
  public static VaultedPaymentMethodRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __vaultedPaymentMethodObj =
          JsonUtil.getJsonObject(jsonObj, "vaulted_payment_method");
      if (__vaultedPaymentMethodObj != null) {
        builder.vaultedPaymentMethod(VaultedPaymentMethod.fromJson(__vaultedPaymentMethodObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse VaultedPaymentMethodRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for VaultedPaymentMethodRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VaultedPaymentMethodRetrieveResponse. */
  public static class Builder {

    private VaultedPaymentMethod vaultedPaymentMethod;

    private Response httpResponse;

    private Builder() {}

    public Builder vaultedPaymentMethod(VaultedPaymentMethod vaultedPaymentMethod) {
      this.vaultedPaymentMethod = vaultedPaymentMethod;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public VaultedPaymentMethodRetrieveResponse build() {
      return new VaultedPaymentMethodRetrieveResponse(this);
    }
  }

  /** Get the vaultedPaymentMethod from the response. */
  public VaultedPaymentMethod getVaultedPaymentMethod() {
    return vaultedPaymentMethod;
  }

  @Override
  public String toString() {
    return "VaultedPaymentMethodRetrieveResponse{"
        + "vaultedPaymentMethod="
        + vaultedPaymentMethod
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VaultedPaymentMethodRetrieveResponse that = (VaultedPaymentMethodRetrieveResponse) o;
    return java.util.Objects.equals(vaultedPaymentMethod, that.vaultedPaymentMethod);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(vaultedPaymentMethod);
  }
}
