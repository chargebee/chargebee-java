package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import java.io.IOException;

public class ListRequest<U extends ListRequest> extends RequestBase<U>{


    public ListRequest(String uri) {
        this.uri = uri;
    }

    public U limit(int limit) {
        params.addOpt("limit", limit);
        return (U)this;
    }

    public U offset(String offset) {
        params.addOpt("offset", offset);
        return (U)this;
    }

    public final ListResult request() throws IOException {
        return request(Environment.defaultConfig());
    }

    public final ListResult request(Environment env) throws IOException {
        if(env == null) {
            throw new RuntimeException("Environment cannot be null");
        }
        String url = new StringBuilder(env.apiBaseUrl()).append(uri).toString();
        return HttpUtil.getList(url, params(),headers, env);
    }

}
