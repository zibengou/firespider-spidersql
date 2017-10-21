package com.firespider.spidersql;

import com.firespider.spidersql.parser.SpiderSQLLexer;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.parser.impl.SpiderSQLDefaultVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printContent();
        handleInput();
    }

    private static void handleInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\rSpiderSQL: ");
            String in = sc.nextLine();
            if ("exit".equals(in)) {
                break;
            } else {
                executeSql(in);
            }
        }

    }

    private static void executeSql(String sql) {
        CodePointCharStream cs = CharStreams.fromString(sql);
        SpiderSQLLexer lexer = new SpiderSQLLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpiderSQLParser parser = new SpiderSQLParser(tokenStream);
        ParseTree tree = parser.spidersql();
        SpiderSQLDefaultVisitor visitor = new SpiderSQLDefaultVisitor();
        visitor.visit(tree);
    }

    private static void printContent() {
        System.out.println("******************* Welcome to SpiderSQL *******************");
        System.out.println("*******************                      *******************");
    }
}
