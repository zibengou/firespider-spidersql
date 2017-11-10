package com.firespider.spidersql.lang;

public abstract class GenElement extends Gen {
    public abstract GenElement deepCopy();

    public abstract void setJsonVarElement(GenElement element);

    public boolean isArray() {
        return this instanceof GenArray;
    }

    public boolean isObject() {
        return this instanceof GenObject;
    }

    public boolean isPrimitive() {
        return this instanceof GenPrimitive;
    }

    public boolean isJsonVar() {
        return this instanceof GenVar;
    }

    public boolean isScript() {
        return this instanceof GenScript;
    }

    public boolean isNull() {
        return this instanceof GenNull;
    }

    public GenArray getAsArray() {
        if (this.isArray()) {
            return (GenArray) this;
        } else {
            throw new IllegalStateException("This is not a JSON Array.");
        }
    }

    public GenObject getAsObject() {
        if (this.isObject()) {
            return (GenObject) this;
        } else {
            throw new IllegalStateException("This is not a JSON Object.");
        }
    }

    public GenPrimitive getAsPrimitive() {
        if (this.isPrimitive()) {
            return (GenPrimitive) this;
        } else {
            throw new IllegalStateException("This is not a JSON Primitive.");
        }
    }

    public GenVar getAsVar() {
        if (this.isJsonVar()) {
            return (GenVar) this;
        } else {
            throw new IllegalStateException("This is not a JSON Var.");
        }
    }

    public GenElement getAsElement() {
        return this;
    }

    public GenNull getAsNull() {
        if (this.isNull()) {
            return (GenNull) this;
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
