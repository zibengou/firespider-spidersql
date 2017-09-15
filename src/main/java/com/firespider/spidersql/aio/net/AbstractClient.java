package com.firespider.spidersql.aio.net;

import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractClient {
    private final AsynchronousChannelGroup asyncChannelGroup;

    public AbstractClient() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        asyncChannelGroup = AsynchronousChannelGroup.withThreadPool(executor);
    }

    public abstract boolean execute(CompletionHandler handler);

    public AbstractClient connect(){
        return this;
    }

    public static void main(String... args) throws Exception {
//        AbstractClient client = new AbstractClient();
//        client.start("localhost", 8070);
    }
}
