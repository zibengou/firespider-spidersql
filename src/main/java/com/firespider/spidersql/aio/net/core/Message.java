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

    private int capacity;

    private int position;

    private final static int DEFAULT_CAPACITY = 8192;


    private Charset charset;

    protected Message(byte[] buffer, Charset charset) {
        this.buffer = buffer;
        this.capacity = buffer.length;
        this.position = this.capacity;
        this.charset = charset;
    }

    protected Message(int capacity, Charset charset) {
        this.capacity = capacity;
        this.position = 0;
        this.buffer = new byte[capacity];
        this.charset = charset;
    }

    protected Message() {
        this(DEFAULT_CAPACITY, Charset.defaultCharset());
    }

    protected Message(byte[] buffer) {
        this(buffer, Charset.defaultCharset());
    }

    protected Message(String str) {
        this(str.getBytes(), Charset.defaultCharset());
    }

    protected Message(Charset charset) {
        this(DEFAULT_CAPACITY, charset);
    }

    protected Message(String str, Charset charset) {
        this(str.getBytes(charset), charset);
    }

    protected void setBuffer(String str) {
        setBuffer(str.getBytes(charset));
    }

    void setBuffer(byte[] buffer) {
        this.buffer = buffer;
        this.capacity = buffer.length;
        this.position = this.capacity;
    }

    ByteBuffer getBuffer() {
        return ByteBuffer.wrap(buffer, 0, this.position);
    }

    public byte[] getBytes() {
        byte[] res = new byte[this.position];
        for (int i = 0; i < this.position; i++) {
            res[i] = this.buffer[i];
        }
        return res;
    }

    /***
     * 1. 判断扩容
     * 2. 调用系统复制函数
     * 3. 重置有效长度
     * @param newBytes
     */
    void put(final byte[] newBytes) {
        if (newBytes != null && newBytes.length > 0) {
            checkCapacity(newBytes.length);
            System.arraycopy(newBytes, 0, this.buffer, this.position, newBytes.length);
            this.position += newBytes.length;
        }
    }

    void put(final String newStr) {
        put(newStr.getBytes(charset));
    }

    /***
     * 如果新数组长与原有效长度之和大于容量则二倍扩容，若仍不足，则将长度扩容至所需长度
     * @param length
     */
    private void checkCapacity(int length) {
        int wholeLength = this.position + length;
        if (wholeLength > this.capacity) {
            int newCapacity = this.capacity << 1;
            if (wholeLength > newCapacity) {
                newCapacity = wholeLength;
            }
            byte[] newBuffer = new byte[newCapacity];
            System.arraycopy(this.buffer, 0, newBuffer, 0, this.capacity);
            this.capacity = newCapacity;
            this.buffer = newBuffer;
        }
    }

    public String toString() {
        return new String(this.buffer, 0, this.position);
    }

}
