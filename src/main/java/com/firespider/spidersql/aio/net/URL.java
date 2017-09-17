package com.firespider.spidersql.aio.net;

import com.sun.corba.se.pept.protocol.ProtocolHandler;
import com.sun.deploy.net.protocol.ProtocolType;
import org.apache.logging.log4j.core.net.Protocol;

import java.net.ProtocolFamily;

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

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String getFragment() {
        return fragment;
    }
}
