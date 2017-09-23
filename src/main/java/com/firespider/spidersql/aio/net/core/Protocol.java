package com.firespider.spidersql.aio.net.core;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by stone on 2017/9/16.
 */
public interface Protocol<T> {
    T readFormat(byte[] buf);
    byte[] writeFormat(T obj);
}
