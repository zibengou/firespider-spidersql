package com.firespider.spidersql.lang;

public class GenJsonPrimitive<T> extends GenJsonElement {
    private T value;

    public GenJsonPrimitive(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    GenJsonElement deepCopy() {
        return this;
    }

    public String getAsString() {
        return String.valueOf(this.value);
    }

    public Boolean getAsBoolean() {
        if (this.value instanceof Boolean) {
            return (Boolean) this.value;
        } else {
            return Boolean.parseBoolean(this.getAsString());
        }
    }

    public Integer getAsInteger() {
        if (this.value instanceof Integer) {
            return (Integer) this.value;
        } else {
            return Integer.parseInt(this.getAsString());
        }
    }

    public Double getAsDouble() {
        if (this.value instanceof Double) {
            return (Double) this.value;
        } else {
            return Double.parseDouble(this.getAsString());
        }
    }

    public T getValue() {
        return this.value;
    }
}
