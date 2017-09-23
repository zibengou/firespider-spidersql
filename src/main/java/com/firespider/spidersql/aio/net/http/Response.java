package com.firespider.spidersql.aio.net.http;

import java.net.HttpURLConnection;

/**
 * Created by stone on 2017/9/16.
 */
public class Response extends HttpMessage{
    private HttpMessage message;

    public Response(byte[] buf){
        super(buf);
    }

    public String getBody(){
        return super.getBody();
    }
}
