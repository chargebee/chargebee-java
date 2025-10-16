package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteResendEinvoice operation. Contains the response data from
 * the API.
 */
public final class CreditNoteResendEinvoiceResponse extends BaseResponse {
  private final CreditNote creditNote;

  private CreditNoteResendEinvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteResendEinvoiceResponse object. */
  public static CreditNoteResendEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteResendEinvoiceResponse object with HTTP response. */
  public static CreditNoteResendEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteResendEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteResendEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteResendEinvoiceResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Response httpResponse;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreditNoteResendEinvoiceResponse build() {
      return new CreditNoteResendEinvoiceResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
