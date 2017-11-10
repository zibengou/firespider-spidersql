package com.firespider.spidersql.lang;

/**
 * Created by stone on 2017/9/13.
 */
public class GenNull extends GenElement {
    public static final GenNull INSTANCE = new GenNull();

    @Override
    public GenElement deepCopy() {
        return INSTANCE;
    }

    public int hashCode() {
        return GenNull.class.hashCode();
    }

    public boolean equals(Object other) {
        return this == other || other instanceof GenNull;
    }

    @Override
    public String getAsString() {
        return toString();
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public void setJsonVarElement(GenElement element) {

    }
}
