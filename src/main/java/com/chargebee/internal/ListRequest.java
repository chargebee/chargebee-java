package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import java.io.IOException;

public class ListRequest {

    private String uri;
    protected Params params = new Params();

    public ListRequest(String uri) {
        this.uri = uri;
    }

    public ListRequest limit(int limit) {
        params.addOpt("limit", limit);
        return this;
    }

    public ListRequest offset(String offset) {
        params.addOpt("offset", offset);
        return this;
    }

    public final ListResult request() throws IOException {
        return request(Environment.defaultConfig());
    }

    public final ListResult request(Environment env) throws IOException {
        if(env == null) {
            throw new RuntimeException("Environment cannot be null");
        }
        String url = new StringBuilder(env.apiBaseUrl()).append(uri).toString();
        return HttpUtil.getList(url, params(), env);
    }

    protected Params params() {
        return params;
    }
}
