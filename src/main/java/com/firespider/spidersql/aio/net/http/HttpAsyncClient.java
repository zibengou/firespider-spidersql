package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.core.AsyncSocketExecutor;
import com.firespider.spidersql.aio.net.core.Message;
import com.firespider.spidersql.aio.net.core.Session;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by xiaotong.shi on 2017/9/15.
 */
public class HttpAsyncClient extends AsyncSocketExecutor {

    public HttpAsyncClient() throws IOException {
        super(Runtime.getRuntime().availableProcessors());
    }

    public Map<String, Response> get(Map<String, String> uriMap) {
        final Map<String, Response> responseMap = new ConcurrentHashMap<>();
        List<Session> sessionList = new ArrayList<>();
        for (Map.Entry<String, String> map : uriMap.entrySet()) {
            Request request = new Request(map.getValue());
            Session session = new Session(request.getHost(), request.getPort(), request, map.getKey());
            sessionList.add(session);
        }
        final CountDownLatch latch = new CountDownLatch(sessionList.size());
        try {
            execute(sessionList, new CompletionHandler<Message, Session>() {
                @Override
                public void completed(Message result, Session attachment) {
                    try {
                        latch.countDown();
                        Response res = new Response(result.getBytes());
                        responseMap.put(attachment.getKey(), res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Session attachment) {
                    latch.countDown();
                }
            });
            latch.await();
            close();
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            return responseMap;
        }
    }

    public void close() throws IOException {
        super.close();
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> resq = new HashMap<>();
        for(int i=0;i<10000;i++){
            resq.put(String.valueOf(i), "http://localhost:80");
        }
        System.out.println(System.currentTimeMillis());
        HttpAsyncClient client = new HttpAsyncClient();
        Map<String, Response> responseMap = client.get(resq);
        System.out.println(System.currentTimeMillis());
//        System.out.println(responseMap.get("1").getBody());
    }
}
