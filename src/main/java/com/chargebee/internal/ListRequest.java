package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import java.io.IOException;

public class ListRequest {

    private String url;
    protected Params params = new Params();

    public ListRequest(String url) {
        this.url = url;
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
        return HttpUtil.getList(url, params(), env);
    }

    protected Params params() {
        return params;
    }
}
