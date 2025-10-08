package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CardRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CardRetrieveResponse {

  private final Card card;

  private final Response httpResponse;

  private CardRetrieveResponse(Card card, Response httpResponse) {

    this.card = card;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CardRetrieveResponse object. */
  public static CardRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CardRetrieveResponse object with HTTP response. */
  public static CardRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new CardRetrieveResponse(card, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardRetrieveResponse from JSON", e);
    }
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
