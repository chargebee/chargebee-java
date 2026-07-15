/*
 * Copyright 2026 Chargebee Inc.
 */

package com.chargebee.v4.telemetry;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Executes Chargebee API calls with optional telemetry adapter hooks. */
public final class TelemetryExecutor {

  private static final Logger LOGGER = Logger.getLogger(TelemetryExecutor.class.getName());

  private TelemetryExecutor() {}

  public static Response execute(
      ChargebeeClient client, Request request, Function<Request, Response> action) {
    TelemetryAdapter adapter = resolveAdapter(client, request);
    if (adapter == null || !request.hasTelemetryMetadata()) {
      return action.apply(request);
    }

    long startTime = System.currentTimeMillis();
    Map<String, String> telemetryHeaders = new HashMap<>();
    Object handle = startTelemetry(client, adapter, request, telemetryHeaders);
    Request requestWithHeaders = withHeaders(request, telemetryHeaders);

    try {
      Response response = action.apply(requestWithHeaders);
      endTelemetrySuccess(adapter, handle, startTime, response);
      return response;
    } catch (RuntimeException e) {
      endTelemetryFailure(adapter, handle, startTime, e);
      throw e;
    }
  }

  public static CompletableFuture<Response> executeAsync(
      ChargebeeClient client,
      Request request,
      Function<Request, CompletableFuture<Response>> action) {
    TelemetryAdapter adapter = resolveAdapter(client, request);
    if (adapter == null || !request.hasTelemetryMetadata()) {
      return action.apply(request);
    }

    long startTime = System.currentTimeMillis();
    Map<String, String> telemetryHeaders = new HashMap<>();
    Object handle = startTelemetry(client, adapter, request, telemetryHeaders);
    Request requestWithHeaders = withHeaders(request, telemetryHeaders);

    return action
        .apply(requestWithHeaders)
        .whenComplete(
            (response, throwable) -> {
              if (throwable != null) {
                Throwable cause = throwable.getCause() != null ? throwable.getCause() : throwable;
                endTelemetryFailure(adapter, handle, startTime, cause);
              } else {
                endTelemetrySuccess(adapter, handle, startTime, response);
              }
            });
  }

  public static TelemetryAdapter resolveAdapter(ChargebeeClient client, Request request) {
    if (request.getTelemetryAdapterOverride() != null) {
      return request.getTelemetryAdapterOverride();
    }
    return client.getTelemetryAdapter();
  }

  private static Object startTelemetry(
      ChargebeeClient client,
      TelemetryAdapter adapter,
      Request request,
      Map<String, String> telemetryHeaders) {
    try {
      RequestTelemetryContext context = buildContext(client, request);
      return adapter.onRequestStart(context, telemetryHeaders);
    } catch (Exception err) {
      LOGGER.log(
          Level.WARNING,
          "Telemetry adapter onRequestStart failed: "
              + err.getMessage()
              + ". Continuing without telemetry.",
          err);
      return null;
    }
  }

  private static void endTelemetrySuccess(
      TelemetryAdapter adapter, Object handle, long startTime, Response response) {
    try {
      adapter.onRequestEnd(
          handle,
          TelemetrySupport.buildRequestTelemetryResult(
              new TelemetrySupport.RequestTelemetryResultInput(
                  response.getStatusCode(),
                  System.currentTimeMillis() - startTime,
                  null,
                  response.getHeaders())));
    } catch (Exception err) {
      LOGGER.log(Level.WARNING, "Telemetry adapter onRequestEnd failed: " + err.getMessage(), err);
    }
  }

  private static void endTelemetryFailure(
      TelemetryAdapter adapter, Object handle, long startTime, Throwable err) {
    Integer status = TelemetrySupport.extractHttpStatusCode(err);
    int httpStatusCode = status != null ? status : 500;
    try {
      adapter.onRequestEnd(
          handle,
          TelemetrySupport.buildRequestTelemetryResult(
              new TelemetrySupport.RequestTelemetryResultInput(
                  httpStatusCode,
                  System.currentTimeMillis() - startTime,
                  TelemetrySupport.extractRequestTelemetryError(err),
                  TelemetrySupport.extractResponseHeaders(err))));
    } catch (Exception telemetryErr) {
      LOGGER.log(
          Level.WARNING,
          "Telemetry adapter onRequestEnd failed: " + telemetryErr.getMessage(),
          telemetryErr);
    }
  }

  static RequestTelemetryContext buildContext(ChargebeeClient client, Request request) {
    URI uri = URI.create(request.getUrl());
    String httpUrl = uri.getScheme() + "://" + uri.getHost() + uri.getPath();
    String apiPath = extractApiPath(client.getBaseUrl());
    return TelemetrySupport.buildRequestTelemetryContext(
        new TelemetrySupport.BuildRequestTelemetryContextInput(
            request.getTelemetryResource(),
            request.getTelemetryOperation(),
            request.getMethod(),
            httpUrl,
            uri.getHost(),
            client.getSiteName(),
            TelemetrySupport.resolveChargebeeApiVersion(apiPath),
            client.getSdkVersion(),
            request.getHeaders()));
  }

  private static String extractApiPath(String baseUrl) {
    URI uri = URI.create(baseUrl);
    String path = uri.getPath();
    return path != null && !path.isEmpty() ? path : "/api/v2";
  }

  static Request withHeaders(Request request, Map<String, String> headers) {
    Request updated = request;
    for (Map.Entry<String, String> header : headers.entrySet()) {
      updated = updated.withHeader(header.getKey(), header.getValue());
    }
    return updated;
  }
}
