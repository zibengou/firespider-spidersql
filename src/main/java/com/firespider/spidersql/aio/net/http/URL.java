package com.firespider.spidersql.aio.net.http;

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
    private String url;

    public URL(String uri) {
        this.parse(uri);
        this.url = uri;
    }

    public boolean parse(String uri) {
        // TODO: 2017/9/24 避免使用StringBuilder，使用多个标志位与POSITION进行处理
        int pos = 0;
        boolean hasProtocol = false;
        boolean hasHost = false;
        boolean hasPort = false;
        char[] chars = uri.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (; pos < chars.length; pos++) {
            char c = chars[pos];
            char c1 = 0;
            char c2 = 0;
            if (!hasProtocol) {
                c1 = pos + 1 > chars.length ? 0 : chars[pos + 1];
                c2 = pos + 2 > chars.length ? 0 : chars[pos + 2];
            }
            if (c == ':' && c1 == '/' && c2 == '/') {
                this.protocol = sb.toString();
                sb.delete(0, sb.length());
                pos += 2;
                hasProtocol = true;
                continue;
            }
            if (c == ':' && hasProtocol && !hasHost) {
                this.host = sb.toString();
                sb.delete(0, sb.length());
                pos++;
                hasHost = true;
                continue;
            }
            if (c == '/' && hasProtocol && !hasHost) {
                this.host = sb.toString();
                switch (this.protocol) {
                    case "http":
                        this.port = 80;
                        break;
                    case "https":
                        this.port = 443;
                        break;
                    default:
                        this.port = 80;
                        break;
                }
                sb.delete(0, sb.length());
                hasHost = true;
                hasPort = true;
            }
            if (c == '/' && hasHost && !hasPort) {
                this.port = Integer.parseInt(sb.toString());
                sb.delete(0, sb.length());
                hasPort = true;
            }
            sb.append(c);
        }
        this.path = sb.toString();
        return true;
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

    public String getUrl(){
        return this.url;
    }
}
