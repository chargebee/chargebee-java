package com.chargebee.v4.transport;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UrlBuilder Tests")
class UrlBuilderTest {
    
    @Nested
    @DisplayName("buildUrl Tests")
    class BuildUrlTests {
        
        @Test
        @DisplayName("should build URL with base URL only")
        void shouldBuildUrlWithBaseUrlOnly() {
            String result = UrlBuilder.buildUrl("https://api.example.com", null, null);
            assertEquals("https://api.example.com", result);
        }
        
        @Test
        @DisplayName("should build URL with base URL and path")
        void shouldBuildUrlWithBaseUrlAndPath() {
            String result = UrlBuilder.buildUrl("https://api.example.com", "/customers", null);
            assertEquals("https://api.example.com/customers", result);
        }
        
        @Test
        @DisplayName("should build URL with base URL (with trailing slash) and path (with leading slash)")
        void shouldHandleDoubleSlash() {
            String result = UrlBuilder.buildUrl("https://api.example.com/", "/customers", null);
            assertEquals("https://api.example.com/customers", result);
        }
        
        @Test
        @DisplayName("should build URL with base URL (no trailing slash) and path (no leading slash)")
        void shouldHandleNoSlash() {
            String result = UrlBuilder.buildUrl("https://api.example.com", "customers", null);
            assertEquals("https://api.example.com/customers", result);
        }
        
        @Test
        @DisplayName("should build URL with base URL (with trailing slash) and path (no leading slash)")
        void shouldHandleTrailingSlash() {
            String result = UrlBuilder.buildUrl("https://api.example.com/", "customers", null);
            assertEquals("https://api.example.com/customers", result);
        }
        
        @Test
        @DisplayName("should build URL with empty path")
        void shouldBuildUrlWithEmptyPath() {
            String result = UrlBuilder.buildUrl("https://api.example.com", "", null);
            assertEquals("https://api.example.com", result);
        }
        
        @Test
        @DisplayName("should build URL with query parameters")
        void shouldBuildUrlWithQueryParams() {
            Map<String, Object> params = new HashMap<>();
            params.put("limit", "10");
            params.put("offset", "20");
            
            String result = UrlBuilder.buildUrl("https://api.example.com", "/customers", params);
            
            assertTrue(result.startsWith("https://api.example.com/customers?"));
            assertTrue(result.contains("limit=10"));
            assertTrue(result.contains("offset=20"));
            assertTrue(result.contains("&"));
        }
        
        @Test
        @DisplayName("should build URL with path and query parameters")
        void shouldBuildUrlWithPathAndQueryParams() {
            Map<String, Object> params = new HashMap<>();
            params.put("name", "John Doe");
            
            String result = UrlBuilder.buildUrl("https://api.example.com", "/customers", params);
            
            assertTrue(result.startsWith("https://api.example.com/customers?"));
            assertTrue(result.contains("name=John+Doe"));
        }
        
        @Test
        @DisplayName("should build URL with empty query parameters map")
        void shouldBuildUrlWithEmptyQueryParams() {
            Map<String, Object> params = new HashMap<>();
            
            String result = UrlBuilder.buildUrl("https://api.example.com", "/customers", params);
            assertEquals("https://api.example.com/customers", result);
        }
        
        @Test
        @DisplayName("should throw exception when base URL is null")
        void shouldThrowExceptionWhenBaseUrlIsNull() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UrlBuilder.buildUrl(null, "/path", null)
            );
            assertEquals("Base URL cannot be null", exception.getMessage());
        }
    }
    
    @Nested
    @DisplayName("encodeQueryParams Tests")
    class EncodeQueryParamsTests {
        
        @Test
        @DisplayName("should encode single value parameters")
        void shouldEncodeSingleValueParams() {
            Map<String, Object> params = new HashMap<>();
            params.put("name", "John Doe");
            params.put("age", "30");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("name=John+Doe"));
            assertTrue(result.contains("age=30"));
            assertTrue(result.contains("&"));
        }
        
        @Test
        @DisplayName("should encode list value parameters")
        void shouldEncodeListValueParams() {
            Map<String, Object> params = new LinkedHashMap<>();
            List<String> ids = Arrays.asList("id1", "id2", "id3");
            params.put("ids", ids);
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("ids=id1"));
            assertTrue(result.contains("ids=id2"));
            assertTrue(result.contains("ids=id3"));
            assertEquals(2, result.chars().filter(ch -> ch == '&').count());
        }
        
        @Test
        @DisplayName("should encode mixed single and list parameters")
        void shouldEncodeMixedParams() {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", "John");
            params.put("tags", Arrays.asList("tag1", "tag2"));
            params.put("status", "active");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("tags=tag1"));
            assertTrue(result.contains("tags=tag2"));
            assertTrue(result.contains("status=active"));
        }
        
        @Test
        @DisplayName("should handle special characters in parameters")
        void shouldHandleSpecialCharacters() {
            Map<String, Object> params = new HashMap<>();
            params.put("email", "user@example.com");
            params.put("message", "Hello & goodbye!");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("email=user%40example.com"));
            assertTrue(result.contains("message=Hello+%26+goodbye%21"));
        }
        
        @Test
        @DisplayName("should return empty string for null parameters")
        void shouldReturnEmptyStringForNull() {
            String result = UrlBuilder.encodeQueryParams(null);
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should return empty string for empty parameters")
        void shouldReturnEmptyStringForEmpty() {
            String result = UrlBuilder.encodeQueryParams(new HashMap<>());
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should skip null keys")
        void shouldSkipNullKeys() {
            Map<String, Object> params = new HashMap<>();
            params.put(null, "value");
            params.put("valid", "data");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertEquals("valid=data", result);
        }
        
        @Test
        @DisplayName("should skip null values")
        void shouldSkipNullValues() {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", "John");
            params.put("email", null);
            params.put("age", "30");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertFalse(result.contains("email"));
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }
        
        @Test
        @DisplayName("should skip null values in lists")
        void shouldSkipNullValuesInLists() {
            Map<String, Object> params = new LinkedHashMap<>();
            List<String> values = new ArrayList<>();
            values.add("value1");
            values.add(null);
            values.add("value2");
            params.put("items", values);
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("items=value1"));
            assertTrue(result.contains("items=value2"));
            assertEquals(1, result.chars().filter(ch -> ch == '&').count());
        }
        
        @Test
        @DisplayName("should handle empty lists")
        void shouldHandleEmptyLists() {
            Map<String, Object> params = new HashMap<>();
            params.put("items", new ArrayList<String>());
            params.put("name", "test");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertEquals("name=test", result);
        }
        
        @Test
        @DisplayName("should handle lists with all null values")
        void shouldHandleListsWithAllNullValues() {
            Map<String, Object> params = new LinkedHashMap<>();
            List<String> values = new ArrayList<>();
            values.add(null);
            values.add(null);
            params.put("items", values);
            params.put("name", "test");
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertEquals("name=test", result);
        }
        
        @Test
        @DisplayName("should encode numeric values as strings")
        void shouldEncodeNumericValues() {
            Map<String, Object> params = new HashMap<>();
            params.put("count", 100);
            params.put("price", 99.99);
            
            String result = UrlBuilder.encodeQueryParams(params);
            
            assertTrue(result.contains("count=100"));
            assertTrue(result.contains("price=99.99"));
        }
    }
    
    @Nested
    @DisplayName("urlEncode Tests")
    class UrlEncodeTests {
        
        @Test
        @DisplayName("should encode simple string")
        void shouldEncodeSimpleString() {
            String result = UrlBuilder.urlEncode("hello");
            assertEquals("hello", result);
        }
        
        @Test
        @DisplayName("should encode string with spaces")
        void shouldEncodeStringWithSpaces() {
            String result = UrlBuilder.urlEncode("hello world");
            assertEquals("hello+world", result);
        }
        
        @Test
        @DisplayName("should encode special characters")
        void shouldEncodeSpecialCharacters() {
            String result = UrlBuilder.urlEncode("user@example.com");
            assertEquals("user%40example.com", result);
        }
        
        @Test
        @DisplayName("should encode ampersand")
        void shouldEncodeAmpersand() {
            String result = UrlBuilder.urlEncode("rock&roll");
            assertEquals("rock%26roll", result);
        }
        
        @Test
        @DisplayName("should encode equals sign")
        void shouldEncodeEquals() {
            String result = UrlBuilder.urlEncode("a=b");
            assertEquals("a%3Db", result);
        }
        
        @Test
        @DisplayName("should encode question mark")
        void shouldEncodeQuestionMark() {
            String result = UrlBuilder.urlEncode("what?");
            assertEquals("what%3F", result);
        }
        
        @Test
        @DisplayName("should encode slash")
        void shouldEncodeSlash() {
            String result = UrlBuilder.urlEncode("path/to/resource");
            assertEquals("path%2Fto%2Fresource", result);
        }
        
        @Test
        @DisplayName("should encode plus sign")
        void shouldEncodePlus() {
            String result = UrlBuilder.urlEncode("1+1=2");
            assertEquals("1%2B1%3D2", result);
        }
        
        @Test
        @DisplayName("should return empty string for null")
        void shouldReturnEmptyStringForNull() {
            String result = UrlBuilder.urlEncode(null);
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should return empty string for empty string")
        void shouldReturnEmptyStringForEmpty() {
            String result = UrlBuilder.urlEncode("");
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should encode unicode characters")
        void shouldEncodeUnicode() {
            String result = UrlBuilder.urlEncode("café");
            assertTrue(result.contains("%"));
        }
        
        @Test
        @DisplayName("should encode percent sign")
        void shouldEncodePercent() {
            String result = UrlBuilder.urlEncode("100%");
            assertEquals("100%25", result);
        }
    }
    
    @Nested
    @DisplayName("isValidBaseUrl Tests")
    class IsValidBaseUrlTests {
        
        @Test
        @DisplayName("should return true for valid https URL")
        void shouldReturnTrueForValidHttpsUrl() {
            assertTrue(UrlBuilder.isValidBaseUrl("https://api.example.com"));
        }
        
        @Test
        @DisplayName("should return true for valid http URL")
        void shouldReturnTrueForValidHttpUrl() {
            assertTrue(UrlBuilder.isValidBaseUrl("http://api.example.com"));
        }
        
        @Test
        @DisplayName("should return true for URL with path")
        void shouldReturnTrueForUrlWithPath() {
            assertTrue(UrlBuilder.isValidBaseUrl("https://api.example.com/v1"));
        }
        
        @Test
        @DisplayName("should return true for URL with port")
        void shouldReturnTrueForUrlWithPort() {
            assertTrue(UrlBuilder.isValidBaseUrl("https://api.example.com:8080"));
        }
        
        @Test
        @DisplayName("should return false for null URL")
        void shouldReturnFalseForNull() {
            assertFalse(UrlBuilder.isValidBaseUrl(null));
        }
        
        @Test
        @DisplayName("should return false for empty URL")
        void shouldReturnFalseForEmpty() {
            assertFalse(UrlBuilder.isValidBaseUrl(""));
        }
        
        @Test
        @DisplayName("should return false for whitespace only")
        void shouldReturnFalseForWhitespace() {
            assertFalse(UrlBuilder.isValidBaseUrl("   "));
        }
        
        @Test
        @DisplayName("should return false for invalid protocol")
        void shouldReturnFalseForInvalidProtocol() {
            assertFalse(UrlBuilder.isValidBaseUrl("ftp://example.com"));
        }
        
        @Test
        @DisplayName("should return false for malformed URL")
        void shouldReturnFalseForMalformedUrl() {
            assertFalse(UrlBuilder.isValidBaseUrl("not a url"));
        }
        
        @Test
        @DisplayName("should return false for URL without protocol")
        void shouldReturnFalseForUrlWithoutProtocol() {
            assertFalse(UrlBuilder.isValidBaseUrl("example.com"));
        }
        
        @Test
        @DisplayName("should return false for incomplete protocol")
        void shouldReturnFalseForIncompleteProtocol() {
            assertFalse(UrlBuilder.isValidBaseUrl("http:/example.com"));
        }
    }
    
    @Nested
    @DisplayName("joinPaths Tests")
    class JoinPathsTests {
        
        @Test
        @DisplayName("should join two path segments")
        void shouldJoinTwoSegments() {
            String result = UrlBuilder.joinPaths("api", "v1");
            assertEquals("api/v1", result);
        }
        
        @Test
        @DisplayName("should join multiple path segments")
        void shouldJoinMultipleSegments() {
            String result = UrlBuilder.joinPaths("api", "v1", "customers", "123");
            assertEquals("api/v1/customers/123", result);
        }
        
        @Test
        @DisplayName("should handle segments with leading slashes")
        void shouldHandleLeadingSlashes() {
            String result = UrlBuilder.joinPaths("api", "/v1", "/customers");
            assertEquals("api/v1/customers", result);
        }
        
        @Test
        @DisplayName("should handle segments with trailing slashes")
        void shouldHandleTrailingSlashes() {
            String result = UrlBuilder.joinPaths("api/", "v1/", "customers");
            assertEquals("api/v1/customers", result);
        }
        
        @Test
        @DisplayName("should preserve leading slash on first segment")
        void shouldPreserveLeadingSlashOnFirst() {
            String result = UrlBuilder.joinPaths("/api", "v1", "customers");
            assertEquals("/api/v1/customers", result);
        }
        
        @Test
        @DisplayName("should preserve trailing slash on last segment")
        void shouldPreserveTrailingSlashOnLast() {
            String result = UrlBuilder.joinPaths("api", "v1", "customers/");
            assertEquals("api/v1/customers/", result);
        }
        
        @Test
        @DisplayName("should return empty string for null array")
        void shouldReturnEmptyForNull() {
            String result = UrlBuilder.joinPaths((String[]) null);
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should return empty string for empty array")
        void shouldReturnEmptyForEmptyArray() {
            String result = UrlBuilder.joinPaths();
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should skip null segments")
        void shouldSkipNullSegments() {
            String result = UrlBuilder.joinPaths("api", null, "v1", null, "customers");
            assertEquals("api/v1/customers", result);
        }
        
        @Test
        @DisplayName("should skip empty segments")
        void shouldSkipEmptySegments() {
            String result = UrlBuilder.joinPaths("api", "", "v1", "", "customers");
            assertEquals("api/v1/customers", result);
        }
        
        @Test
        @DisplayName("should handle single segment")
        void shouldHandleSingleSegment() {
            String result = UrlBuilder.joinPaths("api");
            assertEquals("api", result);
        }
        
        @Test
        @DisplayName("should handle single segment with leading slash")
        void shouldHandleSingleSegmentWithLeadingSlash() {
            String result = UrlBuilder.joinPaths("/api");
            assertEquals("/api", result);
        }
        
        @Test
        @DisplayName("should handle single segment with trailing slash")
        void shouldHandleSingleSegmentWithTrailingSlash() {
            String result = UrlBuilder.joinPaths("api/");
            assertEquals("api/", result);
        }
        
        @Test
        @DisplayName("should handle segments with multiple slashes")
        void shouldHandleMultipleSlashes() {
            String result = UrlBuilder.joinPaths("api//", "//v1", "customers");
            assertEquals("api//v1/customers", result);
        }
        
        @Test
        @DisplayName("should handle all null segments")
        void shouldHandleAllNullSegments() {
            String result = UrlBuilder.joinPaths(null, null, null);
            assertEquals("", result);
        }
        
        @Test
        @DisplayName("should handle all empty segments")
        void shouldHandleAllEmptySegments() {
            String result = UrlBuilder.joinPaths("", "", "");
            assertEquals("", result);
        }
    }
    
    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        
        @Test
        @DisplayName("should build complex URL with all components")
        void shouldBuildComplexUrl() {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("limit", "50");
            params.put("offset", "100");
            params.put("sort_by", "created_at");
            params.put("filters", Arrays.asList("active", "verified"));
            
            String result = UrlBuilder.buildUrl(
                "https://api.example.com",
                "/v1/customers",
                params
            );
            
            assertTrue(result.startsWith("https://api.example.com/v1/customers?"));
            assertTrue(result.contains("limit=50"));
            assertTrue(result.contains("offset=100"));
            assertTrue(result.contains("sort_by=created_at"));
            assertTrue(result.contains("filters=active"));
            assertTrue(result.contains("filters=verified"));
        }
        
        @Test
        @DisplayName("should handle URL with encoded characters in path and params")
        void shouldHandleEncodedCharacters() {
            Map<String, Object> params = new HashMap<>();
            params.put("email", "user@example.com");
            params.put("name", "John & Jane");
            
            String result = UrlBuilder.buildUrl(
                "https://api.example.com",
                "/customers/search",
                params
            );
            
            assertTrue(result.contains("email=user%40example.com"));
            assertTrue(result.contains("name=John+%26+Jane"));
        }
        
        @Test
        @DisplayName("should validate and build URL")
        void shouldValidateAndBuildUrl() {
            String baseUrl = "https://api.example.com";
            
            assertTrue(UrlBuilder.isValidBaseUrl(baseUrl));
            
            String path = UrlBuilder.joinPaths("api", "v1", "customers");
            String fullUrl = UrlBuilder.buildUrl(baseUrl, path, null);
            
            assertEquals("https://api.example.com/api/v1/customers", fullUrl);
        }
    }
}

