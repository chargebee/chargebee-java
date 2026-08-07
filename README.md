# Chargebee Java Client Library

> [!NOTE]
> [![Join Discord](https://img.shields.io/badge/Discord-Early%20Access-blue?logo=discord&logoColor=white)](https://discord.gg/gpsNqnhDm2)
>
> We are trialing a Discord server for developers building with Chargebee. Limited spots are open on a first-come basis. Join [here](https://discord.gg/gpsNqnhDm2) if interested.

This is the official Java library for integrating with Chargebee.

- 📘 For a complete reference of available APIs, check out our [API Documentation](https://apidocs.chargebee.com/docs/api/?lang=java).  
- 🧪 To explore and test API capabilities interactively, head over to our [API Explorer](https://api-explorer.chargebee.com).

## Library versions
***

The versioning scheme of this library is inspired by [SemVer](https://semver.org/) and the format is `v{MAJOR}.{MINOR}.{PATCH}`. For example, `v4.0.0` and `v3.0.0` are valid library versions.

The following table provides some details for each major version:

| Library major version | Status   | Compatible API versions                                                                                               | **Branch**        |
|----------------------------|----------|-----------------------------------------------------------------------------------------------------------------------|---------------|
| v4                         | Active   | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) | `v4`      |
| v3                         | Active   | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) | `v3`|
| v2                         | Inactive | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `chargebee-v2`|
| v1                         | Inactive | [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java)                                                             | `chargebee-v1`|

A couple of terms used in the above table are explained below:
- **Status**: The current development status for the library version. **Beta** versions are feature-complete but may have breaking changes. **Active** major versions are currently being maintained and continue to get backward-compatible changes. **Inactive** versions no longer receive any updates.
- **Branch**: The branch in this repository containing the source code for the latest release of the library version. Every version of the library has been [tagged](https://github.com/chargebee/chargebee-java/tags). You can check out the source code for any version using its tag.

🔴 **Attention**: The support for v3 will eventually be discontinued on **December 31st 2026** and will no longer receive any further updates. We strongly recommend upgrading to v4 as soon as possible. See our [migration guide](https://github.com/chargebee/chargebee-java/wiki/Migration-guide-for-V4) for detailed upgrade instructions.

> 💡 **Rest assured**: Your existing v3 integrations will continue to function indefinitely. After December 31st 2026, v3 will no longer receive updates for new API features, but all current functionality will remain operational.

> 📘 **Looking for v3 SDK documentation?** Please refer to the [`v3`](https://github.com/chargebee/chargebee-java/tree/v3) branch for v3 SDK usage and documentation.

**Note:** See the [changelog](CHANGELOG.md) for a history of changes.

## Install the library
***

### Requirement
***
* Java 1.8 or later.

### Installation
***

The SDK provides a modern, immutable client with enhanced type safety and improved developer experience.

#### Gradle (Kotlin DSL)
```kotlin
dependencies {
    implementation("com.chargebee:chargebee-java:{MAJOR}.{MINOR}.{PATCH}")
}
```

#### Maven
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>{MAJOR}.{MINOR}.{PATCH}</version>
</dependency>
```

### Dependencies

The Chargebee Java SDK uses [Gson](https://github.com/google/gson) `2.13.2` for JSON processing. Gson is declared as a regular dependency of the SDK and is therefore included transitively when the SDK is added to a project.

#### Gson version conflicts

Projects that already depend on a different version of Gson may encounter version conflicts. The build tool resolves a single version based on its dependency resolution strategy, which can lead to mismatches.

**Gradle** selects the highest version by default. If the project uses an older Gson version, Gradle will resolve to the highest version (`2.13.2`) by default. To pin a specific version:

```kotlin
configurations.all {
    resolutionStrategy {
        force("com.google.code.gson:gson:2.13.2")
    }
}
```

**Maven** uses the nearest-wins strategy, meaning a direct dependency declared in the project takes precedence over the SDK's transitive dependency. If an older version is declared directly, it will override the SDK's version. To align versions, either upgrade the direct dependency or use `<dependencyManagement>`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.13.2</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**Minimum compatible Gson version**: `2.8.6`. The SDK relies on `JsonParser.parseString()`, which was introduced in Gson 2.8.6. Versions older than 2.8.6 will cause a `NoSuchMethodError` at runtime.

**Recommended**: Gson `2.10.1` or later is recommended for best compatibility. Projects unable to upgrade should verify that the resolved Gson version is at least `2.8.6`.

### Install JAR files
***

#### Obtain pre-built JAR files
***

You can find the JAR files for the library in the [`dist`](dist) directory. To obtain the JAR files for a specific library version, check the `dist` directory within the source tree of the appropriate [tag](https://github.com/chargebee/chargebee-java/tags).

#### Build JAR files
***

Alternatively, you can build the JAR files by following the steps below:

1. Checkout the appropriate version of the library source code.
```shell
git checkout {tagname}
```
For example, the following is valid:
```shell
git checkout v4.0.0
```

2. Clean and build using Maven.
```shell
mvn clean package
```

## Use the library
***

### Quick Start

#### Create a client
```java
import com.chargebee.v4.client.ChargebeeClient;

ChargebeeClient client = ChargebeeClient.builder()
    .apiKey("cb_test_...")
    .siteName("acme")
    .build();
```

#### Create and list customers (sync)
```java
import com.chargebee.v4.services.CustomerService;
import com.chargebee.v4.models.customer.params.CustomerCreateParams;
import com.chargebee.v4.models.customer.params.CustomerListParams;
import com.chargebee.v4.models.customer.responses.CustomerCreateResponse;
import com.chargebee.v4.models.customer.responses.CustomerListResponse;

CustomerService customers = client.customers();

CustomerCreateResponse created = customers.create(
    CustomerCreateParams.builder()
        .firstName("Ada")
        .lastName("Lovelace")
        .email("ada@example.com")
        .billingAddress(
            CustomerCreateParams.BillingAddressParams.builder()
                .line1("50 Market St")
                .city("San Francisco")
                .state("CA")
                .zip("94105")
                .country("US")
                .build()
        )
        .build()
);

CustomerListResponse list = customers.list(
    CustomerListParams.builder()
        .limit(10)
        .email().startsWith("ada@")
        .build()
);
```

#### Configure client with retries and timeouts
```java
import com.chargebee.v4.internal.RetryConfig;

ChargebeeClient client = ChargebeeClient.builder()
    .apiKey("cb_test_...")
    .siteName("acme")
    .retry(
        RetryConfig.builder()
            .enabled(true)
            .maxRetries(3)
            .baseDelayMs(500)
            .build()
    )
    .timeout(10000, 60000)
    .debugLogging(true)
    .build();
```

#### Per-request options
```java
import com.chargebee.v4.client.request.RequestOptions;

CustomerService scoped = client.customers().withOptions(
    RequestOptions.builder()
        .header("Idempotency-Key", "req-123")
        .maxNetworkRetries(2)
        .build()
);
```

#### Async support
```java
import java.util.concurrent.CompletableFuture;
import com.chargebee.v4.transport.Response;

CompletableFuture<Response> future = client.getAsync("/customers");
future.thenAccept(resp -> {
    if (resp.isSuccessful()) {
        System.out.println(resp.getBodyAsString());
    }
});
```

### Dynamic map fields (`Map<String, Object>`)

Some attributes are returned as `Map<String, Object>` for schema-less JSON (for example `HostedPage.getContent()`, `HostedPage.getCheckoutInfo()`, `Event.getContent()`, `meta_data`). Nested JSON objects become `Map`, and nested JSON arrays become `List`:

```java
Map<String, Object> content = client.hostedPages().retrieve(hostedPageId).getHostedPage().getContent();

@SuppressWarnings("unchecked")
Map<String, Object> subscription = (Map<String, Object>) content.get("subscription");
String subscriptionId = (String) subscription.get("id");
```

Every model exposes a `fromJson(Map<String, Object>)` overload, so a nested entity can be converted into a typed model by passing the map directly—no JSON string round-trip needed:

```java
Subscription typedSubscription = Subscription.fromJson(subscription);
```

### Custom Field Filtering

Filter list operations by custom fields using type-safe filters (`stringFilter()`, `numberFilter()`, `timestampFilter()`, `booleanFilter()`):

```java
CustomerListParams params = CustomerListParams.builder()
    .customField("cf_plan_tier").stringFilter().in("gold", "platinum")
    .customField("cf_is_vip").booleanFilter().is(true)
    .customField("cf_total_spent").numberFilter().gte(5000L)
    .build();

CustomerListResponse response = client.customers().list(params);
```

### Exception Handling

The library provides a comprehensive exception hierarchy with **strongly-typed error enums** to handle different types of errors that may occur during API operations.

#### Exception Hierarchy

All Chargebee exceptions extend from `ChargebeeException`, which is an unchecked exception:

```
ChargebeeException (unchecked - extends RuntimeException)
├── ConfigurationException (setup/config errors)
└── TransportException (runtime API errors)
      ├── NetworkException (DNS failures, connection refused)
      ├── TimeoutException (connect/read timeouts)
      └── HttpException (HTTP status code errors: 4xx, 5xx)
            ├── ClientErrorException (4xx errors)
            ├── ServerErrorException (5xx errors)
            └── APIException (Chargebee API errors)
                  ├── InvalidRequestException (validation errors)
                  ├── PaymentException (payment-related errors)
                  ├── OperationFailedException (business logic errors)
                  ├── BatchAPIException (batch operation errors)
                  └── UbbBatchIngestionInvalidRequestException (batch ingestion errors)
```

#### Exception Types

- **`ChargebeeException`**: Base exception for all SDK errors - catch-all for any Chargebee SDK error
- **`ConfigurationException`**: Thrown when SDK configuration is invalid (missing API key, invalid URL, etc.)
- **`TransportException`**: Base exception for all transport-layer failures (network issues, timeouts, etc.)
- **`HttpException`**: Thrown for HTTP error status codes (4xx, 5xx) - contains status code and response
- **`ClientErrorException`**: HTTP 4xx client errors (bad request, unauthorized, not found, etc.)
- **`ServerErrorException`**: HTTP 5xx server errors (internal server error, service unavailable, etc.)
- **`APIException`**: Base exception for Chargebee API errors - includes error type, API error code, and parameters
- **`InvalidRequestException`**: Invalid parameters, missing required fields, or validation errors (type: `invalid_request`)
- **`PaymentException`**: Payment failures such as card declined, insufficient funds, etc. (type: `payment`)
- **`OperationFailedException`**: Business logic violations or state conflicts (type: `operation_failed`)
- **`BatchAPIException`**: Errors specific to batch API operations
- **`UbbBatchIngestionInvalidRequestException`**: Errors specific to UBB batch ingestion operations

#### Strongly-Typed Error Enums

The SDK provides strongly-typed enums for error handling, making it easier to write type-safe error handling code:

##### ErrorType Enum
Represents the type of error returned by the API:
```java
import com.chargebee.v4.exceptions.ErrorType;

// Available values:
ErrorType.INVALID_REQUEST   // Validation and request errors
ErrorType.PAYMENT           // Payment-related errors  
ErrorType.OPERATION_FAILED  // Business logic errors
ErrorType.UNTYPED           // Untyped errors
ErrorType._UNKNOWN          // Unknown/new error types (forward compatibility)
```

##### Per-HTTP-Status API Error Code Enums
Each HTTP status code has its own enum with specific error codes:

| Enum | HTTP Status | Example Error Codes |
|------|-------------|---------------------|
| `BadRequestApiErrorCode` | 400 | `DUPLICATE_ENTRY`, `INVALID_REQUEST`, `PAYMENT_PROCESSING_FAILED`, `PARAM_WRONG_VALUE` |
| `UnauthorizedApiErrorCode` | 401 | `API_AUTHENTICATION_FAILED`, `BASIC_AUTHENTICATION_FAILED` |
| `ForbiddenApiErrorCode` | 403 | `REQUEST_BLOCKED`, `API_AUTHORIZATION_FAILED` |
| `NotFoundApiErrorCode` | 404 | `RESOURCE_NOT_FOUND`, `SITE_NOT_FOUND` |
| `ConflictApiErrorCode` | 409 | `INVALID_STATE_FOR_REQUEST` |
| `TooManyRequestsApiErrorCode` | 429 | `REQUEST_LIMIT_EXCEEDED`, `OPERATION_LIMIT_EXCEEDED` |
| `InternalServerErrorApiErrorCode` | 500 | `INTERNAL_ERROR`, `INTERNAL_TEMPORARY_ERROR` |
| `ServiceUnavailableApiErrorCode` | 503 | `SITE_NOT_READY`, `SITE_MIGRATING`, `SITE_UNDER_MAINTENANCE` |

All enums include an `_UNKNOWN` value for forward compatibility when new error codes are added by the API.

#### Exception Handling Examples

##### Basic exception handling with enums
```java
import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.exceptions.APIException;
import com.chargebee.v4.exceptions.PaymentException;
import com.chargebee.v4.exceptions.codes.ApiErrorCode;
import com.chargebee.v4.exceptions.codes.BadRequestApiErrorCode;
import com.chargebee.v4.models.customer.responses.CustomerCreateResponse;
import com.chargebee.v4.services.CustomerService;

import com.chargebee.v4.exceptions.*;
import com.chargebee.v4.models.customer.params.CustomerCreateParams;

CustomerService customers = client.customers();

try {
    CustomerCreateResponse created = customers.create(
        CustomerCreateParams.builder()
            .email("invalid-email")  // Invalid email format
            .build()
    );
} catch (InvalidRequestException e) {
    // getApiErrorCode() returns a strongly-typed ApiErrorCode enum
    ApiErrorCode errorCode = e.getApiErrorCode();
    
    // Cast to specific enum based on HTTP status code
    if (errorCode instanceof BadRequestApiErrorCode) {
        BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
        if (code == BadRequestApiErrorCode.DUPLICATE_ENTRY) {
            System.err.println("Resource already exists!");
        } else if (code == BadRequestApiErrorCode.PARAM_WRONG_VALUE) {
            System.err.println("Invalid parameter: " + e.getParams());
        }
    }
} catch (PaymentException e) {
    ApiErrorCode errorCode = e.getApiErrorCode();
    
    if (errorCode instanceof BadRequestApiErrorCode) {
        BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
        if (code == BadRequestApiErrorCode.PAYMENT_PROCESSING_FAILED) {
            System.err.println("Payment failed. Please try again.");
        } else if (code == BadRequestApiErrorCode.PAYMENT_METHOD_NOT_PRESENT) {
            System.err.println("No payment method on file.");
        }
    }
} catch (APIException e) {
    // Handle other API errors
    System.err.println("API error: " + e.getMessage());
    System.err.println("Error type: " + e.getErrorType());
} catch (TransportException e) {
    // Handle network/transport errors
    System.err.println("Transport error: " + e.getMessage());
}
```

##### Using switch with ErrorType enum
```java
try {
    // API operation
} catch (APIException e) {
    switch (e.getErrorType()) {
        case INVALID_REQUEST:
            System.err.println("Invalid request: " + e.getMessage());
            break;
        case PAYMENT:
            System.err.println("Payment error: " + e.getMessage());
            break;
        case OPERATION_FAILED:
            System.err.println("Operation failed: " + e.getMessage());
            break;
        default:
            System.err.println("Unknown error type: " + e.getType());
    }
}
```

##### Handling specific error codes
```java
import com.chargebee.v4.exceptions.*;
import com.chargebee.v4.exceptions.codes.ApiErrorCode;
import com.chargebee.v4.exceptions.codes.BadRequestApiErrorCode;

try {
        client.subscriptions().create(params);
} catch (APIException e) {
    ApiErrorCode errorCode = e.getApiErrorCode();
    
    // Check if it's a BadRequest error code
    if (errorCode instanceof BadRequestApiErrorCode) {
        BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
        switch (code) {
            case DUPLICATE_ENTRY:
                System.err.println("Resource already exists");
                break;
            case RESOURCE_LIMIT_EXHAUSTED:
                System.err.println("Limit reached, please upgrade your plan");
                break;
            case PAYMENT_PROCESSING_FAILED:
                System.err.println("Payment failed, please update payment method");
                break;
            case _UNKNOWN:
                // Unknown error code - use raw value for logging
                System.err.println("Unknown error code: " + e.getApiErrorCodeRaw());
                break;
            default:
                System.err.println("Error: " + e.getMessage());
        }
    }
}
```

##### Checking for unknown error types (forward compatibility)
```java
try {
    // API operation
} catch (APIException e) {
    ErrorType errorType = e.getErrorType();
    
    if (!errorType.isKnown()) {
        // New error type added by API that SDK doesn't know about yet
        System.err.println("New error type encountered: " + e.getType());
        // Log for investigation, but handle gracefully
    }
    
    ApiErrorCode errorCode = e.getApiErrorCode();
    if (errorCode != null && !errorCode.isKnown()) {
        // New error code - handle gracefully using raw value
        System.err.println("New error code: " + e.getApiErrorCodeRaw());
    }
}
```

##### Handling HTTP-level errors
```java
import com.chargebee.v4.exceptions.ClientErrorException;
import com.chargebee.v4.exceptions.ServerErrorException;
import com.chargebee.v4.exceptions.HttpException;

try {
    CustomerCreateResponse response = client.customers().create(params);
} catch (ClientErrorException e) {
    // Handle 4xx client errors
    if (e.isUnauthorized()) {
        System.err.println("Authentication failed. Check your API key.");
    } else if (e.isNotFound()) {
        System.err.println("Resource not found.");
    } else if (e.isTooManyRequests()) {
        System.err.println("Rate limit exceeded. Retry after some time.");
    } else {
        System.err.println("Client error: " + e.getStatusCode());
    }
} catch (ServerErrorException e) {
    // Handle 5xx server errors (often retryable)
    if (e.isRetryable()) {
        System.err.println("Server error. Consider retrying: " + e.getMessage());
    }
} catch (HttpException e) {
    System.err.println("HTTP error: " + e.getStatusCode());
}
catch (Exception e) {
    throw new RuntimeException(e);
}
```

##### Error Response Attributes

The `APIException` class provides typed access to all error response attributes:

| Attribute | Method | Description |
|-----------|--------|-------------|
| `message` | `getMessage()` | Descriptive error information (for developer consumption, not for end users) |
| `type` | `getType()` / `getErrorType()` | Error type grouping: `payment`, `invalid_request`, `operation_failed` |
| `api_error_code` | `getApiErrorCode()` / `getApiErrorCodeRaw()` | Strongly-typed enum (`ApiErrorCode`) or raw string for error handling |
| `param` | `getParam()` / `getParams()` | Parameter name(s) if error is parameter-specific |
| `error_cause_id` | `getErrorCauseId()` | Chargebee-defined code for standardizing errors across gateways |

##### Extracting detailed error information
```java
try {
    // API operation
} catch (APIException e) {
    // Get HTTP status code
    int statusCode = e.getStatusCode();
    
    // Get error type as enum (type-safe)
    ErrorType errorType = e.getErrorType();
    
    // Get error type as raw string
    String type = e.getType();
    
    // Get API error code as typed enum
    ApiErrorCode apiErrorCode = e.getApiErrorCode();
    
    // Get API error code as raw string (for logging)
    String apiErrorCodeRaw = e.getApiErrorCodeRaw();
    
    // Get error message (for developer consumption)
    String message = e.getMessage();
    
    // Get parameter name(s) that caused the error
    String param = e.getParam();           // Single param (convenience)
    List<String> params = e.getParams();   // All params
    
    // Get error cause ID (for gateway error standardization)
    String errorCauseId = e.getErrorCauseId();
    
    // Get full JSON response for debugging
    String jsonResponse = e.getJsonResponse();
    
    // Get full HTTP response object
    Response response = e.getResponse();
    
    System.err.println("Error details: " + e.toString());
}
```

##### Handling gateway errors with error_cause_id
```java
try {
    // Payment operation
} catch (PaymentException e) {
    // error_cause_id helps standardize errors across different payment gateways
    String errorCauseId = e.getErrorCauseId();
    
    if (errorCauseId != null) {
        // Use error_cause_id for consistent handling across gateways
        System.err.println("Gateway error cause: " + errorCauseId);
        
    }
    
    // Check the specific API error code using typed enum
    ApiErrorCode errorCode = e.getApiErrorCode();
    if (errorCode instanceof BadRequestApiErrorCode) {
        BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
        if (code == BadRequestApiErrorCode.PAYMENT_PROCESSING_FAILED) {
            // Handle payment failure
        }
    }
}
```

##### Async exception handling

> **Important:** When using async methods, exceptions are wrapped in a `java.util.concurrent.CompletionException`.
> Unlike sync methods that throw `ChargebeeException` directly, async methods deliver errors through
> `CompletableFuture`'s `.exceptionally()` or `.handle()` callbacks, where the original exception is
> available via `throwable.getCause()`. Always unwrap the `CompletionException` to access the
> underlying `ChargebeeException` (e.g., `InvalidRequestException`, `APIException`).

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

CompletableFuture<CustomerCreateResponse> futureCustomer = customers.createAsync(params);

futureCustomer
    .thenAccept(response -> {
        System.out.println("Customer created: " + response.getCustomer().getId());
    })
    .exceptionally(throwable -> {
        // Unwrap CompletionException to get the actual ChargebeeException
        Throwable cause = throwable instanceof CompletionException
            ? throwable.getCause()
            : throwable;

        if (cause instanceof InvalidRequestException) {
            InvalidRequestException e = (InvalidRequestException) cause;
            ApiErrorCode errorCode = e.getApiErrorCode();

            if (errorCode instanceof BadRequestApiErrorCode) {
                BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
                if (code == BadRequestApiErrorCode.DUPLICATE_ENTRY) {
                    System.err.println("Customer already exists");
                }
            } else {
                System.err.println("Validation error: " + e.getMessage());
            }
        } else if (cause instanceof APIException) {
            APIException e = (APIException) cause;
            System.err.println("API error: " + e.getApiErrorCodeRaw());
        } else {
            System.err.println("Unexpected error: " + cause.getMessage());
        }
        return null;
    });
```

If you prefer blocking on the result, use a try-catch around `.join()` or `.get()`:

```java
try {
    CustomerCreateResponse response = customers.createAsync(params).join();
    System.out.println("Customer created: " + response.getCustomer().getId());
} catch (CompletionException e) {
    // Unwrap to get the original ChargebeeException
    Throwable cause = e.getCause();
    if (cause instanceof InvalidRequestException) {
        System.err.println("Validation error: " + cause.getMessage());
    } else if (cause instanceof APIException) {
        System.err.println("API error: " + ((APIException) cause).getApiErrorCodeRaw());
    } else {
        throw e; // Re-throw unexpected errors
    }
}
```

### Retry Handling

Chargebee's SDK includes built-in retry logic to handle temporary network issues and server-side errors. This feature is **disabled by default** but can be **enabled when needed**.

#### Key features include:

- **Automatic retries for specific HTTP status codes**: Retries are automatically triggered for status codes `500`, `502`, `503`, and `504`.
- **Exponential backoff**: Retry delays increase exponentially to prevent overwhelming the server.
- **Rate limit management**: If a `429 Too Many Requests` response is received with a `Retry-After` header, the SDK waits for the specified duration before retrying.
  > *Note: Exponential backoff and max retries do not apply in this case.*
- **Customizable retry behavior**: Retry logic can be configured using the `retryConfig` parameter in the environment configuration.

#### Example: Customizing Retry Logic

You can enable and configure the retry logic by passing a `retryConfig` object when initializing the Chargebee environment:

```java
import com.chargebee.Environment;
public class Sample {
    public static void main(String[] args) throws Exception {
        Environment.configure("{site}", "{site_api_key}");
        Environment.defaultConfig().updateRetryConfig(
            new com.chargebee.internal.RetryConfig(
                true,
                3,
                500,
                new java.util.HashSet<>(java.util.Arrays.asList(500))
            )
        );
        // ... your Chargebee API operations ...
    }
 }
 
```

#### Example: Rate Limit retry logic

You can enable and configure the retry logic for rate-limit by passing a `retryConfig` object when initializing the Chargebee environment:

```java
import com.chargebee.Environment;
public class Sample {
    public static void main(String[] args) throws Exception {
        Environment.configure("{site}", "{site_api_key}");
        Environment.defaultConfig().updateRetryConfig(
            new com.chargebee.internal.RetryConfig(
                true,
                3,
                500,
                new java.util.HashSet<>(java.util.Arrays.asList(429))
            )
        );
        // ... your Chargebee API operations ...
    }
 }
```

### Telemetry (OpenTelemetry)

Optional. Pass a `telemetryAdapter` when you want Chargebee API calls traced in your observability stack (Datadog, Splunk, Honeycomb, Jaeger, etc.). OpenTelemetry is not bundled with `chargebee-java` — add and configure it in your app, implement `TelemetryAdapter`, and wire it on the client.

The SDK builds standardized span attributes (`ctx.getStartAttributes()`, `result.getEndAttributes()`) following the stable [OpenTelemetry HTTP semantic conventions](https://opentelemetry.io/docs/specs/semconv/http/http-spans/) (`url.full`, `http.request.method`, `http.response.status_code`, `server.address`, `error.type`) plus Chargebee-specific `chargebee.*` attributes — use them as-is so spans render correctly in your APM and stay consistent across SDKs.

Spans are named `chargebee.{resource}.{operation}` (e.g. `chargebee.subscription.create`).

#### Server-side timing telemetry (Beta)

> **Beta.** `X-Chargebee-Telemetry` response parsing and `preferChargebeeTelemetry` are in beta. Header availability, wire format, and SDK behavior may change.

Chargebee returns `X-Chargebee-Telemetry` only when the client opts in with `Prefer: chargebee-telemetry=include`. Call `.preferChargebeeTelemetry(true)` on the client builder to have the SDK add that header on each request when a `telemetryAdapter` is configured (parsed into `chargebee.telemetry.*` span attributes). You can also set the `Prefer` header yourself on individual requests.

On select APIs, Chargebee may include an `X-Chargebee-Telemetry` response header with a server-side timing breakdown — treat it as optional enrichment, not a required contract.

When the header is present and a `telemetryAdapter` is configured, the SDK adds span attributes at request end in two layers:

1. **Raw** — the full header string under `http.response.header.x-chargebee-telemetry` (audit, debug, or custom parsing)
2. **Parsed** — typed flat attributes under `chargebee.telemetry.*` (ready for APM dashboards without writing an parser)

The header value is an [RFC 9651](https://www.rfc-editor.org/rfc/rfc9651) `sf-list`: comma-separated segments, each optionally followed by semicolon-separated `key=value` parameters.

```
X-Chargebee-Telemetry: cb;start_time=@1781280400;time_ms=3800, tp-stripe;pm=card;time_ms=620, ft-account_hierarchy
```

| Segment | Meaning | Parsed span attributes |
|---|---|---|
| `cb;…` | Chargebee processing time | `chargebee.telemetry.cb.{param}` — e.g. `time_ms`, `start_time`, `res_wait_time_ms`, `tp_time_ms` |
| `tp-{provider};…` | Third-party call time (Stripe, Avalara, …) | `chargebee.telemetry.tp.{provider}.{param}` — e.g. `chargebee.telemetry.tp.stripe.time_ms` |
| `ft-{feature}` | Feature flag active on this request (bare token, no params) | Collected into `chargebee.telemetry.features` (`string[]`) |

Parameter values are typed by RFC 9651 wire format and mapped to OTel-friendly types:

| Wire format | Example | OTel type |
|---|---|---|
| sf-date (`@epoch`) | `start_time=@1781280400` | `long` (Unix seconds) |
| sf-integer | `time_ms=3800` | `long` |
| sf-decimal | `ratio=99.9` | `double` |
| sf-token (bare word) | `pm=card` | `string` |
| sf-string (quoted) | `desc="hello world"` | `string` |
| sf-boolean | `enabled=?1` / `?0` | `boolean` |
| sf-binary | `payload=:aGVsbG8=:` | `string` (base64 payload) |

For the example header above, `result.getEndAttributes()` at span end includes:

```
http.response.header.x-chargebee-telemetry  →  "cb;start_time=@1781280400;time_ms=3800, tp-stripe;pm=card;time_ms=620, ft-account_hierarchy"
chargebee.telemetry.cb.start_time           →  1781280400
chargebee.telemetry.cb.time_ms              →  3800
chargebee.telemetry.tp.stripe.time_ms       →  620
chargebee.telemetry.tp.stripe.pm            →  "card"
chargebee.telemetry.features                →  ["account_hierarchy"]
```

**Behavior:**

- Header **absent** → no telemetry header attributes are added; the span is unaffected.
- Header **present but unparseable** → only the raw `http.response.header.x-chargebee-telemetry` attribute is emitted; the API call is never failed or delayed by parsing.
- Parsed timing fields such as `chargebee.telemetry.cb.time_ms` are numeric (`long`) so backends like Datadog, New Relic, and Honeycomb can filter, average, and chart percentiles out of the box.

Use the included `OtelTelemetryAdapter` example below as-is to forward both raw and parsed attributes to your exporter.

#### OpenTelemetry example

```kotlin
dependencies {
    implementation("io.opentelemetry:opentelemetry-api:1.49.0")
    implementation("io.opentelemetry:opentelemetry-sdk:1.49.0")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.49.0")
}
```

Configure OpenTelemetry at app startup, then pass your adapter:

```java
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

// App startup — configure once
OtlpGrpcSpanExporter spanExporter =
    OtlpGrpcSpanExporter.builder()
        .setEndpoint(System.getenv().getOrDefault("OTEL_EXPORTER_OTLP_ENDPOINT", "http://localhost:4317"))
        .build();

SdkTracerProvider tracerProvider =
    SdkTracerProvider.builder()
        .addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build())
        .setResource(
            Resource.getDefault()
                .merge(
                    Resource.create(
                        Attributes.of(AttributeKey.stringKey("service.name"), "billing-service"))))
        .build();

OpenTelemetry openTelemetry =
    OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).buildAndRegisterGlobal();
```

```java
import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.telemetry.RequestTelemetryContext;
import com.chargebee.v4.telemetry.RequestTelemetryResult;
import com.chargebee.v4.telemetry.TelemetryAdapter;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.common.AttributesBuilder;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import java.util.List;
import java.util.Map;

class OtelTelemetryAdapter implements TelemetryAdapter {
  private final OpenTelemetry openTelemetry;
  private final Tracer tracer;

  OtelTelemetryAdapter(OpenTelemetry openTelemetry) {
    this.openTelemetry = openTelemetry;
    this.tracer = openTelemetry.getTracer("chargebee-java");
  }

  @Override
  public Object onRequestStart(RequestTelemetryContext ctx, Map<String, String> requestHeaders) {
    AttributesBuilder attrs = Attributes.builder();
    ctx.getStartAttributes().forEach((k, v) -> attrs.put(AttributeKey.stringKey(k), v));

    Span span =
        tracer
            .spanBuilder(ctx.getSpanName())
            .setSpanKind(SpanKind.CLIENT)
            .setAllAttributes(attrs.build())
            .startSpan();

    Context context = Context.current().with(span);
    openTelemetry
        .getPropagators()
        .getTextMapPropagator()
        .inject(context, requestHeaders, (carrier, key, value) -> carrier.put(key, value));

    return span;
  }

  @Override
  public void onRequestEnd(Object handle, RequestTelemetryResult result) {
    if (!(handle instanceof Span)) {
      return;
    }
    Span span = (Span) handle;
    result
        .getEndAttributes()
        .forEach(
            (k, v) -> {
              if (v instanceof String) {
                span.setAttribute(k, (String) v);
              } else if (v instanceof Long) {
                span.setAttribute(k, (Long) v);
              } else if (v instanceof Integer) {
                span.setAttribute(k, ((Integer) v).longValue());
              } else if (v instanceof Double) {
                span.setAttribute(k, (Double) v);
              } else if (v instanceof Boolean) {
                span.setAttribute(k, (Boolean) v);
              } else if (v instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> values = (List<String>) v;
                span.setAttribute(AttributeKey.stringArrayKey(k), values);
              }
            });
    if (result.getError() != null) {
      span.setStatus(StatusCode.ERROR, result.getError().getMessage());
    } else {
      span.setStatus(StatusCode.OK);
    }
    span.end();
  }
}

ChargebeeClient client =
    ChargebeeClient.builder()
        .apiKey("{{api-key}}")
        .siteName("{{site}}")
        .telemetryAdapter(new OtelTelemetryAdapter(openTelemetry))
        .build();
```

Spans are exported by your own OpenTelemetry setup, so they flow to whatever backend you've configured (Datadog, Splunk, Honeycomb, Jaeger, etc.). The Chargebee config above stays the same regardless of backend — refer to your APM vendor's OpenTelemetry/OTLP documentation for exporter endpoints.

## Features

### SDK Features
- Immutable `ChargebeeClient` with fluent builder
- Direct property naming (e.g., `.apiKey()`, `.siteName()`)
- Type-safe request models and responses
- Dynamic `Map<String, Object>` fields deserialize nested JSON as maps and lists (see [Dynamic map fields](#dynamic-map-fields-mapstring-object))
- Sync and async APIs with retry/backoff
- Per-request options and headers
- Enhanced error handling and debugging

## Build & Test
- Java 8+
- `./gradlew build` - Build project
- `./gradlew test` - Run tests

## ProGuard / R8 Configuration

If you're using ProGuard or R8, add the following rules to prevent the SDK from being obfuscated:

```proguard
# Chargebee SDK - preserve all public API classes and models
-keep class com.chargebee.v4.** { *; }
-keepclassmembers class com.chargebee.v4.** { *; }

# Keep all enum values
-keepclassmembers enum com.chargebee.v4.** { *; }
```

These rules ensure that reflection-based serialization/deserialization and the exception hierarchy work correctly at runtime.

## Contribution
***
You may contribute patches to any of the **Active** versions of this library. To do so, raise a PR against the [respective branch](#library-versions). 

If you find something amiss, you are welcome to create an [issue](https://github.com/chargebee/chargebee-java/issues).

## Documentation
***

- [Migration Guide](https://github.com/chargebee/chargebee-java/wiki/Migration-guide-for-V4) - Upgrade instructions between versions

The API documentation for the Java library can be found in our [API reference](https://apidocs.chargebee.com/docs/api?lang=java).

## License
***

See the [LICENSE](LICENSE).