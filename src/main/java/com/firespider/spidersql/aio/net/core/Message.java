package com.firespider.spidersql.aio.net.core;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.Map;

/**
 * Created by stone on 2017/9/17.
 */
public class Message {
    private ByteBuffer buffer;

    private Protocol protocol;

    public Message(ByteBuffer buffer, Protocol protocol) {
        this.buffer = buffer;
        this.protocol = protocol;
    }

    public Message(Map attribute, Protocol protocol) {

    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public String toString() {
        String res = "";
        try {
            res = protocol.decode(buffer);
        } catch (CharacterCodingException e) {
            res = e.getMessage();
        } finally {
            return res;
        }
    }
}
