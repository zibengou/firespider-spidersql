package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.action.ActionManager;
import com.firespider.spidersql.lang.Gen;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by stone on 2017/10/21.
 */
public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor<Gen> {

    private final Map<String, GenJsonElement> params;

    private final ActionManager actionManager;

    public SpiderSQLDefaultVisitor() {
        this(10);
    }

    public SpiderSQLDefaultVisitor(int threadNum) {
        this.params = new ConcurrentHashMap<>();
        this.actionManager = new ActionManager(threadNum);
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
        ctx.simple_statement().forEach(this::visitSimple_statement);
        actionManager.await(100);
        params.putAll(actionManager.getAll());
        actionManager.clear();
        return new Gen();
    }

    @Override
    public Gen visitAssignGet(SpiderSQLParser.AssignGetContext ctx) {
        String var = ctx.C_VAR().getText();
        Gen res = visitGet(ctx.get());
        actionManager.bind(var, res.getId());
        return res;
    }

    @Override
    public Gen visitAssignScan(SpiderSQLParser.AssignScanContext ctx) {
        String var = ctx.C_VAR().getText();
        Gen res = visitScan(ctx.scan());
        actionManager.bind(var, res.getId());
        return res;
    }

    @Override
    public Gen visitScan(SpiderSQLParser.ScanContext ctx) {
        GenJsonElement element = visitObj(ctx.obj());
        return new Gen(actionManager.accept(element, ActionManager.TYPE.SCAN));
    }

    @Override
    public Gen visitPrint(SpiderSQLParser.PrintContext ctx) {
        final Integer[] res = {0};
        if (ctx.obj() != null) {
            res[0] = actionManager.accept(visitObj(ctx.obj()), ActionManager.TYPE.PRINT);
        } else if (ctx.C_VAR() != null) {
            String var = ctx.C_VAR().getText();
            if (params.containsKey(var)) {
                res[0] = actionManager.accept(params.get(var), ActionManager.TYPE.PRINT);
            }
        } else {
            ctx.c_mul_var().C_VAR().forEach(var -> {
                String v = var.getText();
                if (params.containsKey(v)) {
                    res[0] = actionManager.accept(params.get(v), ActionManager.TYPE.PRINT);
                }
            });
        }
        return new Gen(res[0]);
    }


    @Override
    public Gen visitGet(SpiderSQLParser.GetContext ctx) {
        GenJsonElement element = visitObj(ctx.obj());
        return new Gen(actionManager.accept(element, ActionManager.TYPE.GET));
    }

    @Override
    public Gen visitSave(SpiderSQLParser.SaveContext ctx) {
        GenJsonElement element = visitObj(ctx.obj());
        return new Gen(actionManager.accept(element, ActionManager.TYPE.SAVE));
    }

    @Override
    public GenJsonElement visitValue(SpiderSQLParser.ValueContext ctx) {
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
            element = params.get(props[0]);
            for (int i = 1; i < props.length; i++) {
                element = ((GenJsonObject) element).get(props[i]);
            }
        } else if (ctx.obj() != null) {
            element = visitObj(ctx.obj());
        } else if (ctx.array() != null) {
            element = visitArray(ctx.array());
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
    public GenJsonElement visitObj(SpiderSQLParser.ObjContext ctx) {
        GenJsonObject element = new GenJsonObject();
        for (SpiderSQLParser.MapContext mapContext : ctx.map()) {
            String name;
            if (mapContext.C_VAR() != null) {
                name = mapContext.C_VAR().getText();
            } else {
                name = mapContext.STRING().getText().replace("\"", "");
            }
            GenJsonElement value = visitValue(mapContext.value());
            element.add(name, value);
        }
        return element;
    }

    @Override
    public GenJsonElement visitArray(SpiderSQLParser.ArrayContext ctx) {
        GenJsonArray array = new GenJsonArray();
        ctx.value().forEach(value -> array.add(visitValue(value)));
        return array;
    }

}
