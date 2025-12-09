# Chargebee Java Client Library

> [!NOTE]
> [![Join Discord](https://img.shields.io/badge/Discord-Early%20Access-blue?logo=discord&logoColor=white)](https://discord.gg/S3SXDzXHAg)
>
> We are trialing a Discord server for developers building with Chargebee. Limited spots are open on a first-come basis. Join [here](https://discord.gg/S3SXDzXHAg) if interested.

This is the official Java library for integrating with Chargebee.

- 📘 For a complete reference of available APIs, check out our [API Documentation](https://apidocs.chargebee.com/docs/api/?lang=java).  
- 🧪 To explore and test API capabilities interactively, head over to our [API Explorer](https://api-explorer.chargebee.com).

## Library versions
***

The versioning scheme of this library is inspired by [SemVer](https://semver.org/) and the format is `v{MAJOR}.{MINOR}.{PATCH}`. For example, `v3.0.0` and `v2.5.1` are valid library versions.

The following table provides some details for each major version:

| Library major version | Status   | Compatible API versions                                                                                               | **Branch**        |
|----------------------------|----------|-----------------------------------------------------------------------------------------------------------------------|---------------|
| v4                         | Beta     | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `next`      |
| v3                         | Active   | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `master`|
| v2                         | Inactive | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `chargebee-v2`|
| v1                         | Inactive | [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java)                                                             | `chargebee-v1`|

A couple of terms used in the above table are explained below:
- **Status**: The current development status for the library version. **Beta** versions are feature-complete but may have breaking changes. **Active** major versions are currently being maintained and continue to get backward-compatible changes. **Inactive** versions no longer receive any updates.
- **Branch**: The branch in this repository containing the source code for the latest release of the library version. Every version of the library has been [tagged](https://github.com/chargebee/chargebee-java/tags). You can check out the source code for any version using its tag.

🔴 **Attention**: The support for v2 will eventually be discontinued on **December 31st 2023** and will no longer receive any further updates. We strongly recommend upgrading to the latest version as soon as possible. See our [migration guide](docs/MIGRATION_GUIDE.md) for detailed upgrade instructions.

**Note:** See the [changelog](CHANGELOG.md) for a history of changes.

## Install the library
***

### Requirement
***
* Java 1.8 or later.

### Install v4 SDK (Beta)
***

The v4 SDK provides a modern, immutable client with enhanced type safety and improved developer experience.

#### Gradle (Kotlin DSL)
```kotlin
dependencies {
    implementation("com.chargebee:chargebee-java:4.0.0-beta.1")
}
```

#### Maven
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>4.0.0-beta.2</version>
</dependency>
```

### Install v3 SDK (Stable)
***

You can install any release of an **active** library version by adding the below dependency:

#### Maven
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>{MAJOR}.{MINOR}.{PATCH}</version>
</dependency>
```

For example, the following are valid:

- Install the latest stable version:
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>3.0.0</version>
</dependency>
```

- Install an earlier version, say v2.5.1:
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>2.5.1</version>
</dependency>
```

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
git checkout v3.0.0
```

2. Clean and build using Maven.
```shell
mvn clean package
```

## Use the library
***

### v4 SDK (Beta)

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
import com.chargebee.v4.core.models.customer.params.CustomerCreateParams;
import com.chargebee.v4.core.models.customer.params.CustomerListParams;
import com.chargebee.v4.core.responses.customer.CustomerCreateResponse;
import com.chargebee.v4.core.responses.customer.CustomerListResponse;

CustomerService customers = client.customer();

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
).get();

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

CustomerService scoped = customers.withOptions(
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

### Exception Handling

The library provides a comprehensive exception hierarchy with **strongly-typed error enums** to handle different types of errors that may occur during API operations.

#### Exception Hierarchy

All Chargebee exceptions extend from `TransportException`, which is a checked exception:

```
TransportException (checked)
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

The v4 SDK provides strongly-typed enums for error handling, making it easier to write type-safe error handling code:

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

#### v4 SDK Exception Handling Examples

##### Basic exception handling with enums
```java
import com.chargebee.v4.exceptions.*;
import com.chargebee.v4.services.CustomerService;
import com.chargebee.v4.core.models.customer.params.CustomerCreateParams;

CustomerService customers = client.customer();

try {
    CustomerCreateResponse created = customers.create(
        CustomerCreateParams.builder()
            .email("invalid-email")  // Invalid email format
            .build()
    ).get();
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

try {
    subscriptions.create(params).get();
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
import com.chargebee.v4.transport.ClientErrorException;
import com.chargebee.v4.transport.ServerErrorException;
import com.chargebee.v4.transport.HttpException;

try {
    CustomerCreateResponse response = customers.create(params).get();
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
        
        // Log for analytics/debugging
        logPaymentError(e.getApiErrorCodeRaw(), errorCauseId, e.getMessage());
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
```java
import java.util.concurrent.CompletableFuture;

CompletableFuture<CustomerCreateResponse> futureCustomer = customers.create(params);

futureCustomer
    .thenAccept(customer -> {
        System.out.println("Customer created: " + customer.getId());
    })
    .exceptionally(throwable -> {
        if (throwable.getCause() instanceof InvalidRequestException) {
            InvalidRequestException e = (InvalidRequestException) throwable.getCause();
            ApiErrorCode errorCode = e.getApiErrorCode();
            
            if (errorCode instanceof BadRequestApiErrorCode) {
                BadRequestApiErrorCode code = (BadRequestApiErrorCode) errorCode;
                if (code == BadRequestApiErrorCode.DUPLICATE_ENTRY) {
                    System.err.println("Customer already exists");
                }
            } else {
                System.err.println("Validation error: " + e.getMessage());
            }
        } else if (throwable.getCause() instanceof APIException) {
            APIException e = (APIException) throwable.getCause();
            System.err.println("API error: " + e.getApiErrorCodeRaw());
        } else {
            System.err.println("Unexpected error: " + throwable.getMessage());
        }
        return null;
    });
```

### v3 SDK Examples

#### Create a subscription

```java
import java.io.IOException;
import com.chargebee.*;
import com.chargebee.models.*;
import com.chargebee.models.enums.*;

public class Sample{

  public static void main(String args[]) throws IOException{
    Environment.configure("{site}","{site_api_key}");
    Result result = Subscription.create()
                      .id("HwxfyiHNUFzaiWO")
                      .planId("starter")
                      .customerEmail("john@user.com")
                      .customerFirstName("John")
                      .customerLastName("Wayne").request();
    Subscription subscription = result.subscription();
    Customer customer = result.customer();
    Card card = result.card();
    System.out.println(result);
  }
}
```

#### Create an idempotent request
[Idempotency keys](https://apidocs.chargebee.com/docs/api/idempotency?prod_cat_ver=2) are passed along with request headers to allow a safe retry of POST requests. 

```java
import com.chargebee.models.Card;
import com.chargebee.models.Contact;
import com.chargebee.models.Customer;
import com.chargebee.models.Subscription;
import org.json.JSONObject;

public class Sample {
    public static void main(String[] args) throws Exception {
        Environment.configure("{site}", "{site_api_key}");
        Result result = Customer.create()
                .firstName("John")
                .lastName("Dove")
                .email("john@test.com")
                .setIdempotencyKey("<<UUID>>") // Replace <<UUID>> with a unique string
                .request();
        Customer customer = result.customer();
        System.out.println(result.customer());
        Map<String, List<String>> responseHeaders = result.getResponseHeaders(); // Retrieves response headers
        System.out.println(responseHeaders);
        String idempotencyReplayedHeader = result.isIdempotencyReplayed();// Retrieves idempotency replayed header value 
        System.out.println(idempotencyReplayedHeader);
    }
}
```
`isIdempotencyReplayed()` method can be accessed to differentiate between original and replayed requests.

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

## Features

### v4 SDK (Beta)
- Immutable `ChargebeeClient` with fluent builder
- Direct property naming (e.g., `.apiKey()`, `.siteName()`)
- Type-safe request models and responses
- Sync and async APIs with retry/backoff
- Per-request options and headers
- Enhanced error handling and debugging

### v3 SDK
- Traditional mutable request builders
- Environment-based configuration
- Comprehensive API coverage
- Battle-tested stability

## Build & Test
- Java 8+
- `./gradlew build` - Build project
- `./gradlew test` - Run tests

## Contribution
***
You may contribute patches to any of the **Active** versions of this library. To do so, raise a PR against the [respective branch](#library-versions). 

If you find something amiss, you are welcome to create an [issue](https://github.com/chargebee/chargebee-java/issues).

## Documentation
***

- [Migration Guide](docs/MIGRATION_GUIDE.md) - Upgrade instructions between versions

The API documentation for the Java library can be found in our [API reference](https://apidocs.chargebee.com/docs/api?lang=java).

## License
***

See the [LICENSE](LICENSE).