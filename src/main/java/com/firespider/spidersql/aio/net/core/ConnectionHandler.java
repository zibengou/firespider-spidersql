package com.firespider.spidersql.aio.net.core;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Callable;

/**
 * Created by stone on 2017/9/17.
 */
public class ConnectionHandler implements CompletionHandler<Void, Session> {

    @Override
    public void completed(Void result, Session session) {
        System.out.println("connect success");
        session.writeToChannel();
    }

    @Override
    public void failed(Throwable exc, Session session) {
        System.out.println("connect failure");
    }
}
