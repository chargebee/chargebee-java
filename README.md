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
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerCreateParams;
import com.chargebee.core.models.customer.params.CustomerListParams;
import com.chargebee.core.responses.customer.CustomerCreateResponse;
import com.chargebee.core.responses.customer.CustomerListResponse;

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