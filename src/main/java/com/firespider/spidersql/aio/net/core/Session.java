package com.firespider.spidersql.aio.net.core;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.List;

/**
 * Created by stone on 2017/9/17.
 */
public class Session {
    private static final int READ_BUF_SIZE = 1024;

    private AsynchronousSocketChannel socketChannel;

    private InetSocketAddress address;

    private Protocol protocol;

    private ConnectionHandler connectionHandler;

    private ReadFromChannelHandler readHandler;

    private WriteToChannelHandler writeHandler;

    private Message writeToChannelMessage, readFromChannelMessage;

    private ByteBuffer readBuffer;

    private CompletionHandler<Integer, Message> customHandler;

    public Session(String host, int port) {
        this.address = new InetSocketAddress(host, port);
        this.protocol = new Protocol();
        this.readHandler = new ReadFromChannelHandler();
        this.writeHandler = new WriteToChannelHandler();
        this.connectionHandler = new ConnectionHandler();
        this.readBuffer = ByteBuffer.allocate(READ_BUF_SIZE);
        this.writeToChannelMessage = new Message(ByteBuffer.wrap("aaa".getBytes()),protocol);
    }

    public void readFromChannel(Integer writeResult) {
        this.socketChannel.read(readBuffer, this, this.readHandler);
    }

    public void writeToChannel() {
        this.socketChannel.write(this.writeToChannelMessage.getBuffer(), this, this.writeHandler);
    }

    public void handleCustomHandler(Integer readResult) {
        if(readResult > 0) {
            readBuffer.flip();
            System.out.println("read in");
            System.out.println(new Message(readBuffer, protocol).toString());
            readBuffer.compact();
        }
//        readFromChannelMessage = new Message(readBuffer, protocol);
        this.customHandler.completed(readResult, readFromChannelMessage);
    }

    public AsynchronousSocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public ReadFromChannelHandler getReadHandler() {
        return readHandler;
    }

    public WriteToChannelHandler getWriteHandler() {
        return writeHandler;
    }

    public void setCustomHandler(CompletionHandler customHandler) {
        this.customHandler = customHandler;
    }

    public Message getWriteToChannelMessage() {
        return writeToChannelMessage;
    }

    public void setWriteToChannelMessage(Message writeToChannelMessage) {
        this.writeToChannelMessage = writeToChannelMessage;
    }

    public Message getReadFromChannelMessage() {
        return readFromChannelMessage;
    }

    public void setReadFromChannelMessage(Message readFromChannelMessage) {
        this.readFromChannelMessage = readFromChannelMessage;
    }
}
