package com.firespider.spidersql.aio.net.http;

import com.sun.corba.se.pept.protocol.ProtocolHandler;
import com.sun.deploy.net.protocol.ProtocolType;
import org.apache.logging.log4j.core.net.Protocol;

import java.net.MalformedURLException;
import java.net.ProtocolFamily;

/**
 * 统一资源定位符
 * [protocol:][//host:port][/path][?query][#fragment]
 */
public class URL {
    private String protocol;
    private String host;
    private int port;
    private String path;
    private String query;
    private String fragment;

    public URL(String uri) {
        this.parse(uri);
    }

    public boolean parse(String uri) {
        String[] c1 = uri.trim().split("://");
        if (c1.length == 2) {
            this.protocol = c1[0];
            String[] c2 = c1[1].split("/");
            String hostPort = c2[0];
            String[] c3 = hostPort.split(":");
            this.host = c3[0];
            this.port = Integer.parseInt(c3[1]);
            this.path = c2.length > 1 ? c2[2] : "/";
            return true;
        } else {
            return false;
        }
    }

    public URL(String protocol, String host, int port, String path, String query, String fragment) {
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

    public int getPort() {
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
