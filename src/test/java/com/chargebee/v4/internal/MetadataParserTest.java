package com.chargebee.v4.internal;

import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.models.grantBlock.GrantBlock;
import com.chargebee.v4.models.item.Item;
import com.chargebee.v4.models.itemPrice.ItemPrice;
import com.chargebee.v4.models.ledgerOperation.params.LedgerOperationAllocateParams;
import com.chargebee.v4.models.ledgerOperation.responses.LedgerOperationAllocateResponse;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MetadataParserTest {

    private static final Map<String, Object> METADATA = Map.of(
            "grantor", "local-test",
            "grantor_type", "test_operator",
            "reason", "test-promo-claim",
            "nested", Map.of("eligible", true, "amount", 25L),
            "labels", Arrays.asList("café", null),
            "quoted", "a \"quote\"\\path\nline",
            "opaque", "{\"keep\":\"string\"}");

    @ParameterizedTest
    @CsvSource({"false,false", "false,true", "true,false", "true,true"})
    void allocatePreservesMetadataOverLocalHttp(boolean stringEncoded, boolean async) throws Exception {
        JsonObject block = metadataModel(stringEncoded);
        block.addProperty("id", "local-grant");
        block.addProperty("grant_source", "promotional");
        String body = "{\"grant_blocks\":[" + block + "]}";
        byte[] responseBytes = body.getBytes(StandardCharsets.UTF_8);
        AtomicReference<String> requestBody = new AtomicReference<>();
        AtomicReference<String> requestMethod = new AtomicReference<>();
        AtomicReference<String> requestPath = new AtomicReference<>();
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/api/v2/ledger_operations/allocate", exchange -> {
            requestBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
            requestMethod.set(exchange.getRequestMethod());
            requestPath.set(exchange.getRequestURI().getPath());
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseBody().write(responseBytes);
            exchange.close();
        });
        server.start();
        try (ChargebeeClient client = ChargebeeClient.builder("local-test-key", "local-test")
                .endpoint("http://127.0.0.1:" + server.getAddress().getPort() + "/api/v2")
                .build()) {
            LedgerOperationAllocateParams params = LedgerOperationAllocateParams.builder()
                    .subscriptionId("local-subscription").unitId("local-unit")
                    .id("local-allocation").amount("25").metadata(METADATA).build();
            LedgerOperationAllocateResponse response = async
                    ? client.ledgerOperations().allocateAsync(params).get(10, TimeUnit.SECONDS)
                    : client.ledgerOperations().allocate(params);

            assertEquals("POST", requestMethod.get());
            assertEquals("/api/v2/ledger_operations/allocate", requestPath.get());
            assertEquals(JsonUtil.parse(JsonUtil.toJson(METADATA)),
                    JsonUtil.parse(requestBody.get()).get("metadata"));
            assertEquals(1, response.getGrantBlocks().size());
            GrantBlock grant = response.getGrantBlocks().get(0);
            assertEquals("local-grant", grant.getId());
            assertEquals("test-promo-claim", grant.getMetadata().get("reason"));
            assertEquals(METADATA, grant.getMetadata());
        } finally {
            server.stop(0);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void itemAndItemPricePreserveMetadata(boolean stringEncoded) {
        JsonObject model = metadataModel(stringEncoded);
        assertEquals(METADATA, Item.fromJson(model).getMetadata());
        assertEquals(METADATA, ItemPrice.fromJson(model).getMetadata());
    }

    @ParameterizedTest
    @ValueSource(strings = {"{}", "{\"metadata\":null}", "{\"metadata\":{}}", "{\"metadata\":\"{}\"}",
            "{\"metadata\":\"{\"}", "{\"metadata\":[]}", "{\"metadata\":42}", "{\"metadata\":true}"})
    void absentOrInvalidMetadataRemainsEmpty(String json) {
        assertTrue(GrantBlock.fromJson(json).getMetadata().isEmpty());
        assertTrue(Item.fromJson(json).getMetadata().isEmpty());
        assertTrue(ItemPrice.fromJson(json).getMetadata().isEmpty());
    }

    private static JsonObject metadataModel(boolean stringEncoded) {
        JsonObject model = new JsonObject();
        String json = JsonUtil.toJson(METADATA);
        if (stringEncoded) {
            model.addProperty("metadata", json);
        } else {
            model.add("metadata", JsonUtil.parse(json));
        }
        return model;
    }
}
