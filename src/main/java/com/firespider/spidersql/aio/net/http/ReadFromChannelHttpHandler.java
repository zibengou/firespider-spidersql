package com.firespider.spidersql.aio.net.http;

import com.firespider.spidersql.aio.net.core.ReadFromChannelHandler;
import com.firespider.spidersql.aio.net.core.Session;

import java.nio.ByteBuffer;

/**
 * Created by stone on 2017/9/24.
 */
public class ReadFromChannelHttpHandler extends ReadFromChannelHandler {
    private int threshold;
    private ByteBuffer readBuffer;
    private HttpMessage message;
    private long length = 0, currentLength = 0;

    public ReadFromChannelHttpHandler(int threshold){
        this.threshold = threshold;
    }
    public ReadFromChannelHttpHandler(){
        this(3);
    }

    @Override
    public void completed(Integer result, Session session) {
        if (readBuffer == null) {
            readBuffer = session.getReadBuffer();
            message = new HttpMessage(session.getReadFromChannelMessage());
            message.setBuffer(readBuffer.array(), 0, readBuffer.position());
            if (message.getHeader() != null && message.getHeader().containsKey("Content-Length")) {
                length = Long.parseLong(message.getHeader().get("Content-Length"));
            }
            currentLength = message.getBodyLength();
        } else {
            message.put(readBuffer.array(), 0, readBuffer.position());
            currentLength += result;
        }
        if (currentLength >= length - threshold) {
            System.out.println(((Request) session.getWriteToChannelMessage()).getUrl());
            session.getCustomHandler().completed(message, session);
        } else {
            readBuffer.clear();
            session.getSocketChannel().read(readBuffer, session, this);
        }
    }
}
