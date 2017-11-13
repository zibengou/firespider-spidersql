package com.firespider.spidersql.lang;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by stone on 2017/9/13.
 */
public class GenObject extends GenElement {
    private final LinkedHashMap<String, GenElement> members = new LinkedHashMap<>();

    public GenObject deepCopy() {
        GenObject result = new GenObject();
        for (Entry<String, GenElement> o : this.members.entrySet()) {
            result.add((String) ((Entry) o).getKey(), ((GenElement) ((Entry) o).getValue()).deepCopy());
        }
        return result;
    }

    public void add(String key, GenElement element) {
        if (element == null) {
            element = GenNull.INSTANCE;
        }
        members.put(key, element);
    }

    public <T> void addPrimitive(String key, T value) {
        GenPrimitive<T> primitive = value == null ? null : new GenPrimitive<>(value);
        members.put(key, primitive);
    }

    public <T extends GenElement> T get(String key) {
        return this.members.containsKey(key) ? (T) this.members.get(key) : (T) GenNull.INSTANCE;
    }

    public Set<Entry<String, GenElement>> entrySet() {
        return this.members.entrySet();
    }

    public int size() {
        return this.members.size();
    }

    public boolean has(String memberName) {
        return this.members.containsKey(memberName);
    }

    public boolean equals(Object o) {
        return o == this || o instanceof GenObject && ((GenObject) o).members.equals(this.members);
    }

    public int hashCode() {
        return this.members.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, GenElement> map : members.entrySet()) {
            sb.append("\"").append(map.getKey()).append("\":").append(map.getValue().toString()).append(",");
        }
        if (sb.lastIndexOf(",") > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void setJsonVarElement(GenElement element) {
        this.entrySet().forEach(v -> v.getValue().setJsonVarElement(element));
    }
}
