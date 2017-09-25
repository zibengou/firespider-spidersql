package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.core.AsyncSocketExecutor;
import com.firespider.spidersql.aio.net.core.Message;
import com.firespider.spidersql.aio.net.core.Session;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class HttpAsyncClient extends AsyncSocketExecutor {

    private Map<String, String> header;

    private Charset charset;

    public HttpAsyncClient() throws IOException {
        super(Runtime.getRuntime().availableProcessors());
        charset = Charset.defaultCharset();
    }

    public HttpAsyncClient(String charset) throws IOException {
        super(Runtime.getRuntime().availableProcessors());
        this.charset = Charset.forName(charset);
    }

    public HttpAsyncClient(Map<String, String> header, String charset) throws IOException {
        this(charset);
        this.header = header;
    }

    public List<Response> get(List<String> uriList) throws InterruptedException, IOException {
        final List<Response> responseList = new ArrayList<>();
        List<Session> sessionList = new ArrayList<>();
        for (String uri : uriList) {
            Request request = new Request(uri, this.header);
            sessionList.add(parseSession(request));
        }
        final CountDownLatch latch = new CountDownLatch(sessionList.size());
        handle(sessionList, new CompletionHandler<Response, Response>() {
            @Override
            public void completed(Response result, Response response) {
                responseList.add(result);
                latch.countDown();
            }

            @Override
            public void failed(Throwable exc, Response response) {
                responseList.add(response);
                latch.countDown();
            }
        });
        latch.await();
        close();
        return responseList;
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

    public void close() throws IOException {
        super.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, String> header = new HashMap<>();
//        header.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        // TODO: 2017/9/24 底层支持各种压缩格式 
//        header.put("Accept-Encoding","gzip, deflate");
        HttpAsyncClient client = new HttpAsyncClient("GBK");
        List<String> urlList = new ArrayList<>();
        for (int i = 154411; i < 154422; i++) {
            String url = String.format("http://www.52ij.com/jishu/aspx/%s.html", String.valueOf(i));
//            String url = "http://localhost/";
            urlList.add(url);
        }
        long start = System.currentTimeMillis();
        client.get("https://www.zhihu.com/");
        System.out.println(System.currentTimeMillis() - start);
        client.close();
    }
}
