# Chargebee Java Client Library - API V2

The java library for integrating with Chargebee Recurring Billing and Subscription Management solution.

Chargebee now supports two API versions - [V1](https://apidocs.chargebee.com/docs/api/v1) and [V2](https://apidocs.chargebee.com/docs/api), of which V2 is the latest release and all future developments will happen in V2. 

This library is for our <b>API version V2</b>. The library for V1 can be found in [chargebee-v1 branch](https://github.com/chargebee/chargebee-java/tree/chargebee-v1). 

## Installation

### Maven users
Add the below dependency to your ```pom.xml```:

    <dependency>
      <groupId>com.chargebee</groupId>
      <artifactId>chargebee-java</artifactId>
      <version>[latest-2.x.x-release-version]</version>
    </dependency>


### To get the latest jar

ChargeBee java jar is distributed as part of the release under the ```dist``` directory. Just checkout the latest version for 2.x.x by ```git checkout [latest 2.x.x release tag]```

or

Build the jar by running maven as follows in latest release tag for 2.x.x: 

<pre><code>git checkout [latest 2.x.x release tag]
mvn clean package</code></pre> 

## Documentation

<a href="https://apidocs.chargebee.com/docs/api?lang=java" target="_blank">API Reference</a>

## Usage
You can also copy the below code snippet from our
<a href="https://apidocs.chargebee.com/docs/api?lang=java" target="_blank">API Reference</a>
documentation. We have provided downloadable code for each API operation.


<pre><code>import java.io.IOException;
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
}</code></pre>

## License

See the LICENSE file.

