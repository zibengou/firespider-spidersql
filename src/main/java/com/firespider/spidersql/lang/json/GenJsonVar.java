package com.firespider.spidersql.lang.json;

import com.firespider.spidersql.utils.Utils;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiaotong.shi on 2017/11/7.
 */
public class GenJsonVar extends GenJsonElement {

    private GenJsonElement element;

    private String props;

    public GenJsonVar() {
        this.props = "";
        this.element = GenJsonNull.INSTANCE;
    }

    public GenJsonVar(GenJsonElement element, String props) {
        this.element = element;
        this.props = props;
    }

    @Override
    GenJsonVar deepCopy() {
        GenJsonVar res = new GenJsonVar();
        GenJsonElement element = this.element.deepCopy();
        res.setElement(element);
        res.setProps(this.props);
        return res;
    }

    @Override
    public GenJsonArray getAsArray() {
        GenJsonElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenJsonArray) {
            return (GenJsonArray) ele;
        } else {
            return super.getAsArray();
        }
    }

    @Override
    public GenJsonObject getAsObject() {
        GenJsonElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenJsonObject) {
            return (GenJsonObject) ele;
        } else {
            return super.getAsObject();
        }
    }

    @Override
    public GenJsonPrimitive getAsPrimitive() {
        GenJsonElement ele = parseVarElement(this.element, this.props);
        if (ele instanceof GenJsonPrimitive) {
            return (GenJsonPrimitive) ele;
        } else {
            return super.getAsPrimitive();
        }
    }

    public GenJsonElement getAsElement() {
        return parseVarElement(this.element, this.props);
    }

    @Override
    public String toString() {
        return parseVarElement(this.element, this.props).toString();
    }

    public GenJsonElement getElement() {
        return element;
    }

    public void setElement(GenJsonElement element) {
        this.element = element;
    }

    private GenJsonElement parseVarElement(GenJsonElement element, String props) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!Utils.isEmpty(props)) {
            arrayList.addAll(Arrays.asList(props.split("\\.")));
        }
        return parseVarElement(element, arrayList);
    }

    private GenJsonElement parseVarElement(GenJsonElement element, ArrayList<String> varList) {
        if (varList == null || varList.size() == 0) {
            return element;
        }
        GenJsonElement res;
        if (element == null) {
            return GenJsonNull.INSTANCE;
        } else if (element instanceof GenJsonArray) {
            res = new GenJsonArray();
            Iterator<GenJsonElement> iterator = ((GenJsonArray) element).iterator();
            while (iterator.hasNext()) {
                ((GenJsonArray) res).add(parseVarElement(iterator.next(), new ArrayList<>(varList)));
            }
        } else if (element instanceof GenJsonObject) {
            res = ((GenJsonObject) element).get(varList.get(0));
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
}
