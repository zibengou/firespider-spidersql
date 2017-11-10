package com.firespider.spidersql;

import com.firespider.spidersql.parser.SpiderSQLLexer;
import com.firespider.spidersql.parser.SpiderSQLParser;
import com.firespider.spidersql.parser.impl.SpiderSQLDefaultVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) throws InterruptedException {
        // TODO: 2017/11/10 Sql语法格式化
        // TODO: 2017/11/10 默认参数设置
        // TODO: 2017/11/10 readme文档整理
        printContent();
        handleInput();
    }

    private static void handleInput() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Long exeTime;
        while (true) {
            printFlag();
            String in = sc.nextLine();
            if ("exit".equals(in)) {
                break;
            } else {
                exeTime = System.currentTimeMillis();
                executeSql(in);
                Float time = Float.valueOf(System.currentTimeMillis() - exeTime) / 1000;
                System.out.println("Total Time : " + time + " sec");
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

    private static void printFlag() throws InterruptedException {
        Thread.sleep(250);
        System.out.print("SpiderSQL> ");
    }
}
