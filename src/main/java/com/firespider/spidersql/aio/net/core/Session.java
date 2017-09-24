package com.firespider.spidersql.aio.net.core;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by stone on 2017/9/17.
 */
public class Session {
    private static final int READ_BUF_SIZE = 4096;

    private static final int TIMEOUT = 5;

    private int timeout;

    private int bufSize;

    private AsynchronousSocketChannel socketChannel;

    private InetSocketAddress address;

    private ConnectionHandler connectionHandler;

    private ReadFromChannelHandler readHandler;

    private WriteToChannelHandler writeHandler;

    private Message writeToChannelMessage, readFromChannelMessage;

    private SSLManager sslManager;

    private ByteBuffer readBuffer;

    private CompletionHandler<Message, Session> customHandler;

    private Charset charset;

    public Session(String host, int port, Message read, Message write, boolean useSSL) {
        this(host, port, read, write, TIMEOUT, READ_BUF_SIZE, useSSL);
    }

    public Session(String host, int port, Message read, Message write, int timeout, int bufSize, boolean useSSL) {
        this.address = new InetSocketAddress(host, port);
        this.readHandler = new ReadFromChannelHandler();
        this.writeHandler = new WriteToChannelHandler();
        this.connectionHandler = new ConnectionHandler();
        this.bufSize = bufSize;
        this.readBuffer = ByteBuffer.allocate(bufSize);
        this.writeToChannelMessage = write;
        this.readFromChannelMessage = read;
        this.timeout = timeout;
        if (useSSL) {
            try {
                this.sslManager = new SSLManager("TLSv1.2", host, port);
            } catch (SSLException e) {
                e.printStackTrace();
            }
        } else {
            this.sslManager = null;
        }
    }

    public Session(String host, int port) {
        this(host, port, new Message(), new Message(), false);
    }

    public Session(String host, int port, Message write, boolean useSSL, Charset charset) {
        this(host, port, new Message(charset), write, useSSL);
        this.charset = charset;
    }

    public Session(String host, int port, Message write, Charset charset) {
        this(host, port, new Message(charset), write, false);
        this.charset = charset;
    }

    public void setReadFromChannelHandler(ReadFromChannelHandler handler) {
        this.readHandler = handler;
    }

    void readFromChannel(Integer length, boolean fromWrite) {
        if (fromWrite) {
            this.socketChannel.read(readBuffer, timeout, TimeUnit.SECONDS, this, this.readHandler);
        } else {
            if (length > 0) {
                readFromChannelMessage.put(readBuffer.array(), 0, readBuffer.position());
                readBuffer.clear();
                this.socketChannel.read(readBuffer, timeout, TimeUnit.SECONDS, this, this.readHandler);
            } else {
                this.customHandler.completed(readFromChannelMessage, this);
            }
        }
    }

    void doSSLHandShake() {
        try {
            sslManager.doHandShake(this);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
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

    public CompletionHandler<Message, Session> getCustomHandler() {
        return customHandler;
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public boolean isSSL() {
        return sslManager != null;
    }
}
