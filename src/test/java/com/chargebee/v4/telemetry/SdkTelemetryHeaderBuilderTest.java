package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SDK telemetry header builder")
class SdkTelemetryHeaderBuilderTest {

  @Test
  @DisplayName("Should serialize features under the f key")
  void shouldSerializeFeaturesUnderKey() {
    String header =
        SdkTelemetryHeaderBuilder.build(
            List.of(
                SdkTelemetryFeature.TELEMETRY_ADAPTER, SdkTelemetryFeature.CUSTOM_TRANSPORT));

    assertEquals("f;ta;ct", header);
  }

  @Test
  @DisplayName("Should return null when no features are enabled")
  void shouldReturnNullWhenEmpty() {
    assertNull(SdkTelemetryHeaderBuilder.build(Set.of()));
    assertNull(SdkTelemetryHeaderBuilder.build(null));
  }

  @Test
  @DisplayName("Should skip null feature entries")
  void shouldSkipNullEntries() {
    String header =
        SdkTelemetryHeaderBuilder.build(
            java.util.Arrays.asList(SdkTelemetryFeature.RETRY_CONFIG, null));

    assertEquals("f;rc", header);
  }

  @Test
  @DisplayName("Should preserve enum declaration order via LinkedHashSet-style input")
  void shouldPreserveOrder() {
    String header =
        SdkTelemetryHeaderBuilder.build(
            EnumSet.of(
                SdkTelemetryFeature.TELEMETRY_ADAPTER,
                SdkTelemetryFeature.CUSTOM_TRANSPORT,
                SdkTelemetryFeature.RETRY_CONFIG));

    assertEquals("f;ta;ct;rc", header);
  }
}
