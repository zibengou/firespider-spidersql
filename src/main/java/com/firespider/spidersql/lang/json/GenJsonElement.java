package com.firespider.spidersql.lang.json;

import com.firespider.spidersql.lang.Gen;

public abstract class GenJsonElement extends Gen {
    abstract GenJsonElement deepCopy();

    public boolean isArray() {
        return this instanceof GenJsonArray;
    }

    public boolean isObject() {
        return this instanceof GenJsonObject;
    }

    public boolean isPrimitive() {
        return this instanceof GenJsonPrimitive;
    }

    public boolean isNull() {
        return this instanceof GenJsonNull;
    }

    public GenJsonArray getAsArray() {
        if (this.isArray()) {
            return (GenJsonArray) this;
        } else {
            throw new IllegalStateException("This is not a JSON Array.");
        }
    }

    public GenJsonObject getAsObject() {
        if (this.isObject()) {
            return (GenJsonObject) this;
        } else {
            throw new IllegalStateException("This is not a JSON Object.");
        }
    }

    public GenJsonPrimitive getAsPrimitive() {
        if (this.isPrimitive()) {
            return (GenJsonPrimitive) this;
        } else {
            throw new IllegalStateException("This is not a JSON Primitive.");
        }
    }

    public GenJsonNull getAsNull() {
        if (this.isNull()) {
            return (GenJsonNull) this;
        } else {
            throw new IllegalStateException("This is not a JSON Null.");
        }
    }

    public String getAsString() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Boolean getAsBoolean() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Integer getAsInteger() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Double getAsDouble() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public Object getValue() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
}
