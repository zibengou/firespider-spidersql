package com.firespider.spidersql.lang;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GenArray extends GenElement {
    private final List<GenElement> elements;


    //确保线程安全
    public GenArray() {
        this.elements = new CopyOnWriteArrayList<>();
    }

    @Override
    public GenArray deepCopy() {
        GenArray result = new GenArray();
        for (GenElement element : elements) {
            result.add(element.deepCopy());
        }
        return result;
    }

    public <T> void add(T value) {
        if (value instanceof GenElement) {
            elements.add((GenElement) value);
        } else if (value == null) {
            elements.add(GenNull.INSTANCE);
        } else {
            GenPrimitive<T> element = new GenPrimitive<>(value);
            elements.add(element);
        }
    }

    public void addAll(GenArray array) {
        elements.addAll(array.elements);
    }

    public Iterator<GenElement> iterator() {
        return elements.iterator();
    }

    public GenElement get(int i) {
        return elements.get(i);
    }

    public GenElement remove(int index) {
        return elements.remove(index);
    }

    public boolean contains(GenElement element) {
        return elements.contains(element);
    }

    public int size() {
        return elements.size();
    }

    @Override
    public boolean equals(Object o) {
        return (o == this) || (o instanceof GenArray && ((GenArray) o).elements.equals(elements));
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");
        for (GenElement element : elements) {
            sb.append(element.toString()).append(",\n");
        }
        sb.deleteCharAt(sb.length() - 2).append("]");
        return sb.toString();
    }

    @Override
    public void setJsonVarElement(GenElement element) {
        this.iterator().forEachRemaining(e -> e.setJsonVarElement(element));
    }
}
