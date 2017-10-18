package com.firespider.spidersql.io.net;


import java.nio.channels.CompletionHandler;

/**
 * Created by stone on 2017/10/17.
 */
public interface IHttpAsyncClient {

    void handleGet(String url, CompletionHandler<Response, String> handler);

    void handleScanPort(String host, String port, CompletionHandler<Boolean, String> handler);
}
