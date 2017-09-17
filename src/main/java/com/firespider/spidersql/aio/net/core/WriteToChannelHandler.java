package com.firespider.spidersql.aio.net.core;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by stone on 2017/9/17.
 */
public class WriteToChannelHandler implements CompletionHandler<Integer, Session> {

    @Override
    public void completed(Integer result, Session session) {
        System.out.println("write success");
        session.readFromChannel(result);
    }

    @Override
    public void failed(Throwable exc, Session session) {
        System.out.println("write failure");
    }
}
