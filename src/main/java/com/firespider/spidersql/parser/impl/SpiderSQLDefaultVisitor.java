package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.aio.net.http.HttpAsyncClient;
import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor<Gen> {

    private final Map<String, Gen> params;

    private final ActionManager actionManager;


    public SpiderSQLDefaultVisitor() throws IOException {
        params = new HashMap<>();
        actionManager = new ActionManager(10);
    }

    // TODO: 2017/9/27 处理多线程与区块功能
    @Override
    public Gen visitExecuteSimple(SpiderSQLParser.ExecuteSimpleContext ctx) {
        Gen res = visitCombine_statement(ctx.combine_statement());
        actionManager.close();
        return res;
    }

    @Override
    public Gen visitCombine_statement(SpiderSQLParser.Combine_statementContext ctx) {
        ctx.simple_statement().forEach(sm -> visitSimple_statement(sm));
        actionManager.await(100);
        params.putAll(actionManager.getAll());
        actionManager.clear();
        return new Gen();
    }

    @Override
    public Gen visitAssignGet(SpiderSQLParser.AssignGetContext ctx) {
        String var = ctx.C_VAR().getText();
        Integer id = visitGet(ctx.get()).getId();
        actionManager.bind(var, id);
        return new Gen();
    }

    @Override
    public Gen visitGet(SpiderSQLParser.GetContext ctx) {
        Gen gen = new Gen();
        GenJsonObject element = (GenJsonObject) visitObj(ctx.obj());
        Integer id = actionManager.accept(element, "get");
        gen.setId(id);
        return gen;
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
            if (ctx.getText().equals("null")) {
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
