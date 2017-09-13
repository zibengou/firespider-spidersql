package com.firespider.spidersql.lang;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by stone on 2017/9/13.
 */
public class GenJsonObject extends GenJsonElement {
    private final LinkedHashMap<String, GenJsonElement> members = new LinkedHashMap<>();

    GenJsonObject deepCopy() {
        GenJsonObject result = new GenJsonObject();
        for (Entry<String, GenJsonElement> o : this.members.entrySet()) {
            result.add((String) ((Entry) o).getKey(), ((GenJsonElement) ((Entry) o).getValue()).deepCopy());
        }
        return result;
    }

    public void add(String key, GenJsonElement element) {
        if (element == null) {
            element = GenJsonNull.INSTANCE;
        }
        members.put(key, element);
    }

    public <T> void addPrimitive(String key, T value) {
        GenJsonPrimitive<T> primitive = value == null ? null : new GenJsonPrimitive<>(value);
        members.put(key, primitive);
    }

    public <T extends GenJsonElement> T get(String key) {
        return (T) this.members.get(key);
    }

    public Set<Entry<String, GenJsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public int size() {
        return this.members.size();
    }

    public boolean has(String memberName) {
        return this.members.containsKey(memberName);
    }

    public boolean equals(Object o) {
        return o == this || o instanceof GenJsonObject && ((GenJsonObject) o).members.equals(this.members);
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
