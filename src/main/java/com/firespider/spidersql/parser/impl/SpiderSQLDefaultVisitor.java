package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.action.ActionManager;
import com.firespider.spidersql.lang.Gen;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.sun.deploy.util.StringUtils;

import java.util.*;
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
        actionManager.execute(100);
        params.putAll(actionManager.getAll());
        actionManager.clear();
        return new Gen();
    }

    @Override
    public Gen visitDefaultPush(SpiderSQLParser.DefaultPushContext ctx) {
        Gen source = visitVar(ctx.var(0));
        if (ctx.mul_var() == null) {
            GenJsonElement varElement;
            String var;
            ActionManager.TYPE type;
            SpiderSQLParser.VarContext varContext1 = ctx.var(1);

            if (varContext1.C_VAR() != null) {
                var = varContext1.C_VAR().getText();
                varElement = new GenJsonVar();
                type = ActionManager.TYPE.VALUE;
            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignGetContext) {
                SpiderSQLParser.AssignGetContext getContext = (SpiderSQLParser.AssignGetContext) varContext1.assign_statement();
                SpiderSQLParser.ObjContext objContext = getContext.get().obj();
                var = getContext.C_VAR().getText();
                varElement = visitObj(objContext);
                type = ActionManager.TYPE.GET;

            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignSaveContext) {
                SpiderSQLParser.AssignSaveContext saveContext = (SpiderSQLParser.AssignSaveContext) varContext1.assign_statement();
                SpiderSQLParser.ObjContext objContext = saveContext.save().obj();
                var = saveContext.C_VAR().getText();
                varElement = visitObj(objContext);
                type = ActionManager.TYPE.SAVE;
            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignScanContext) {
                SpiderSQLParser.AssignScanContext scanContext = ((SpiderSQLParser.AssignScanContext) varContext1.assign_statement());
                SpiderSQLParser.ObjContext objContext = scanContext.scan().obj();
                var = scanContext.C_VAR().getText();
                varElement = visitObj(objContext);
                type = ActionManager.TYPE.SCAN;
            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignDescContext) {
                SpiderSQLParser.AssignDescContext descContext = (SpiderSQLParser.AssignDescContext) varContext1.assign_statement();
                SpiderSQLParser.ObjContext objContext = descContext.desc().obj();
                var = descContext.C_VAR().getText();
                varElement = visitObj(objContext);
                type = ActionManager.TYPE.DESC;
            } else {    // AssignValueContext
                SpiderSQLParser.AssignValueContext valueContext = (SpiderSQLParser.AssignValueContext) varContext1.assign_statement();
                SpiderSQLParser.ObjContext objContext = valueContext.value().obj();
                var = valueContext.C_VAR().getText();
                varElement = visitObj(objContext);
                type = ActionManager.TYPE.VALUE;
            }
            actionManager.bind(var, varElement.hashCode());
            actionManager.acceptHandler(varElement, type, source.getId());
        } else {
            SpiderSQLParser.Mul_varContext mulVarContext = ctx.mul_var();
            mulVarContext.var().forEach(this::visitVar);
        }
        return source;
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
    public Gen visitAssignValue(SpiderSQLParser.AssignValueContext ctx) {
        GenJsonElement element = visitValue(ctx.value());
        Integer id = actionManager.accept(element, ActionManager.TYPE.VALUE);
        actionManager.bind(ctx.C_VAR().getText(), id);
        return new Gen(id);
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
            res[0] = actionManager.accept(parseVarElement(var), ActionManager.TYPE.PRINT);
        } else {
            ctx.c_mul_var().C_VAR().forEach(var -> {
                res[0] = actionManager.accept(parseVarElement(var.getText()), ActionManager.TYPE.PRINT);
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
            element = parseVarElement(ctx.C_VAR().getText());
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

    private GenJsonElement parseVarElement(String props) {
        ArrayList<String> propList = new ArrayList<>(Arrays.asList(props.split("\\.")));
        GenJsonVar element = new GenJsonVar();
        // TODO: 2017/10/26 object与array取值模式
        if (params.containsKey(propList.get(0))) {
            GenJsonElement ele = params.get(propList.get(0));
            element.setElement(ele);
        }
        // 删除根节点变量
        propList.remove(0);
        element.setProps(StringUtils.join(propList, "\\."));
        return element;
    }

}
