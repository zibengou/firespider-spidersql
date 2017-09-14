package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;

import java.util.HashMap;
import java.util.Map;

public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor<Gen> {

    private final Map<String, Gen> params;

    public SpiderSQLDefaultVisitor() {
        params = new HashMap<>();
    }

    @Override
    public Gen visitGet(SpiderSQLParser.GetContext ctx) {
        System.out.println(System.currentTimeMillis());
        GenJsonObject element = (GenJsonObject) visitObj(ctx.obj());
        System.out.println(System.currentTimeMillis());
        return null;
    }

    @Override
    public Gen visitValue(SpiderSQLParser.ValueContext ctx) {
        GenJsonElement element;
        if (ctx.STRING() != null) {
            element = new GenJsonPrimitive<>(ctx.STRING().getText().replace("\"", ""));
        } else if (ctx.INT() != null) {
            element = new GenJsonPrimitive<>(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.DOUBLE() != null) {
            element = new GenJsonPrimitive<>(Double.parseDouble(ctx.DOUBLE().getText()));
        } else if (ctx.C_VAR() != null) {
            // 针对属性值，遍历获取
            String[] props = ctx.C_VAR().getText().split("\\.");
            if (!params.containsKey(props[0]))
                return GenJsonNull.INSTANCE;
            element = (GenJsonElement) params.get(props[0]);
            for (int i = 1; i < props.length; i++) {
                element = ((GenJsonObject) element).get(props[i]);
            }
        } else if (ctx.obj() != null) {
            element = (GenJsonElement) visitObj(ctx.obj());
        } else if (ctx.array() != null) {
            element = (GenJsonArray) visitArray(ctx.array());
        } else {
            if (ctx.getText() == "null") {
                element = GenJsonNull.INSTANCE;
            } else {
                element = new GenJsonPrimitive<>(Boolean.parseBoolean(ctx.getText()));
            }
        }
        return element;
    }

    @Override
    public Gen visitObj(SpiderSQLParser.ObjContext ctx) {
        GenJsonObject element = new GenJsonObject();
        for (SpiderSQLParser.MapContext mapContext : ctx.map()) {
            String name;
            if (mapContext.C_VAR() != null) {
                name = mapContext.C_VAR().getText();
            } else {
                name = mapContext.STRING().getText().replace("\"", "");
            }
            GenJsonElement value = (GenJsonElement) visitValue(mapContext.value());
            element.add(name, value);
        }
        return element;
    }

    @Override
    public Gen visitArray(SpiderSQLParser.ArrayContext ctx) {
        GenJsonArray array = new GenJsonArray();
        ctx.value().forEach(value -> array.add(visitValue(value)));
        return array;
    }
}
