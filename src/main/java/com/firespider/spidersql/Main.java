package com.firespider.spidersql;

import com.firespider.spidersql.parser.SpiderSQLLexer;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.parser.impl.SpiderSQLDefaultVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by xiaotong.shi on 2017/9/12.
 */
public class Main {
    public static void main(String[] args) {
        String sql="a:get{\"host\":1234}";
        CodePointCharStream cs = CharStreams.fromString(sql);
        SpiderSQLLexer lexer = new SpiderSQLLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpiderSQLParser parser = new SpiderSQLParser(tokenStream);
        ParseTree tree = parser.spidersql();
        SpiderSQLDefaultVisitor visitor = new SpiderSQLDefaultVisitor();
        visitor.visit(tree);
    }
}
