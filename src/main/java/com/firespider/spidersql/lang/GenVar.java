package com.firespider.spidersql.lang;

import com.firespider.spidersql.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by xiaotong.shi on 2017/11/7.
 */
public class GenVar extends GenElement {

    private GenElement element;

    private String props;

    public GenVar() {
        this.props = "";
        this.element = GenNull.INSTANCE;
    }

    public GenVar(GenElement element, String props) {
        this.element = element;
        this.props = props;
    }

    @Override
    public GenVar deepCopy() {
        GenVar res = new GenVar();
        GenElement element = this.element.deepCopy();
        res.setElement(element);
        res.setProps(this.props);
        return res;
    }

    @Override
    public GenArray getAsArray() {
        GenElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenArray) {
            return (GenArray) ele;
        } else {
            return super.getAsArray();
        }
    }

    @Override
    public GenObject getAsObject() {
        GenElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenObject) {
            return (GenObject) ele;
        } else {
            return super.getAsObject();
        }
    }

    @Override
    public GenPrimitive getAsPrimitive() {
        GenElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenPrimitive) {
            return (GenPrimitive) ele;
        } else {
            return super.getAsPrimitive();
        }
    }

    @Override
    public String getAsString() {
        return parseVarElement(this.element, this.props).getAsString();
    }

    public GenElement getAsElement() {
        return parseVarElement(this.element, this.props);
    }

    @Override
    public String toString() {
        return parseVarElement(this.element, this.props).toString();
    }

    public GenElement getElement() {
        return element;
    }

    public void setElement(GenElement element) {
        this.element = element;
    }

    private GenElement parseVarElement(GenElement element, String props) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!Utils.isEmpty(props)) {
            arrayList.addAll(Arrays.asList(props.split("\\.")));
        }
        return parseVarElement(element, arrayList);
    }

    private GenElement parseVarElement(GenElement element, ArrayList<String> varList) {
        if (varList == null || varList.size() == 0) {
            return element;
        }
        GenElement res;
        if (element == null) {
            return GenNull.INSTANCE;
        } else if (element instanceof GenArray) {
            res = new GenArray();
            Iterator<GenElement> iterator = ((GenArray) element).iterator();
            while (iterator.hasNext()) {
                ((GenArray) res).add(parseVarElement(iterator.next(), new ArrayList<>(varList)));
            }
        } else if (element instanceof GenObject) {
            res = ((GenObject) element).get(varList.get(0));
            varList.remove(0);
            res = parseVarElement(res, new ArrayList<>(varList));
        } else {
            return element;
        }
        return res;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }


    @Override
    public int hashCode() {
        return this.props.hashCode() + this.element.hashCode();
    }

    @Override
    public void setJsonVarElement(GenElement element) {
        this.setElement(element);
    }
}
