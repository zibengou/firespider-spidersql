package com.firespider.spidersql.aio.net;

/**
 * 统一资源定位符
 * [protocol:][//host:port][/path][?query][#fragment]
 */
public class URL {
    private String protocol;
    private String host;
    private String port;
    private String path;
    private String query;
    private String fragment;

    public URL(String uri) {
        this.parse(uri);
    }

    public void parse(String uri) {
        // TODO: 2017/9/15 parse uri
    }

    public URL(String protocol, String host, String port, String path, String query, String fragment) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.query = query;
        this.fragment = fragment;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
