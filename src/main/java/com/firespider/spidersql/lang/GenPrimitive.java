package com.firespider.spidersql.lang;

public class GenPrimitive<T> extends GenElement {
    private T value;

    public GenPrimitive(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public GenElement deepCopy() {
        return this;
    }

    public boolean isInt() {
        return value instanceof Integer;
    }

    public boolean isDobule() {
        return value instanceof Double;
    }

    public boolean isString() {
        return value instanceof String;
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

    @Override
    public String toString() {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else {
            return value.toString();
        }
    }

    @Override
    public void setJsonVarElement(GenElement element) {

    }
}
