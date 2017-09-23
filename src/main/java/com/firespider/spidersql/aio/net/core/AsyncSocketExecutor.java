package com.firespider.spidersql.aio.net.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.StandardSocketOptions;
import java.net.URL;
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

    public void execute(List<Session> sessionList, CompletionHandler<Message, Session> handler) throws IOException, ExecutionException, InterruptedException {
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
    }

    public void close() throws IOException {
        this.channelGroup.shutdownNow();
    }

}
