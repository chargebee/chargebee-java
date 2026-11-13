package com.chargebee.v4.models.paymentSource.responses;

import java.util.List;

import com.chargebee.v4.models.gatewayPaymentMethodToken.GatewayPaymentMethodToken;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.PaymentSourceService;
import com.chargebee.v4.models.paymentSource.params.ListGatewayTokensForPaymentSourceParams;

/**
 * Immutable response object for ListGatewayTokensForPaymentSource operation. Contains paginated
 * list data.
 */
public final class ListGatewayTokensForPaymentSourceResponse {

  private final List<PaymentSourceListGatewayTokensForPaymentSourceItem> list;

  private final String nextOffset;

  private final String custPaymentSourceId;

  private final PaymentSourceService service;
  private final ListGatewayTokensForPaymentSourceParams originalParams;
  private final Response httpResponse;

  private ListGatewayTokensForPaymentSourceResponse(
      List<PaymentSourceListGatewayTokensForPaymentSourceItem> list,
      String nextOffset,
      String custPaymentSourceId,
      PaymentSourceService service,
      ListGatewayTokensForPaymentSourceParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.custPaymentSourceId = custPaymentSourceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ListGatewayTokensForPaymentSourceResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static ListGatewayTokensForPaymentSourceResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PaymentSourceListGatewayTokensForPaymentSourceItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              PaymentSourceListGatewayTokensForPaymentSourceItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListGatewayTokensForPaymentSourceResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListGatewayTokensForPaymentSourceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ListGatewayTokensForPaymentSourceResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static ListGatewayTokensForPaymentSourceResponse fromJson(
      String json,
      PaymentSourceService service,
      ListGatewayTokensForPaymentSourceParams originalParams,
      String custPaymentSourceId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PaymentSourceListGatewayTokensForPaymentSourceItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              PaymentSourceListGatewayTokensForPaymentSourceItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ListGatewayTokensForPaymentSourceResponse(
          list, nextOffset, custPaymentSourceId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ListGatewayTokensForPaymentSourceResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentSourceListGatewayTokensForPaymentSourceItem> getList() {
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
  public ListGatewayTokensForPaymentSourceResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ListGatewayTokensForPaymentSourceParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ListGatewayTokensForPaymentSourceParams.builder())
            .offset(nextOffset)
            .build();

    return service.listGatewayTokensForPaymentSource(custPaymentSourceId, nextParams);
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
    return "ListGatewayTokensForPaymentSourceResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListGatewayTokensForPaymentSourceResponse that = (ListGatewayTokensForPaymentSourceResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class PaymentSourceListGatewayTokensForPaymentSourceItem {

    private GatewayPaymentMethodToken gatewayPaymentMethodToken;

    public GatewayPaymentMethodToken getGatewayPaymentMethodToken() {
      return gatewayPaymentMethodToken;
    }

    public static PaymentSourceListGatewayTokensForPaymentSourceItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static PaymentSourceListGatewayTokensForPaymentSourceItem fromJson(JsonObject jsonObj) {
      PaymentSourceListGatewayTokensForPaymentSourceItem item =
          new PaymentSourceListGatewayTokensForPaymentSourceItem();

      JsonObject __gatewayPaymentMethodTokenObj =
          JsonUtil.getJsonObject(jsonObj, "gateway_payment_method_token");
      if (__gatewayPaymentMethodTokenObj != null) {
        item.gatewayPaymentMethodToken =
            GatewayPaymentMethodToken.fromJson(__gatewayPaymentMethodTokenObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "PaymentSourceListGatewayTokensForPaymentSourceItem{"
          + "gatewayPaymentMethodToken="
          + gatewayPaymentMethodToken
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PaymentSourceListGatewayTokensForPaymentSourceItem that =
          (PaymentSourceListGatewayTokensForPaymentSourceItem) o;
      return java.util.Objects.equals(gatewayPaymentMethodToken, that.gatewayPaymentMethodToken);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(gatewayPaymentMethodToken);
    }
  }
}
