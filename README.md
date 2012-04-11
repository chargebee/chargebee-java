# ChargeBee Java Client Library

The java library for integrating with ChargeBee Recurring Billing and Subscription Management solution.

## Installation

### Maven users
Add the below dependency to your ```pom.xml```:

    <dependency>
      <groupId>com.chargebee</groupId>
      <artifactId>chargebee-java</artifactId>
      <version>1.0.0</version>
    </dependency>


### To download the jar directly

  * Get the ChargeBee Java jar from https://github.com/chargebee/chargebee-java/downloads

## Documentation

  * <a href="https://apidocs.chargebee.com/docs/api?lang=java" target="_blank">API Reference</a>

## Usage
You can also copy the below code snippet from our
<a href="https://apidocs.chargebee.com/docs/api?lang=java" target="_blank">API Reference</a>
documentation. We have provided downloadable code for each API operation.

<pre><code>
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
</code></pre>

## License

See the LICENSE file.

