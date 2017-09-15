package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.AbstractClient;

import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/15.
 */
public class HttpClient extends AbstractClient {
    public HttpClient() throws Exception {
    }

    @Override
    public boolean execute(CompletionHandler handler) {
        return false;
    }
}
