package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.PromotionalGrant;
import com.chargebee.models.Subscription;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Verifies that request body serialization is correct end-to-end across both
 * transport encodings:
 *
 * 1. JSON requests (e.g. {@link PromotionalGrant#promotionalGrants()}): Timestamps
 *    must be serialized as Unix-seconds numbers, NOT human-readable strings such
 *    as "2026-06-23 09:54:44.513". This is the regression scenario.
 *
 * 2. Form-url-encoded requests: behavior is unchanged. Timestamps continue to
 *    flow through {@link Params#toValStr(Object)} → {@link Params#asUnixTimestamp(Timestamp)}
 *    and are encoded as {@code expires_at=<unix-seconds>}.
 */
public class RequestBodySerializationTest {

    private Environment env;

    @BeforeEach
    void setUp() {
        env = new Environment("test-site", "test-key");
        env.enableDebugLogging = false;
    }

    // ---------------------------------------------------------------------
    // JSON path: end-to-end via the real PromotionalGrant request primitive.
    // ---------------------------------------------------------------------

    @Test
    void promotionalGrantJsonBodySerializesTimestampAsUnixSecondsNumber() throws Exception {
        // The exact call shape from the bug report.
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixSeconds = expiresAt.getTime() / 1000;

        CapturedRequest captured = stubHttpForJsonPost(
                buildPromotionalGrantResponse("1mGETgZVF2umUZq", "ai_credits", "500", expectedUnixSeconds)
        );

        try (MockedStatic<HttpUtil> mocked = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            mocked.when(() -> HttpUtil.createConnection(anyString(), eq(HttpUtil.Method.POST), any(), eq(env)))
                    .thenReturn(captured.conn);

            Result result = PromotionalGrant.promotionalGrants()
                    .subscriptionId("1mGETgZVF2umUZq")
                    .unitId("ai_credits")
                    .amount("500")
                    .expiresAt(expiresAt)
                    .request(env);

            assertNotNull(result);
            assertEquals(200, result.httpCode);

            String body = captured.body();
            JSONObject sent = new JSONObject(body);

            assertEquals("1mGETgZVF2umUZq", sent.getString("subscription_id"));
            assertEquals("ai_credits", sent.getString("unit_id"));
            assertEquals("500", sent.getString("amount"));

            // Core regression assertions:
            //  - expires_at must be a JSON number equal to Unix seconds.
            assertEquals(expectedUnixSeconds, sent.getLong("expires_at"),
                    "expires_at must be Unix seconds (number) in the JSON body");
            //  - The raw body must NOT contain a quoted, human-readable timestamp.
            assertFalse(body.matches(".*\"expires_at\"\\s*:\\s*\"[^\"]+\".*"),
                    "expires_at must be a JSON number, not a quoted string. Body was: " + body);
            assertFalse(body.contains(expiresAt.toString()),
                    "Body must not contain Timestamp.toString() output. Body was: " + body);
        }
    }

    @Test
    void promotionalGrantJsonBodyMatchesUserExampleShape() throws Exception {
        Timestamp expiresAt = Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS));
        long expectedUnixSeconds = expiresAt.getTime() / 1000;

        CapturedRequest captured = stubHttpForJsonPost(
                buildPromotionalGrantResponse("1mGETgZVF2umUZq", "ai_credits", "500", expectedUnixSeconds)
        );

        try (MockedStatic<HttpUtil> mocked = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            mocked.when(() -> HttpUtil.createConnection(anyString(), eq(HttpUtil.Method.POST), any(), eq(env)))
                    .thenReturn(captured.conn);

            PromotionalGrant.promotionalGrants()
                    .subscriptionId("1mGETgZVF2umUZq")
                    .unitId("ai_credits")
                    .amount("500")
                    .expiresAt(expiresAt)
                    .request(env);

            JSONObject sent = new JSONObject(captured.body());
            assertEquals(expectedUnixSeconds, sent.getLong("expires_at"));
        }
    }

    @Test
    void promotionalGrantSetsJsonContentTypeHeader() throws Exception {
        Timestamp expiresAt = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixSeconds = expiresAt.getTime() / 1000;

        CapturedRequest captured = stubHttpForJsonPost(
                buildPromotionalGrantResponse("sub_1", "ai_credits", "500", expectedUnixSeconds)
        );

        try (MockedStatic<HttpUtil> mocked = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            mocked.when(() -> HttpUtil.createConnection(anyString(), eq(HttpUtil.Method.POST),
                    argThat(h -> h != null
                            && ("application/json;charset=" + Environment.CHARSET).equals(h.get("Content-Type"))),
                    eq(env)))
                    .thenReturn(captured.conn);

            assertDoesNotThrow(() -> PromotionalGrant.promotionalGrants()
                    .subscriptionId("sub_1")
                    .unitId("ai_credits")
                    .amount("500")
                    .expiresAt(expiresAt)
                    .request(env));

            // If the matcher above didn't match, the static mock would have returned null and
            // the call would have NPE'd. Reaching here proves the JSON Content-Type was set.
            mocked.verify(() -> HttpUtil.createConnection(anyString(), eq(HttpUtil.Method.POST), any(), eq(env)));
        }
    }

    // ---------------------------------------------------------------------
    // Form-url-encoded path: behavior must NOT change.
    // ---------------------------------------------------------------------

    @Test
    void formEncodedPathStillEncodesTimestampAsUnixSeconds() throws Exception {
        Timestamp ts = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixSeconds = ts.getTime() / 1000;

        Params p = new Params();
        p.add("subscription_id", "overage-check");
        p.add("amount", "500");
        p.add("expires_at", ts);
        p.add("unit_id", "ai_credits");

        String query = HttpUtil.toQueryStr(p);

        assertTrue(query.contains("expires_at=" + expectedUnixSeconds),
                "Form-encoded body must contain 'expires_at=<unix-seconds>'. Got: " + query);
        assertTrue(query.contains("subscription_id=overage-check"));
        assertTrue(query.contains("amount=500"));
        assertTrue(query.contains("unit_id=ai_credits"));

        // And must NOT contain any URL-encoded human-readable timestamp fragments.
        assertFalse(query.contains("%20"), // a space-encoded char would only appear in a date-time string
                "Form-encoded body unexpectedly contains a space-encoded character: " + query);
        assertFalse(query.toLowerCase().contains(ts.toString().substring(0, 4).toLowerCase() + "-"),
                "Form-encoded body must not contain a yyyy-MM-dd fragment for the timestamp.");
    }

    @Test
    void formEncodedPathSerializesDateAndOtherPrimitivesUnchanged() throws Exception {
        Date date = new Date(0L); // 1970-01-01 in UTC
        Params p = new Params();
        p.add("name", "alice");
        p.add("count", 7);
        p.add("active", true);
        p.add("trial_end_date", date);

        String query = HttpUtil.toQueryStr(p);

        assertTrue(query.contains("name=alice"));
        assertTrue(query.contains("count=7"));
        assertTrue(query.contains("active=true"));
        // Date is formatted via SimpleDateFormat("yyyy-MM-dd") with default TZ; verify the encoded "-" survives.
        assertTrue(query.matches(".*trial_end_date=\\d{4}-\\d{2}-\\d{2}.*"),
                "Date must remain serialized as yyyy-MM-dd. Got: " + query);
    }

    @Test
    void subscriptionCreateWithItemsFormBodySerializesTimestampsAsUnixSeconds() throws Exception {
        // The exact user snippet, augmented with the Timestamp fields exposed by this request
        // primitive (trialEnd, startDate, subscriptionItemTrialEnd) so the form-url-encoded
        // path is exercised end-to-end.
        Timestamp trialEnd        = Timestamp.from(Instant.parse("2026-07-01T00:00:00Z"));
        Timestamp startDate       = Timestamp.from(Instant.parse("2026-06-25T00:00:00Z"));
        Timestamp itemTrialEnd    = Timestamp.from(Instant.parse("2026-06-30T12:00:00Z"));
        long expectedTrialEnd     = trialEnd.getTime() / 1000;
        long expectedStartDate    = startDate.getTime() / 1000;
        long expectedItemTrialEnd = itemTrialEnd.getTime() / 1000;

        CapturedRequest captured = stubHttpForFormPost("{\"subscription\":{\"id\":\"__test__8asz8Ru9WhHOJO\"}}");

        try (MockedStatic<HttpUtil> mocked = mockStatic(HttpUtil.class, CALLS_REAL_METHODS)) {
            mocked.when(() -> HttpUtil.createConnection(anyString(), eq(HttpUtil.Method.POST), any(), eq(env)))
                    .thenReturn(captured.conn);

            Result result = Subscription.createWithItems("__test__8asz8Ru9WhHOJO")
                    .subscriptionItemItemPriceId(0, "basic-USD")
                    .subscriptionItemBillingCycles(0, 2)
                    .subscriptionItemQuantity(0, 1)
                    .subscriptionItemItemPriceId(1, "day-pass-USD")
                    .subscriptionItemUnitPrice(1, 100L)
                    .trialEnd(trialEnd)
                    .startDate(startDate)
                    .subscriptionItemTrialEnd(0, itemTrialEnd)
                    .request(env);

            assertNotNull(result);
            assertEquals(200, result.httpCode);

            String body = captured.body();

            // Body must look like a form-url-encoded string, not JSON.
            assertFalse(body.startsWith("{"), "Form-encoded body must not be JSON. Got: " + body);
            assertTrue(body.contains("&") || body.contains("="),
                    "Form-encoded body must contain '=' / '&'. Got: " + body);

            // Headers passed to createConnection must NOT have been switched to JSON.
            // (The form path leaves the Content-Type header to be set inside createConnection itself.)
            mocked.verify(() -> HttpUtil.createConnection(
                    anyString(),
                    eq(HttpUtil.Method.POST),
                    argThat(h -> h == null
                            || !("application/json;charset=" + Environment.CHARSET).equals(h.get("Content-Type"))),
                    eq(env)));

            // Customer id is part of the URL path, not the body.
            ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
            mocked.verify(() -> HttpUtil.createConnection(
                    urlCaptor.capture(), eq(HttpUtil.Method.POST), any(), eq(env)));
            assertTrue(urlCaptor.getValue().contains("/customers/__test__8asz8Ru9WhHOJO/subscription_for_items"),
                    "Customer id must be embedded in the URL path. Got: " + urlCaptor.getValue());

            Map<String, String> params = parseFormBody(body);

            // All non-timestamp params are present and intact.
            assertEquals("basic-USD",   params.get("subscription_items[item_price_id][0]"));
            assertEquals("2",            params.get("subscription_items[billing_cycles][0]"));
            assertEquals("1",            params.get("subscription_items[quantity][0]"));
            assertEquals("day-pass-USD", params.get("subscription_items[item_price_id][1]"));
            assertEquals("100",          params.get("subscription_items[unit_price][1]"));

            // Core regression assertions for the form path: each Timestamp comes through
            // as a Unix-seconds string, NOT a human-readable date-time.
            assertEquals(String.valueOf(expectedTrialEnd),     params.get("trial_end"));
            assertEquals(String.valueOf(expectedStartDate),    params.get("start_date"));
            assertEquals(String.valueOf(expectedItemTrialEnd), params.get("subscription_items[trial_end][0]"));

            // And belt-and-braces: none of the timestamp keys should hold a value containing
            // a space (which would indicate Timestamp.toString() leaked into the body).
            assertFalse(params.get("trial_end").contains(" "));
            assertFalse(params.get("start_date").contains(" "));
            assertFalse(params.get("subscription_items[trial_end][0]").contains(" "));
        }
    }

    @Test
    void paramsEntriesUsedByFormPathStoreTimestampAsUnixSecondsString() {
        // The form-encoded path iterates Params.entries(), which reads the `m` map.
        // The `m` map stores values produced by toValStr(...). Pin that contract.
        Timestamp ts = Timestamp.from(Instant.parse("2026-06-23T09:54:44Z"));
        long expectedUnixSeconds = ts.getTime() / 1000;

        Params p = new Params();
        p.add("expires_at", ts);

        Object stored = null;
        for (java.util.Map.Entry<String, Object> e : p.entries()) {
            if (e.getKey().equals("expires_at")) {
                stored = e.getValue();
                break;
            }
        }
        assertEquals(String.valueOf(expectedUnixSeconds), stored,
                "Form-path storage for Timestamp must remain a Unix-seconds string");
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    private static final class CapturedRequest {
        final HttpURLConnection conn;
        final ByteArrayOutputStream out;

        CapturedRequest(HttpURLConnection conn, ByteArrayOutputStream out) {
            this.conn = conn;
            this.out = out;
        }

        String body() throws Exception {
            return out.toString(StandardCharsets.UTF_8.name());
        }
    }

    private CapturedRequest stubHttpForJsonPost(String responseJson) throws Exception {
        return stubHttpForPost(responseJson, "https://test-site.chargebee.com/api/v2/promotional_grants");
    }

    private CapturedRequest stubHttpForFormPost(String responseJson) throws Exception {
        return stubHttpForPost(responseJson, "https://test-site.chargebee.com/api/v2/subscriptions");
    }

    private CapturedRequest stubHttpForPost(String responseJson, String url) throws Exception {
        HttpURLConnection conn = mock(HttpURLConnection.class);
        ByteArrayOutputStream captured = new ByteArrayOutputStream();

        when(conn.getOutputStream()).thenReturn(captured);
        when(conn.getResponseCode()).thenReturn(200);
        when(conn.getInputStream())
                .thenReturn(new ByteArrayInputStream(responseJson.getBytes(StandardCharsets.UTF_8)));
        when(conn.getHeaderFields()).thenReturn(new HashMap<>());
        when(conn.getURL()).thenReturn(new URL(url));
        when(conn.getRequestMethod()).thenReturn("POST");

        return new CapturedRequest(conn, captured);
    }

    private static Map<String, String> parseFormBody(String body) throws Exception {
        Map<String, String> out = new LinkedHashMap<String, String>();
        if (body == null || body.isEmpty()) {
            return out;
        }
        for (String pair : body.split("&")) {
            int eq = pair.indexOf('=');
            String rawKey = eq < 0 ? pair : pair.substring(0, eq);
            String rawVal = eq < 0 ? "" : pair.substring(eq + 1);
            String key = URLDecoder.decode(rawKey, StandardCharsets.UTF_8.name());
            String val = URLDecoder.decode(rawVal, StandardCharsets.UTF_8.name());
            out.put(key, val);
        }
        return out;
    }

    private static String buildPromotionalGrantResponse(String subId, String unitId, String amount, long expiresAt) {
        JSONObject grant = new JSONObject();
        grant.put("subscription_id", subId);
        grant.put("unit_id", unitId);
        grant.put("amount", amount);
        grant.put("expires_at", expiresAt);
        JSONObject root = new JSONObject();
        root.put("promotional_grant", grant);
        return root.toString();
    }
}
