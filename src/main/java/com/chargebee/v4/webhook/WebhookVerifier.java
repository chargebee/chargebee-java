/*
 * Copyright 2025 Chargebee Inc.
 */

package com.chargebee.v4.webhook;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class for verifying Chargebee webhook requests using HTTP Basic Authentication.
 * 
 * <p>Chargebee supports optional HTTP Basic Authentication for webhooks. When configured,
 * Chargebee sends the username and password in the Authorization header as Base64-encoded
 * credentials.</p>
 * 
 * <p>To enable webhook authentication in Chargebee:</p>
 * <ol>
 *   <li>Go to Settings → Configure Chargebee → Webhooks</li>
 *   <li>Edit your webhook endpoint</li>
 *   <li>Enable "Basic Authentication" and set username/password</li>
 * </ol>
 * 
 * <p>Usage example:</p>
 * <pre>
 * WebhookVerifier verifier = new WebhookVerifier();
 * boolean isValid = verifier.verifyBasicAuth(
 *     authorizationHeader,
 *     expectedUsername,
 *     expectedPassword
 * );
 * </pre>
 * 
 * @see <a href="https://apidocs.chargebee.com/docs/api/events">Chargebee Events API</a>
 */
public class WebhookVerifier {

    private static final String BASIC_AUTH_PREFIX = "Basic ";

    /**
     * Verifies the webhook request using HTTP Basic Authentication.
     * 
     * <p>Chargebee sends credentials in the Authorization header as:
     * "Basic base64(username:password)"</p>
     *
     * @param authorizationHeader The Authorization header value from the request
     * @param expectedUsername The expected username configured in Chargebee
     * @param expectedPassword The expected password configured in Chargebee
     * @return true if credentials match, false otherwise
     */
    public boolean verifyBasicAuth(String authorizationHeader, String expectedUsername, String expectedPassword) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BASIC_AUTH_PREFIX)) {
            return false;
        }

        if (expectedUsername == null || expectedPassword == null) {
            return false;
        }

        try {
            // Extract Base64 encoded credentials
            String base64Credentials = authorizationHeader.substring(BASIC_AUTH_PREFIX.length()).trim();
            
            // Decode credentials
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
            
            // Split into username:password
            int colonIndex = decodedCredentials.indexOf(':');
            if (colonIndex == -1) {
                return false;
            }
            
            String receivedUsername = decodedCredentials.substring(0, colonIndex);
            String receivedPassword = decodedCredentials.substring(colonIndex + 1);
            
            // Use constant-time comparison to prevent timing attacks
            boolean usernameMatch = secureCompare(receivedUsername, expectedUsername);
            boolean passwordMatch = secureCompare(receivedPassword, expectedPassword);
            
            return usernameMatch && passwordMatch;
            
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Extracts username from the Authorization header.
     *
     * @param authorizationHeader The Authorization header value
     * @return The username, or null if extraction fails
     */
    public String extractUsername(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BASIC_AUTH_PREFIX)) {
            return null;
        }

        try {
            String base64Credentials = authorizationHeader.substring(BASIC_AUTH_PREFIX.length()).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
            
            int colonIndex = decodedCredentials.indexOf(':');
            if (colonIndex == -1) {
                return null;
            }
            
            return decodedCredentials.substring(0, colonIndex);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Performs a constant-time comparison of two strings to prevent timing attacks.
     *
     * @param a First string
     * @param b Second string
     * @return true if strings are equal, false otherwise
     */
    private boolean secureCompare(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        
        byte[] aBytes = a.getBytes(StandardCharsets.UTF_8);
        byte[] bBytes = b.getBytes(StandardCharsets.UTF_8);
        
        if (aBytes.length != bBytes.length) {
            return false;
        }
        
        int result = 0;
        for (int i = 0; i < aBytes.length; i++) {
            result |= aBytes[i] ^ bBytes[i];
        }
        return result == 0;
    }
}

