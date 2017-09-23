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

    private String key;

    private AsynchronousSocketChannel socketChannel;

    private InetSocketAddress address;

    private Protocol protocol;

    private ConnectionHandler connectionHandler;

    private ReadFromChannelHandler readHandler;

    private WriteToChannelHandler writeHandler;

    private Message writeToChannelMessage, readFromChannelMessage;

    private ByteBuffer readBuffer;

    private CompletionHandler<Message, Session> customHandler;

    public Session(String host, int port, Message read, Message write, String key) {
        this.address = new InetSocketAddress(host, port);
        this.readHandler = new ReadFromChannelHandler();
        this.writeHandler = new WriteToChannelHandler();
        this.connectionHandler = new ConnectionHandler();
        this.readBuffer = ByteBuffer.allocate(READ_BUF_SIZE);
        this.writeToChannelMessage = write;
        this.readFromChannelMessage = read;
        this.key = key;
    }

    public Session(String host, int port, String key) {
        this(host, port, new Message(), new Message("NULL"), key);
    }

    public Session(String host, int port, Message write, String key) {
        this(host, port, new Message(), write, key);
    }

    void readFromChannel(Integer length, boolean fromWrite) {
        if (fromWrite) {
            this.socketChannel.read(readBuffer, this, this.readHandler);
        } else {
            if (length < READ_BUF_SIZE) {
                byte[] lastBytes = new byte[length];
                System.arraycopy(this.readBuffer.array(), 0, lastBytes, 0, length);
                readFromChannelMessage.put(lastBytes);
                readBuffer.compact();
                this.customHandler.completed(readFromChannelMessage, this);
            } else {
                readFromChannelMessage.put(readBuffer.array());
                readBuffer.compact();
                this.socketChannel.read(readBuffer, this, this.readHandler);
            }
        }
    }

    void handleFail() {
        this.customHandler.failed(new Throwable("fail"), this);
    }

    void writeToChannel() {
        this.socketChannel.write(this.writeToChannelMessage.getBuffer(), this, this.writeHandler);
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

    public String getKey() {
        return key;
    }
}
