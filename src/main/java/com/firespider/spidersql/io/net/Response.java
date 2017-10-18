package com.firespider.spidersql.io.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stone on 2017/10/17.
 */
public class Response<T> {
    private int code;
    private Map header;
    private T content;
    private String body;
    private URL url;

    public Response() {
    }

    public Response(HttpURLConnection connection) throws IOException {
        this.code = connection.getResponseCode();
        this.url = connection.getURL();
        this.header = parseHeader(connection.getHeaderFields());
    }

    private Map<String, String> parseHeader(Map<String, List<String>> headerFields) {
        Map<String, String> res = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            if (entry.getKey() != null) {
                if (entry.getValue().size() > 0) {
                    res.put(entry.getKey(), entry.getValue().get(0));
                }
            }
        }
        return res;
    }

    public int getCode() {
        return code;
    }

    public Map getHeader() {
        return header;
    }

    public T getContent() {
        return content;
    }

    public String getBody() {
        return body;
    }

    public URL getUrl() {
        return url;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", header=" + header +
                ", content=" + content +
                ", body='" + body + '\'' +
                ", url=" + url +
                '}';
    }
}
