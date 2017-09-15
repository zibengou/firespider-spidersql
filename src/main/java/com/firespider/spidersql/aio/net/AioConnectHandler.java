package com.firespider.spidersql.aio.net;

import java.util.concurrent.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
public class AioConnectHandler implements CompletionHandler<Void,AsynchronousSocketChannel>{
    private Integer content = 0;
    public AioConnectHandler(Integer value){
        this.content = value;
    }

    public void completed(Void attachment,AsynchronousSocketChannel connector) {
        try {
            connector.write(ByteBuffer.wrap(String.valueOf(content).getBytes())).get();
            startRead(connector);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException ep) {
            ep.printStackTrace();
        }
    }

    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        exc.printStackTrace();
    }

    public void startRead(AsynchronousSocketChannel socket) {
        ByteBuffer clientBuffer = ByteBuffer.allocate(4);
        socket.read(clientBuffer, clientBuffer, new AioReadHandler(socket));
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
