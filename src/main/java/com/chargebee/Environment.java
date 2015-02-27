package com.chargebee;

public class Environment {

    /**
     * You can generate API keys from the ChargeBee web interface.
     */
    public final String apiKey;

    /**
     * Your chargebee subdomain. Could be your sandbox or production.
     */
    public final String siteName;

    /**
     * Timeout value, in milliseconds, to be used when trying to conect to the
     * chargebee api server. If the timeout expires before the connection can be
     * established, a java.net.SocketTimeoutException is raised. A timeout of
     * zero is interpreted as an infinite timeout.
     */
    public  int connectTimeout = Integer.getInteger("com.chargebee.api.http.timeout.connect", 15000);

    /**
     * Timeout value, in milliseconds, to be used when reading response from the
     * chargebee api server. If the timeout expires before there is data available
     * for read, a java.net.SocketTimeoutException is raised. A timeout of zero
     * is interpreted as an infinite timeout.
     */
    public  int readTimeout = Integer.getInteger("com.chargebee.api.http.timeout.read", 60000);

    public static final String CHARSET = "UTF-8";

    public static final String API_VERSION = "v1";
    
    public static final String LIBRARY_VERSION = "1.1.54";

    private final String apiBaseUrl;

    private static Environment defaultEnv; // singleton


    public Environment(String siteName, String apiKey) {
        this.apiKey = apiKey;
        this.siteName = siteName;
        String domainSuffix = System.getProperty("com.chargebee.api.domain.suffix", "chargebee.com");
        String proto = System.getProperty("com.chargebee.api.protocol", "https");
        this.apiBaseUrl = proto + "://" + siteName +"." + domainSuffix +"/api/" + API_VERSION;
    }

    public static void configure(String siteName, String apikey) {
        Environment.defaultEnv = new Environment(siteName, apikey);
    }

    public static Environment defaultConfig() {
        if(defaultEnv == null) {
            throw new RuntimeException("The default environment has not been configured");
        }
        return defaultEnv;
    }

    public String apiBaseUrl() {
        return this.apiBaseUrl;
    }

}
