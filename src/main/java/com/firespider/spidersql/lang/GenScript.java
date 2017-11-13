package com.firespider.spidersql.lang;

import com.firespider.spidersql.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiaotong.shi on 2017/11/7.
 */
public class GenScript extends GenElement {

    private GenElement first;

    private ArrayList<List<GenElement>> symbolList;

    @Override
    public GenElement deepCopy() {
        GenScript newScript = new GenScript();
        ArrayList<List<GenElement>> newList = new ArrayList<>();
        newScript.setFirst(this.first.deepCopy());
        for (List<GenElement> elements : this.symbolList) {
            List<GenElement> elementList = new ArrayList<>();
            elementList.add(elements.get(0).deepCopy());
            elementList.add(elements.get(1).deepCopy());
            newList.add(elementList);
        }
        newScript.setSymbolList(newList);
        return newScript;
    }

    @Override
    public GenElement getAsElement() {
        return executeScript();
    }

    @Override
    public String getAsString() {
        return executeScript().getAsString();
    }

    @Override
    public String toString() {
        return executeScript().toString();
    }

    private GenElement executeScript() {
        GenPrimitive first = this.first.getAsPrimitive();
        if (first.isString()) {
            String fe = first.getAsString();
            for (List<GenElement> elements : this.symbolList) {
                String symbol = elements.get(0).getAsString();
                try {
                    fe = executeStringSymbol(symbol, fe, elements.get(1).getAsString());
                } catch (Exception e) {
                    System.err.println("execute string script:" + e.getMessage());
                }
            }
            return new GenPrimitive<>(fe);
        } else if (first.isDobule()) {
            Double fe = first.getAsDouble();
            for (List<GenElement> elements : this.symbolList) {
                String symbol = elements.get(0).getAsString();
                try {
                    fe = executeDoubleSymbol(symbol, fe, elements.get(1).getAsDouble());
                } catch (Exception e) {
                    System.err.println("execute double script:" + e.getMessage());
                }
            }
            return new GenPrimitive<>(fe);
        } else if (first.isInt()) {
            Integer fe = first.getAsInteger();
            for (List<GenElement> elements : this.symbolList) {
                String symbol = elements.get(0).getAsString();
                try {
                    fe = executeIntSymbol(symbol, fe, elements.get(1).getAsInteger());
                } catch (Exception e) {
                    System.err.println("execute int script:" + e.getMessage());
                }
            }
            return new GenPrimitive<>(fe);
        }
        return GenNull.INSTANCE;
    }

    private Double executeDoubleSymbol(String symbol, Double first, Double second) {
        Double res;
        switch (symbol) {
            case "+":
                res = first + second;
                break;
            case "-":
                res = first - second;
                break;
            case "*":
                res = first * second;
                break;
            case "/":
                res = first / second;
                break;
            default:
                res = first;
        }
        return res;
    }

    private Integer executeIntSymbol(String symbol, Integer first, Integer second) {
        Integer res;
        switch (symbol) {
            case "+":
                res = first + second;
                break;
            case "-":
                res = first - second;
                break;
            case "*":
                res = first * second;
                break;
            case "/":
                res = first / second;
                break;
            default:
                res = first;
        }
        return res;
    }

    private String executeStringSymbol(String symbol, String first, String second) {
        switch (symbol) {
            case "+":
                return first + second;
            default:
                return first;
        }
    }

    public GenElement getFirst() {
        return first;
    }

    public void setFirst(GenElement first) {
        this.first = first;
    }

    public ArrayList<List<GenElement>> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(ArrayList<List<GenElement>> symbolList) {
        this.symbolList = symbolList;
    }

    @Override
    public void setJsonVarElement(GenElement element) {
        this.first.setJsonVarElement(element);
        this.symbolList.forEach(symbols -> symbols.get(1).setJsonVarElement(element));
    }
}
