package com.firespider.spidersql.aio.net.core;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

public class AsyncSocketExecutor {

    private final AsynchronousChannelGroup channelGroup;

    private final ExecutorService service;

    private static final int THREAD_NUM = 20;

    public AsyncSocketExecutor() throws IOException {
        this(THREAD_NUM);
    }

    public AsyncSocketExecutor(int threadNum) throws IOException {
        this.service = Executors.newFixedThreadPool(threadNum);
        this.channelGroup = AsynchronousChannelGroup.withThreadPool(service);
    }

    protected void execute(Session session, CompletionHandler<Message, Session> handler) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel sc = AsynchronousSocketChannel.open(this.channelGroup);
        session.setCustomHandler(handler);
        session.setSocketChannel(sc);
        sc.setOption(StandardSocketOptions.TCP_NODELAY, true);
        sc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        sc.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        sc.connect(session.getAddress(), session, session.getConnectionHandler());
    }

    protected void scanPort(Session session, CompletionHandler<Boolean, Session> handler) throws IOException {
        AsynchronousSocketChannel sc = AsynchronousSocketChannel.open(this.channelGroup);
        session.setCustomHandler(handler);
        session.setSocketChannel(sc);
        sc.connect(session.getAddress(), session, new CompletionHandler<Void, Session>() {
            @Override
            public void completed(Void result, Session attachment) {
                handler.completed(true, session);
            }

            @Override
            public void failed(Throwable exc, Session attachment) {
                handler.failed(exc, session);
            }
        });
    }

    public void close() throws IOException {
        this.channelGroup.shutdownNow();
    }

}
