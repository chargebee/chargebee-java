package com.chargebee.v4.models.promotionalGrant.params;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.RequestBody;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end serialization test for {@link PromotionalGrantsParams}.
 *
 * This param class is exercised by {@code PromotionalGrantService.promotionalGrants(...)} via
 * {@code postJson("/promotional_grants", params.toJsonString())} — i.e. the JSON content-type
 * path. The class puts a raw {@link Timestamp} into the param map
 * ({@code formData.put("expires_at", expiresAt)}), which was previously serialized by
 * {@code JsonUtil.toJson(...)} as a human-readable string (e.g. "2026-06-23 09:54:44.513")
 * — the regression scenario the Chargebee API rejected.
 *
 * The form-url-encoded path was always correct (handled by
 * {@code FormRequestBody.valueToString}); these tests pin both transports.
 */
@DisplayName("PromotionalGrantsParams JSON/form body serialization")
class PromotionalGrantsParamsJsonTest {

    @Test
    @DisplayName("JSON body: expires_at must be Unix-millis number, not human-readable")
    void jsonBodyEmitsExpiresAtAsUnixMillisNumber() {
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixMillis = expiresAt.getTime();

        PromotionalGrantsParams params = PromotionalGrantsParams.builder()
                .subscriptionId("1mGETgZVF2umUZq")
                .unitId("ai_credits")
                .amount("500")
                .expiresAt(expiresAt)
                .build();

        String json = params.toJsonString();
        JsonObject parsed = JsonUtil.parse(json);

        assertEquals("1mGETgZVF2umUZq", JsonUtil.getString(parsed, "subscription_id"));
        assertEquals("ai_credits",      JsonUtil.getString(parsed, "unit_id"));
        assertEquals("500",              JsonUtil.getString(parsed, "amount"));

        // Core regression assertion: must be a JSON number equal to Unix millis.
        assertEquals(expectedUnixMillis, JsonUtil.getLong(parsed, "expires_at"),
                "expires_at must be Unix millis (number) in the JSON body");

        // Belt-and-braces: the raw JSON string must not embed a human-readable timestamp.
        assertFalse(json.matches(".*\"expires_at\"\\s*:\\s*\"[^\"]+\".*"),
                "expires_at must not be a quoted string. JSON: " + json);
        assertFalse(json.contains(expiresAt.toString()),
                "JSON must not contain Timestamp.toString() output. JSON: " + json);
    }

    @Test
    @DisplayName("JSON body mirrors the user-reported snippet (Instant.now() + 1 day)")
    void jsonBodyMatchesUserExampleShape() {
        Timestamp expiresAt = Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS));
        long expectedUnixMillis = expiresAt.getTime();

        PromotionalGrantsParams params = PromotionalGrantsParams.builder()
                .subscriptionId("1mGETgZVF2umUZq")
                .unitId("ai_credits")
                .amount("500")
                .expiresAt(expiresAt)
                .build();

        JsonObject parsed = JsonUtil.parse(params.toJsonString());
        assertEquals(expectedUnixMillis, JsonUtil.getLong(parsed, "expires_at"));
    }

    @Test
    @DisplayName("JSON body: metadata Map is preserved verbatim as a JSON string field")
    void jsonBodyPreservesMetadataJsonString() {
        // PromotionalGrantsParams.toFormData() puts metadata as a pre-serialized JSON string:
        //   formData.put("metadata", JsonUtil.toJson(this.metadata));
        // The outer toJsonString() must therefore emit it as a string, not double-serialize it.
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("source", "ui");
        meta.put("tier", 1);

        PromotionalGrantsParams params = PromotionalGrantsParams.builder()
                .subscriptionId("sub_x")
                .unitId("ai_credits")
                .amount("500")
                .expiresAt(expiresAt)
                .metadata(meta)
                .build();

        JsonObject parsed = JsonUtil.parse(params.toJsonString());
        // expires_at still a number.
        assertEquals(expiresAt.getTime(), JsonUtil.getLong(parsed, "expires_at"));
        // metadata is a JSON-encoded string.
        String metaStr = JsonUtil.getString(parsed, "metadata");
        assertNotNull(metaStr);
        JsonObject reparsed = JsonUtil.parse(metaStr);
        assertEquals("ui", JsonUtil.getString(reparsed, "source"));
        assertEquals(1L,   JsonUtil.getLong(reparsed, "tier"));
    }

    @Test
    @DisplayName("Form body: expires_at continues to be encoded as Unix seconds (unchanged behavior)")
    void formBodyStillEncodesExpiresAtAsUnixSeconds() throws IOException {
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixSeconds = expiresAt.getTime() / 1000L;

        PromotionalGrantsParams params = PromotionalGrantsParams.builder()
                .subscriptionId("1mGETgZVF2umUZq")
                .unitId("ai_credits")
                .amount("500")
                .expiresAt(expiresAt)
                .build();

        RequestBody body = RequestBody.form(params.toFormData());
        String encoded = new String(body.getBytes(), StandardCharsets.UTF_8);
        Map<String, String> form = parseFormBody(encoded);

        assertEquals("1mGETgZVF2umUZq", form.get("subscription_id"));
        assertEquals("ai_credits",      form.get("unit_id"));
        assertEquals("500",              form.get("amount"));
        assertEquals(String.valueOf(expectedUnixSeconds), form.get("expires_at"),
                "Form path must keep emitting Unix-seconds string for Timestamp");
        // No human-readable date fragment.
        assertFalse(form.get("expires_at").contains(" "),
                "Form-encoded expires_at must not contain spaces (would indicate Timestamp.toString() leak)");
    }

    @Test
    @DisplayName("Content-Type: form and JSON transports each retain their own content-type")
    void contentTypesAreNotSwapped() {
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        PromotionalGrantsParams params = PromotionalGrantsParams.builder()
                .subscriptionId("sub_x")
                .unitId("ai_credits")
                .amount("500")
                .expiresAt(expiresAt)
                .build();

        RequestBody form = RequestBody.form(params.toFormData());
        RequestBody json = RequestBody.json(params.toJsonString());

        assertTrue(form.getContentType().startsWith("application/x-www-form-urlencoded"),
                "Form content-type unchanged");
        assertTrue(json.getContentType().startsWith("application/json"),
                "JSON content-type unchanged");
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    private static Map<String, String> parseFormBody(String body) {
        Map<String, String> out = new LinkedHashMap<>();
        if (body == null || body.isEmpty()) return out;
        for (String pair : body.split("&")) {
            int eq = pair.indexOf('=');
            String rawKey = eq < 0 ? pair : pair.substring(0, eq);
            String rawVal = eq < 0 ? "" : pair.substring(eq + 1);
            try {
                String key = java.net.URLDecoder.decode(rawKey, StandardCharsets.UTF_8.name());
                String val = java.net.URLDecoder.decode(rawVal, StandardCharsets.UTF_8.name());
                out.put(key, val);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return out;
    }
}
