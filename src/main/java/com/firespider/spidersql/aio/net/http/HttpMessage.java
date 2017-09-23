package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.core.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stone on 2017/9/16.
 */
public class HttpMessage extends Message {
    protected final static String CRLF = "\r\n";

    private String statusLine;

    private Map<String, String> header;

    private String body;

    public HttpMessage() {

    }

    public HttpMessage(String statusLine, Map<String, String> header, String body) {
        super();
        this.statusLine = statusLine;
        this.header = header;
        this.body = body;
    }

    public HttpMessage(byte[] buf) {
        super(buf);
        parse(buf);
    }

    void setHeader(Map<String, String> header) {
//        setBuffer(parse(this.statusLine, header, this.body));
        this.header = header;
    }

    void setBody(String body) {
//        setBuffer(parse(this.statusLine, this.header, body));
        this.body = body;
    }

    void setStatusLine(String statusLine) {
//        setBuffer(parse(statusLine, this.header, this.body));
        this.statusLine = statusLine;
    }

    protected String getStatusLine() {
        return statusLine;
    }

    protected Map<String, String> getHeader() {
        return header;
    }

    protected String getBody() {
        return body;
    }

    public void reset() {
        setBuffer(parse(statusLine, header, body));
    }

    private String parse(String statusLine, Map<String, String> header, String body) {
        StringBuilder res = new StringBuilder();
        res.append(statusLine).append(CRLF);
        for (Map.Entry<String, String> head : header.entrySet()) {
            res.append(head.getKey()).append(": ").append(head.getValue()).append(CRLF);
        }
        res.append(CRLF);
        if (body != null) {
            res.append(body);
        }
        return res.toString();
    }

    private void parse(byte[] buf) {
        this.header = new TreeMap<>();

        int pos = 0;
        boolean hasStatusLine = false;
        boolean hasHeader = false;
        StringBuilder sb = new StringBuilder();
        for (; pos < buf.length; pos++) {
            char c = (char) buf[pos];
            char next = pos + 1 >= buf.length ? 0 : (char) buf[pos + 1];
            if (c == '\r' && next == '\n') {
                if (!hasStatusLine) {
                    this.statusLine = sb.toString();
                    sb.delete(0, sb.length());
                    hasStatusLine = true;
                } else {
                    String headerLine = sb.toString();
                    sb.delete(0, sb.length());
                    String[] split = headerLine.split(": ");
                    if (split.length == 2) {
                        this.header.put(split[0], split[1]);
                    } else {
                        hasHeader = true;
                    }
                }
                ++pos;
                continue;
            }
            sb.append(c);
        }
        this.body = sb.toString();
        sb.delete(0, sb.length());
    }

}
