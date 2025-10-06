package com.chargebee.v4.transport;

import java.util.Arrays;
import java.util.Objects;

/**
 * Raw request body for custom content.
 */
class RawRequestBody extends RequestBody {
    private final byte[] data;
    private final String contentType;
    
    RawRequestBody(byte[] data, String contentType) {
        this.data = data != null ? data.clone() : new byte[0];
        this.contentType = contentType;
    }
    
    @Override
    public String getContentType() {
        return contentType;
    }
    
    @Override
    public byte[] getBytes() {
        return data.clone();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawRequestBody)) return false;
        RawRequestBody that = (RawRequestBody) o;
        return Arrays.equals(data, that.data) && Objects.equals(contentType, that.contentType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(data), contentType);
    }
    
    @Override
    public String toString() {
        return "RawRequestBody{contentType='" + contentType + "', dataLength=" + data.length + "}";
    }
}