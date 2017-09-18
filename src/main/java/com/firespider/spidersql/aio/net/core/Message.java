package com.firespider.spidersql.aio.net.core;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by stone on 2017/9/17.
 */
public class Message {
    private byte[] buffer;


    private Charset charset;

    public Message(String charset) {
        this.buffer = new byte[1024];
        this.charset = Charset.forName(charset);
    }

    public Message(byte[] buffer) {
        this("UTF-8");
        this.buffer = buffer;
    }

    public Message(String msg, String dCharset) {
        this(dCharset);
        this.buffer = msg.getBytes(charset);
    }

    public ByteBuffer getBuffer() {
        return ByteBuffer.wrap(buffer);
    }

    public void put(final byte[] newBytes) {
        if (newBytes != null && newBytes.length > 0) {
            byte[] joinedArray = new byte[buffer.length + newBytes.length];
            System.arraycopy(buffer, 0, joinedArray, 0, buffer.length);
            System.arraycopy(newBytes, 0, joinedArray, buffer.length, newBytes.length);
            this.buffer = joinedArray;
        }
    }

    public void put(final String newStr) {
        put(newStr.getBytes(charset));
    }

    public String toString() {
        String res = new String(buffer, charset);
        return res;
    }

}
