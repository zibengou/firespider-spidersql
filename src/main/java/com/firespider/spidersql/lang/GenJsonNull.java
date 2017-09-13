package com.firespider.spidersql.lang;

/**
 * Created by stone on 2017/9/13.
 */
public class GenJsonNull extends GenJsonElement{
    public static final GenJsonNull INSTANCE = new GenJsonNull();

    @Override
    GenJsonElement deepCopy() {
        return INSTANCE;
    }

    public int hashCode() {
        return GenJsonNull.class.hashCode();
    }

    public boolean equals(Object other) {
        return this == other || other instanceof GenJsonNull;
    }
}
