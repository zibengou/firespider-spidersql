package com.firespider.spidersql.aio.net.core;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class AsyncSocketExecutor {

    private final AsynchronousChannelGroup channelGroup;

    private static final int THREAD_NUM = 20;

    public AsyncSocketExecutor() throws IOException {
        this(THREAD_NUM);
    }

    public AsyncSocketExecutor(int threadNum) throws IOException {
        this.channelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(threadNum));
    }

    public void execute(List<Session> sessionList, CompletionHandler<Integer, Message> handler) throws IOException, ExecutionException, InterruptedException {
        for (Session session : sessionList) {
            AsynchronousSocketChannel sc = AsynchronousSocketChannel.open(this.channelGroup);
            sc.setOption(StandardSocketOptions.TCP_NODELAY,
                    true);
            sc.setOption(StandardSocketOptions.SO_REUSEADDR,
                    true);
            sc.setOption(StandardSocketOptions.SO_KEEPALIVE,
                    true);
            session.setCustomHandler(handler);
            session.setSocketChannel(sc);
            sc.connect(session.getAddress(), session, session.getConnectionHandler());
        }
        Thread.sleep(5000);
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsyncSocketExecutor executor = new AsyncSocketExecutor();
        Session session = new Session("localhost", 80);
        executor.execute(Arrays.asList(session), new CompletionHandler<Integer, Message>() {
            @Override
            public void completed(Integer result, Message attachment) {
                System.out.println("customer finished");
            }

            @Override
            public void failed(Throwable exc, Message attachment) {

            }
        });
    }

}
