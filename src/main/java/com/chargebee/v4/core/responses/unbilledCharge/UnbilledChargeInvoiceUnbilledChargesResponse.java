package com.chargebee.v4.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargeInvoiceUnbilledCharges operation. Contains the
 * response data from the API.
 */
public final class UnbilledChargeInvoiceUnbilledChargesResponse extends BaseResponse {
  private final List<Invoice> invoices;

  private UnbilledChargeInvoiceUnbilledChargesResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoices = builder.invoices;
  }

  /** Parse JSON response into UnbilledChargeInvoiceUnbilledChargesResponse object. */
  public static UnbilledChargeInvoiceUnbilledChargesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into UnbilledChargeInvoiceUnbilledChargesResponse object with HTTP
   * response.
   */
  public static UnbilledChargeInvoiceUnbilledChargesResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.invoices(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "invoices")).stream()
              .map(Invoice::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargeInvoiceUnbilledChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeInvoiceUnbilledChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeInvoiceUnbilledChargesResponse. */
  public static class Builder {

    private List<Invoice> invoices;

    private Response httpResponse;

    private Builder() {}

    public Builder invoices(List<Invoice> invoices) {
      this.invoices = invoices;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UnbilledChargeInvoiceUnbilledChargesResponse build() {
      return new UnbilledChargeInvoiceUnbilledChargesResponse(this);
    }
  }

  /** Get the invoices from the response. */
  public List<Invoice> getInvoices() {
    return invoices;
  }
}
