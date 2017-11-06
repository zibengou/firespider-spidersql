package com.firespider.spidersql.io.net;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by stone on 2017/10/17.
 */
public class NetUtil {
    private static int timeout = 1000;

    public static Response get(String path, Map<String, String> header, Charset charset) throws IOException {
        HttpURLConnection conn = getConn(path, header);
        checkResponse(conn);
        return parseResponse(conn, charset);
//        return readInputStream(conn.getInputStream());
    }

    public static Response get(String path) throws IOException {
        return get(path, null, Charset.defaultCharset());
    }

    public static Response post(String path, String data, Map<String, String> header, Charset charset) throws IOException {
        HttpURLConnection conn = getConn(path, header);
        conn.setRequestMethod("POST");
        OutputStream op = conn.getOutputStream();
        op.write(data.getBytes(charset));
        op.close();
        return parseResponse(conn, charset);
    }

    public static Response post(String path, String data) throws IOException {
        return post(path, data, null, Charset.defaultCharset());
    }

    public static String conn(String host, int port) throws IOException {
        Socket socket = new Socket();
        SocketAddress remoteAddr = new InetSocketAddress(host, port);
        socket.connect(remoteAddr, timeout);
        String address = socket.getInetAddress().getHostAddress();
        socket.close();
        return address;
    }

    private static HttpURLConnection getConn(String path, Map<String, String> header) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        if (header != null) {
            header.forEach(connection::setRequestProperty);
        }
        connection.connect();
        return connection;
    }

    private static HttpURLConnection getConn(String path, Map<String, String> header, Proxy proxy) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();
        return connection;
    }

    private static String readInputStream(InputStream inputStream, Charset charset) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, charset));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static boolean checkResponse(HttpURLConnection connection) throws IOException {
        String res = connection.getResponseMessage();
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private static Response parseResponse(HttpURLConnection conn, Charset charset) throws IOException {
        Response response = new Response(conn);
        String body = readInputStream(conn.getInputStream(), charset);
        response.setBody(body);
        return response;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(get("https://www.baidu.com"));
    }
}
