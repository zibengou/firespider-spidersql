package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.URL;

import java.util.Map;

/**
 * Created by stone on 2017/9/16.
 */
public class Request {
    private URL url;

    private Header header;

    private String Method;

    private Map<String,String> params;
}
