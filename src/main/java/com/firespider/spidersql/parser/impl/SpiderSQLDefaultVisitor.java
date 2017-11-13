package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.action.ActionManager;
import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.utils.Utils;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 语法解析模块
 */
public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor<Gen> {

    private final Map<String, GenElement> params;

    private final ActionManager actionManager;

    public SpiderSQLDefaultVisitor() {
        this(10);
    }

    public SpiderSQLDefaultVisitor(int threadNum) {
        this.params = new ConcurrentHashMap<>();
        this.actionManager = new ActionManager(threadNum);
    }

    @Override
    public Gen visitExecuteSimple(SpiderSQLParser.ExecuteSimpleContext ctx) {
        Gen res = visitCombine_statement(ctx.combine_statement());
        // ActionManager单例
//        actionManager.close();
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
            visitDefaultPushSingle(ctx.var(1), source.getId());
        } else {
            SpiderSQLParser.Mul_varContext mulVarContext = ctx.mul_var();
            mulVarContext.var().forEach(vatContext -> visitDefaultPushSingle(vatContext, source.getId()));
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
        GenElement element = visitValue(ctx.value());
        Integer id = actionManager.accept(element, ActionManager.TYPE.VALUE);
        actionManager.bind(ctx.C_VAR().getText(), id);
        return new Gen(id);
    }

    @Override
    public Gen visitAssignSave(SpiderSQLParser.AssignSaveContext ctx) {
        String var = ctx.C_VAR().getText();
        Gen res = visitSave(ctx.save());
        actionManager.bind(var, res.getId());
        return res;
    }

    @Override
    public Gen visitScan(SpiderSQLParser.ScanContext ctx) {
        GenElement element = visitObj(ctx.obj());
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
        GenElement element = visitObj(ctx.obj());
        return new Gen(actionManager.accept(element, ActionManager.TYPE.GET));
    }

    @Override
    public Gen visitSave(SpiderSQLParser.SaveContext ctx) {
        GenElement element = visitObj(ctx.obj());
        return new Gen(actionManager.accept(element, ActionManager.TYPE.SAVE));
    }

    @Override
    public GenElement visitValue(SpiderSQLParser.ValueContext ctx) {
        GenElement element;
        if (ctx.STRING() != null) {
            element = new GenPrimitive<>(ctx.STRING().getText().replace("\"", ""));
        } else if (ctx.INT() != null) {
            element = new GenPrimitive<>(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.DOUBLE() != null) {
            element = new GenPrimitive<>(Double.parseDouble(ctx.DOUBLE().getText()));
        } else if (ctx.C_VAR() != null) {
            element = parseVarElement(ctx.C_VAR().getText());
        } else if (ctx.obj() != null) {
            element = visitObj(ctx.obj());
        } else if (ctx.array() != null) {
            element = visitArray(ctx.array());
        } else if (ctx.SYMBOL() != null) {
            GenScript genScript = new GenScript();
            GenElement firstEle = visitValue(ctx.value(0));
            ArrayList<List<GenElement>> symbols = new ArrayList<>();
            int pos = 1;
            for (TerminalNode symbolNode : ctx.SYMBOL()) {
                List<GenElement> elementList = new ArrayList<>();
                GenPrimitive symbol = new GenPrimitive(symbolNode.getText());
                GenElement ele = visitValue(ctx.value(pos++));
                elementList.add(symbol);
                elementList.add(ele);
                symbols.add(elementList);
            }
            genScript.setFirst(firstEle);
            genScript.setSymbolList(symbols);
            return genScript;
        } else {
            if (ctx.getText().equals("null")) {
                element = GenNull.INSTANCE;
            } else {
                element = new GenPrimitive<>(Boolean.parseBoolean(ctx.getText()));
            }
        }
        return element;
    }

    @Override
    public GenElement visitObj(SpiderSQLParser.ObjContext ctx) {
        GenObject element = new GenObject();
        for (SpiderSQLParser.MapContext mapContext : ctx.map()) {
            String name;
            if (mapContext.C_VAR() != null) {
                name = mapContext.C_VAR().getText();
            } else {
                name = mapContext.STRING().getText().replace("\"", "");
            }
            GenElement value = visitValue(mapContext.value());
            element.add(name, value);
        }
        return element;
    }

    @Override
    public GenElement visitArray(SpiderSQLParser.ArrayContext ctx) {
        GenArray array = new GenArray();
        ctx.value().forEach(value -> array.add(visitValue(value)));
        return array;
    }

    private GenElement parseVarElement(String props) {
        ArrayList<String> propList = new ArrayList<>(Arrays.asList(props.split("\\.")));
        GenVar element = new GenVar();
        // TODO: 2017/10/26 object与array取值模式
        if (params.containsKey(propList.get(0))) {
            GenElement ele = params.get(propList.get(0));
            element.setElement(ele);
        }
        // 删除根节点变量
        propList.remove(0);
        element.setProps(Utils.join(propList, "\\."));
        return element;
    }

    private void visitDefaultPushSingle(SpiderSQLParser.VarContext varContext1, Integer sourceId) {
        GenElement varElement;
        String var;
        ActionManager.TYPE type;

        if (varContext1.C_VAR() != null) {
            var = varContext1.C_VAR().getText();
            varElement = new GenVar();
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
        actionManager.acceptHandler(varElement, type, sourceId);
    }

}
