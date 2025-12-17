package com.chargebee.v4.models.unbilledCharge.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceUnbilledCharges operation. Contains the response data from
 * the API.
 */
public final class InvoiceUnbilledChargesResponse extends BaseResponse {
  private final List<Invoice> invoices;

  private InvoiceUnbilledChargesResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoices = builder.invoices;
  }

  /** Parse JSON response into InvoiceUnbilledChargesResponse object. */
  public static InvoiceUnbilledChargesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceUnbilledChargesResponse object with HTTP response. */
  public static InvoiceUnbilledChargesResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.invoices(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "invoices")).stream()
              .map(Invoice::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceUnbilledChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceUnbilledChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceUnbilledChargesResponse. */
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

    public InvoiceUnbilledChargesResponse build() {
      return new InvoiceUnbilledChargesResponse(this);
    }
  }

  /** Get the invoices from the response. */
  public List<Invoice> getInvoices() {
    return invoices;
  }
}
