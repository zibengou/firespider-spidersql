package com.firespider.spidersql;

import com.firespider.spidersql.parser.SpiderSQLLexer;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.parser.impl.SpiderSQLDefaultVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // TODO: 2017/9/27 命令行输入 
        String sql="a:scan{host:\"172.20.33.190,www.baidu.com\",port:\"80,8080,443,8090,9100\"} | print a";
        System.out.println(System.currentTimeMillis());
        Vector vector =new Vector();
        CodePointCharStream cs = CharStreams.fromString(sql);
        SpiderSQLLexer lexer = new SpiderSQLLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpiderSQLParser parser = new SpiderSQLParser(tokenStream);
        ParseTree tree = parser.spidersql();
        SpiderSQLDefaultVisitor visitor = null;
        try {
            visitor = new SpiderSQLDefaultVisitor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        visitor.visit(tree);
    }
}
