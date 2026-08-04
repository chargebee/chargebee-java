package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SDK telemetry header builder")
class SdkTelemetryHeaderBuilderTest {

  @Test
  @DisplayName("Should serialize a complete snapshot")
  void shouldSerializeCompleteSnapshot() {
    Set<String> features = new LinkedHashSet<>();
    features.add(SdkTelemetryHeader.FT_TELEMETRY_ADAPTER);
    features.add(SdkTelemetryHeader.FT_CUSTOM_TRANSPORT);

    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion("4.14.0")
            .resource("customer")
            .operation("list")
            .startTimeEpochSeconds(1781280400L)
            .timeMs(380)
            .httpStatus(200)
            .requestId("req_abc123")
            .featureTokens(features)
            .build();

    String header = SdkTelemetryHeaderBuilder.build(snapshot);

    assertNotNull(header);
    assertTrue(header.startsWith("sdk;name=chargebee-java;version=4.14.0;runtime=jvm;"));
    assertTrue(
        header.contains(
            "resource=customer;operation=list;start_time=@1781280400;time_ms=380;http_status=200"));
    assertTrue(header.contains("request_id=\"req_abc123\""));
    assertTrue(header.contains(SdkTelemetryHeader.FT_TELEMETRY_ADAPTER));
    assertTrue(header.contains(SdkTelemetryHeader.FT_CUSTOM_TRANSPORT));
  }

  @Test
  @DisplayName("Should include error_code on failure snapshots")
  void shouldIncludeErrorCode() {
    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion("4.14.0")
            .resource("customer")
            .operation("update")
            .timeMs(210)
            .httpStatus(400)
            .errorCode("param_wrong_value")
            .requestId("req_def456")
            .build();

    String header = SdkTelemetryHeaderBuilder.build(snapshot);

    assertNotNull(header);
    assertTrue(header.contains("http_status=400"));
    assertTrue(header.contains("error_code=\"param_wrong_value\""));
  }

  @Test
  @DisplayName("Should escape sf-string values")
  void shouldEscapeSfStrings() {
    assertEquals("\"hello\"", SdkTelemetryHeaderBuilder.escapeSfString("hello"));
    assertEquals("\"say \\\"hi\\\"\"", SdkTelemetryHeaderBuilder.escapeSfString("say \"hi\""));
    assertEquals("\"path\\\\to\"", SdkTelemetryHeaderBuilder.escapeSfString("path\\to"));
  }

  @Test
  @DisplayName("Should omit start_time when the call start is unknown")
  void shouldOmitUnknownStartTime() {
    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion("4.14.0")
            .resource("customer")
            .operation("list")
            .timeMs(12)
            .build();

    String header = SdkTelemetryHeaderBuilder.build(snapshot);

    assertNotNull(header);
    assertFalse(header.contains("start_time"));
  }

  @Test
  @DisplayName("Should quote token params that are not valid sf-tokens")
  void shouldQuoteInvalidTokenValues() {
    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion("4.14.0")
            .resource("customer report")
            .operation("list")
            .timeMs(5)
            .build();

    String header = SdkTelemetryHeaderBuilder.build(snapshot);

    assertNotNull(header);
    assertTrue(header.contains("resource=\"customer report\""));
    assertTrue(header.contains("operation=list"));
  }

  @Test
  @DisplayName("Should quote version when it contains structural characters")
  void shouldQuoteUnsafeVersionValues() {
    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion("4.14.0-rc\"1")
            .resource("customer")
            .operation("list")
            .timeMs(5)
            .build();

    String header = SdkTelemetryHeaderBuilder.build(snapshot);

    assertNotNull(header);
    assertTrue(header.contains("version=\"4.14.0-rc\\\"1\""));
  }

  @Test
  @DisplayName("Should return null when header exceeds max UTF-8 bytes")
  void shouldReturnNullWhenOversized() {
    String longVersion = "v".repeat(SdkTelemetryHeader.MAX_HEADER_BYTES);
    SdkTelemetrySnapshot snapshot =
        SdkTelemetrySnapshot.builder()
            .sdkName("chargebee-java")
            .sdkVersion(longVersion)
            .resource("customer")
            .operation("list")
            .timeMs(1)
            .httpStatus(200)
            .build();

    assertNull(SdkTelemetryHeaderBuilder.build(snapshot));
  }
}
