package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.core.AsyncSocketExecutor;
import com.firespider.spidersql.aio.net.core.Message;
import com.firespider.spidersql.aio.net.core.Session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

// TODO: 2017/9/26 支持接口扫描，支持https 
public class HttpAsyncClient extends AsyncSocketExecutor {

    private Map<String, String> header;

    private Charset charset;

    public HttpAsyncClient() throws IOException {
        super(Runtime.getRuntime().availableProcessors());
        charset = Charset.defaultCharset();
    }

    public HttpAsyncClient(int threadNum) throws IOException {
        super(threadNum);
    }

    // TODO: 2017/9/28 决定是否需要为上层封装同步实现 
    public Map<Response, Boolean> get(List<String> uriList) throws InterruptedException, IOException {
        final Map<Response, Boolean> responseMap = new ConcurrentHashMap<>();
        List<Session> sessionList = new ArrayList<>();
        for (String uri : uriList) {
            Request request = new Request(uri, this.header);
            sessionList.add(parseSession(request));
        }
        final CountDownLatch latch = new CountDownLatch(sessionList.size());
        handle(sessionList, new CompletionHandler<Response, Response>() {
            @Override
            public void completed(Response result, Response response) {
                responseMap.put(result, true);
                latch.countDown();
            }

            @Override
            public void failed(Throwable exc, Response response) {
                responseMap.put(response, false);
                latch.countDown();
            }
        });
        latch.await();
        return responseMap;
    }

    public Response get(String uri) throws InterruptedException, IOException {
        final Response[] response = new Response[1];
        Request request = new Request(uri, this.header);
        Session session = parseSession(request);
        session.setReadFromChannelHandler(new ReadFromChannelHttpHandler());
        CountDownLatch latch = new CountDownLatch(1);
        handle(session, new CompletionHandler<Response, Response>() {
            @Override
            public void completed(Response result, Response attachment) {
                response[0] = result;
                latch.countDown();
            }

            @Override
            public void failed(Throwable exc, Response attachment) {
                response[0] = attachment;
                latch.countDown();
            }
        });
        latch.await();
        return response[0];

    }

    public void handleGet(List<String> uriList, CompletionHandler<Response, Response> handler) {
        List<Session> sessionList = new ArrayList<>();
        for (String uri : uriList) {
            Request request = new Request(uri, this.header);
            sessionList.add(parseSession(request));
        }
        handle(sessionList, handler);
    }

    public void handleGet(Request request, CompletionHandler<Response, Response> handler) {
        Session session = parseSession(request);
        session.setReadFromChannelHandler(new ReadFromChannelHttpHandler());
        handle(session, handler);
    }

    public void handleScanPort(String host, String port, CompletionHandler<Boolean, String[]> handler) throws IOException {
        Session session = new Session(host, Integer.parseInt(port));
        scanPort(session, new CompletionHandler<Boolean, Session>() {
            @Override
            public void completed(Boolean result, Session attachment) {
                String[] res = new String[3];
                InetSocketAddress address = attachment.getAddress();
                res[0] = address.getAddress().getHostAddress();
                res[1] = address.getAddress().getHostName();
                res[2] = String.valueOf(address.getPort());
                handler.completed(result, res);
            }

            @Override
            public void failed(Throwable exc, Session attachment) {
                String[] res = new String[3];
                InetSocketAddress address = attachment.getAddress();
                res[0] = address.getAddress().getHostAddress();
                res[1] = address.getAddress().getHostName();
                res[2] = String.valueOf(address.getPort());
                handler.failed(exc, res);
            }
        });

    }

    private Session parseSession(Request request) {
        Session session;
        if (request.getProtocol().equals("https")) {
            session = new Session(request.getHost(), request.getPort(), request, true, charset);
        } else {
            session = new Session(request.getHost(), request.getPort(), request, charset);
        }
        session.setReadFromChannelHandler(new ReadFromChannelHttpHandler());
        return session;
    }

    private void handle(List<Session> sessionList, CompletionHandler<Response, Response> handler) {
        for (Session session : sessionList) {
            handle(session, handler);
        }
    }

    /**
     * 处理请求
     * todo 支持请求转发，异常重试功能
     *
     * @param session
     * @param handler
     */
    private void handle(Session session, CompletionHandler<Response, Response> handler) {
        Request request = (Request) session.getWriteToChannelMessage();
        try {
            execute(session, new CompletionHandler<Message, Session>() {
                @Override
                public void completed(Message result, Session session) {
                    Response res = new Response(result.getEffectBytes(), request, charset);
                    handler.completed(res, res);
                }

                @Override
                public void failed(Throwable exc, Session session) {
                    Response res = new Response(session.getReadFromChannelMessage().getEffectBytes(), request, charset);
                    handler.failed(exc, res);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            handler.failed(e, null);
        }

    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void close() throws IOException {
        super.close();
    }


}
