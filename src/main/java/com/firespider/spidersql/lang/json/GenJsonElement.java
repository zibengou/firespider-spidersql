package com.firespider.spidersql.lang.json;

import com.firespider.spidersql.lang.Gen;

import java.util.ArrayList;

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

    public boolean isJsonVar() {
        return this instanceof GenJsonVar;
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

    public GenJsonVar getAsVar() {
        if (this.isJsonVar()) {
            return (GenJsonVar) this;
        } else {
            throw new IllegalStateException("This is not a JSON Var.");
        }
    }

    public GenJsonElement getAsElement() {
        return this;
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

    public void setJsonVarElement(GenJsonElement element) {
        if (this.isJsonVar()) {
            this.getAsVar().setElement(element);
        } else if (this.isObject()) {
            ((GenJsonObject) this).entrySet().forEach(v -> v.getValue().setJsonVarElement(element));
        } else if (this.isArray()) {
            ((GenJsonArray) this).iterator().forEachRemaining(e -> e.setJsonVarElement(element));
        }
    }
}
