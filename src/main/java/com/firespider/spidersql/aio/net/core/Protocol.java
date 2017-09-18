package com.firespider.spidersql.aio.net.core;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by stone on 2017/9/16.
 */
public class Protocol {

    private final CharsetDecoder decoder;

    public Protocol() {
        this("UTF-8");
    }

    public Protocol(String charset) {
        decoder = Charset.forName(charset).newDecoder();
    }

    public String decode(ByteBuffer bf) throws CharacterCodingException {

        return String.valueOf(decoder.decode(bf));
    }
}
