package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaotong.shi on 2017/9/13.
 */
public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor<Gen> {

    private final Map<String, Gen> params;

    public SpiderSQLDefaultVisitor() {
        params = new HashMap<>();
    }

    @Override
    public Gen visitGet(SpiderSQLParser.GetContext ctx) {
        System.out.println(System.currentTimeMillis());
        for(int i=0;i<10;i++) {
            GenJsonObject element = (GenJsonObject) visitObj(ctx.obj());
        }
        System.out.println(System.currentTimeMillis());
        System.out.println("in get info");
        String json = ctx.obj().getText();
        System.out.println(System.currentTimeMillis());
        Gson gson = new Gson();
        for(int i=0;i<10;i++){
            gson.fromJson(json, JsonElement.class);
        }
        System.out.println(System.currentTimeMillis());
//        return super.visitGet(ctx);
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
            element = (GenJsonElement) params.get(ctx.C_VAR().getText());
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
            String name = mapContext.STRING().getText().replace("\"", "");
            GenJsonElement value = (GenJsonElement) visitValue(mapContext.value());
            element.add(name, value);
        }
        return element;
    }

    @Override
    public Gen visitArray(SpiderSQLParser.ArrayContext ctx) {
        GenJsonArray array = new GenJsonArray();
        for (SpiderSQLParser.ValueContext valueContext : ctx.value()) {
            array.add(visitValue(valueContext));
        }
        return array;
    }
}
