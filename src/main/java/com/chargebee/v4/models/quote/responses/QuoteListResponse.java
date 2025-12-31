package com.chargebee.v4.models.quote.responses;

import java.util.List;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.QuoteService;
import com.chargebee.v4.models.quote.params.QuoteListParams;

/** Immutable response object for QuoteList operation. Contains paginated list data. */
public final class QuoteListResponse {

  private final List<QuoteListItem> list;

  private final String nextOffset;

  private final QuoteService service;
  private final QuoteListParams originalParams;
  private final Response httpResponse;

  private QuoteListResponse(
      List<QuoteListItem> list,
      String nextOffset,
      QuoteService service,
      QuoteListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into QuoteListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static QuoteListResponse fromJson(String json) {
    try {

      List<QuoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into QuoteListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static QuoteListResponse fromJson(
      String json, QuoteService service, QuoteListParams originalParams, Response httpResponse) {
    try {

      List<QuoteListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<QuoteListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public QuoteListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    QuoteListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : QuoteListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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

  @Override
  public String toString() {
    return "QuoteListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteListResponse that = (QuoteListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class QuoteListItem {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedRamp quotedRamp;

    public Quote getQuote() {
      return quote;
    }

    public QuotedSubscription getQuotedSubscription() {
      return quotedSubscription;
    }

    public QuotedRamp getQuotedRamp() {
      return quotedRamp;
    }

    public static QuoteListItem fromJson(String json) {
      QuoteListItem item = new QuoteListItem();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        item.quote = Quote.fromJson(__quoteJson);
      }

      String __quotedSubscriptionJson = JsonUtil.getObject(json, "quoted_subscription");
      if (__quotedSubscriptionJson != null) {
        item.quotedSubscription = QuotedSubscription.fromJson(__quotedSubscriptionJson);
      }

      String __quotedRampJson = JsonUtil.getObject(json, "quoted_ramp");
      if (__quotedRampJson != null) {
        item.quotedRamp = QuotedRamp.fromJson(__quotedRampJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "QuoteListItem{"
          + "quote="
          + quote
          + ", quotedSubscription="
          + quotedSubscription
          + ", quotedRamp="
          + quotedRamp
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      QuoteListItem that = (QuoteListItem) o;
      return java.util.Objects.equals(quote, that.quote)
          && java.util.Objects.equals(quotedSubscription, that.quotedSubscription)
          && java.util.Objects.equals(quotedRamp, that.quotedRamp);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(quote, quotedSubscription, quotedRamp);
    }
  }
}
