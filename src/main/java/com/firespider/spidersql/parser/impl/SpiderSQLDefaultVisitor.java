package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.action.Action;
import com.firespider.spidersql.action.ActionManager;
import com.firespider.spidersql.lang.Gen;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;

import java.nio.channels.CompletionHandler;
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
//        actionManager.await(100);
        params.putAll(actionManager.getAll());
        actionManager.clear();
        return new Gen();
    }

    @Override
    public Gen visitDefaultPush(SpiderSQLParser.DefaultPushContext ctx) {
        Gen source = visitVar(ctx.var(0));
//        SpiderSQLParser.VarContext varContext0 = ctx.var(0);
//        Gen source = visitVar(varContext0);
        String sourceVar = actionManager.getVar(source.getId());
        if (ctx.mul_var() == null) {
            SpiderSQLParser.VarContext varContext1 = ctx.var(1);
            if (varContext1.C_VAR() != null) {
                String var = varContext1.C_VAR().getText();
                Integer id = var.hashCode();
                actionManager.bind(var, id);
                actionManager.regist(id, source.getId(), new CompletionHandler<GenJsonElement, Integer>() {
                    @Override
                    public void completed(GenJsonElement result, Integer attachment) {
                        Action action = actionManager.accept(result, ActionManager.TYPE.VALUE, id);
                        if (action != null) {
                            action.run();
                        }
                    }
                    @Override
                    public void failed(Throwable exc, Integer attachment) {

                    }
                });
            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignValueContext) {

            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignGetContext) {

            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignSaveContext) {

            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignScanContext) {

            } else if (varContext1.assign_statement() instanceof SpiderSQLParser.AssignDescContext) {

            }
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
            ArrayList<String> varList = new ArrayList<>(Arrays.asList(var.split("\\.")));
            res[0] = actionManager.accept(parseVarElement(null, varList), ActionManager.TYPE.PRINT);
        } else {
            ctx.c_mul_var().C_VAR().forEach(var -> {
                ArrayList<String> varList = new ArrayList<>(Arrays.asList(var.getText().split("\\.")));
                res[0] = actionManager.accept(parseVarElement(null, varList), ActionManager.TYPE.PRINT);
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
            // TODO: 2017/10/26 object与array取值模式 
            if (!params.containsKey(props[0]))
                return GenJsonNull.INSTANCE;
            element = params.get(props[0]);
            element = parseVarElement(element, new ArrayList<>(Arrays.asList(props)));
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

    private GenJsonElement parseVarElement(GenJsonElement element, ArrayList<String> varList) {
        if (varList.size() == 0) {
            return element;
        }
        GenJsonElement res = null;
        if (element == null) {
            String root = varList.get(0);
            varList.remove(0);
            res = parseVarElement(this.params.get(root), new ArrayList<>(varList));
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
