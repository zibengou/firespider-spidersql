package com.firespider.spidersql;

import com.firespider.spidersql.parser.SpiderSQLLexer;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.parser.impl.SpiderSQLDefaultVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 上层入口
 * todo 集成服务
 */
public class SqlManager {

    public static final SqlManager INSTANCE = new SqlManager();

    private final SpiderSQLDefaultVisitor visitor;

    private SqlManager() {
        this.visitor = new SpiderSQLDefaultVisitor();
    }

    void execute(String sql) {
        CodePointCharStream cs = CharStreams.fromString(sql);
        SpiderSQLLexer lexer = new SpiderSQLLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpiderSQLParser parser = new SpiderSQLParser(tokenStream);
        ParseTree tree = parser.spidersql();
        this.visitor.visit(tree);
    }
}
