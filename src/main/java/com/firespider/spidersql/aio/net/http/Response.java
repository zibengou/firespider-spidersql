package com.firespider.spidersql.aio.net.http;

import java.nio.charset.Charset;

/**
 * Created by stone on 2017/9/16.
 */
public class Response extends HttpMessage {
    private Request request;

    public Response(byte[] buf) {
        super(buf);
    }

    public Response(byte[] buf, Request request, Charset charset) {
        super(buf, charset);
        this.request = request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public String getBody() {
        return super.getBody();
    }
}
