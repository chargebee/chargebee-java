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
    public int connectTimeout = Integer.getInteger("com.chargebee.api.http.timeout.connect", 30000);

    /**
     * Timeout value, in milliseconds, to be used when reading response from the
     * chargebee api server. If the timeout expires before there is data available
     * for read, a java.net.SocketTimeoutException is raised. A timeout of zero
     * is interpreted as an infinite timeout.
     */
    public int readTimeout = Integer.getInteger("com.chargebee.api.http.timeout.read", 80000);

    public static final String CHARSET = "UTF-8";

    public static final String API_VERSION = "v2";
    
    public static final String LIBRARY_VERSION = "3.2.0";

    private final String apiBaseUrl;

    private static Environment defaultEnv; // singleton
    
    private RequestInterceptor reqInterceptor;
    
    public Environment(String siteName, String apiKey) {
        this(siteName, apiKey, null);
    }
    
    public Environment(String siteName, String apiKey, RequestInterceptor reqInterceptor) {
        this.apiKey = apiKey;
        this.siteName = siteName;
        this.reqInterceptor = reqInterceptor;
        String domainSuffix = System.getProperty("com.chargebee.api.domain.suffix", "chargebee.com");
        String proto = System.getProperty("com.chargebee.api.protocol", "https");
        this.apiBaseUrl = proto + "://" + siteName + "." + domainSuffix + "/api/" + API_VERSION;
    }
    
    public static void configure(String siteName, String apikey) {
        Environment.defaultEnv = new Environment(siteName, apikey);
    }
    
    public static void reqInterceptor(RequestInterceptor reqInterceptor) {
        defaultConfig().reqInterceptor = reqInterceptor;
    }
    
    public RequestInterceptor reqInterceptor() {
        return reqInterceptor;
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

    public static void updateConnectTimeoutInMillis(int connectTimeout) {
        Environment.defaultEnv.connectTimeout = connectTimeout;
    }

    public static void updateReadTimeoutInMillis(int readTimeout) {
        Environment.defaultEnv.readTimeout = readTimeout;
    }

}
