package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.parser.SpiderSQLBaseVisitor;
import com.firespider.spidersql.parser.SpiderSQLParser;

/**
 * Created by xiaotong.shi on 2017/9/13.
 */
public class SpiderSQLDefaultVisitor extends SpiderSQLBaseVisitor {

    public SpiderSQLDefaultVisitor() {

    }

    @Override
    public Object visitGet(SpiderSQLParser.GetContext ctx) {
        for (SpiderSQLParser.MapContext mapContext : ctx.obj().map()) {
            String name = mapContext.STRING().getText();
            Object value = visitChildren(mapContext.value());
            System.out.println(name + "  " + value);
        }
        System.out.println("in get info");
//        return super.visitGet(ctx);
        return "";
    }

    @Override
    public Object visitValue(SpiderSQLParser.ValueContext ctx) {
        return super.visitValue(ctx);
    }
}
