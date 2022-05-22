package com.github.hrozhek.noizalyzerserver.util;

import java.nio.ByteBuffer;

public class ByteBufferUtil {

    private static final byte[] EMPTY_ARRAY = new byte[0];

    private ByteBufferUtil() {}

    public static byte[] toArray(ByteBuffer buffer) {
        if (buffer == null) {
            return EMPTY_ARRAY;
        }
        if (buffer.hasArray()) {
            return buffer.array();
        }
        buffer.rewind();
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        return array;
    }
}
