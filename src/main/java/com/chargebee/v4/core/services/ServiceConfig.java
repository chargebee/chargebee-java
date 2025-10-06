package com.chargebee.v4.core.services;

/**
 * Configuration object for service behavior options.
 * This makes adding new options scalable without changing constructor signatures.
 */
public final class ServiceConfig {
    private final boolean rawResponseEnabled;
    
    private ServiceConfig(Builder builder) {
        this.rawResponseEnabled = builder.rawResponseEnabled;
    }
    
    public boolean isRawResponseEnabled() {
        return rawResponseEnabled;
    }
    
    /**
     * Create a default configuration.
     */
    public static ServiceConfig defaultConfig() {
        return new Builder().build();
    }
    
    /**
     * Create a builder to modify this configuration.
     */
    public Builder toBuilder() {
        return new Builder()
            .rawResponse(rawResponseEnabled);
    }
    
    public static final class Builder {
        private boolean rawResponseEnabled = false;
        
        public Builder rawResponse(boolean enabled) {
            this.rawResponseEnabled = enabled;
            return this;
        }
        
        public ServiceConfig build() {
            return new ServiceConfig(this);
        }
    }
    
    @Override
    public String toString() {
        return "ServiceConfig{" +
                "rawResponseEnabled=" + rawResponseEnabled +
                '}';
    }
}