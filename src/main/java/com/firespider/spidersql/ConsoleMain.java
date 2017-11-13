package com.firespider.spidersql;

import com.firespider.spidersql.utils.Utils;
import jline.console.ConsoleReader;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) throws IOException {
        // TODO: 2017/11/10 readme文档整理
        // TODO: 2017/11/12 save支持无赋值动作
        if (args != null && args.length > 0) {
            handleFile(args[0]);
        } else {
            printContent();
            handleInput();
        }
    }

    private static void handleFile(String path) throws IOException {
        List<String> sqlList = Files.readAllLines(Paths.get(path));
        for (String sql : sqlList) {
            executeSql(sql);
        }
    }

    private static void handleInput() throws IOException {
//        ConsoleReader reader = new ConsoleReader();
//        ConsoleCompleter consoleCompleter = new ConsoleCompleter();
//        reader.addCompleter(consoleCompleter);
        //WINDOWS IDEA debug
        Scanner reader = new Scanner(System.in);
        while (true) {
//            String in = reader.readLine("SpiderSQL> ");
            System.out.print("SpiderSQL> ");
            String in = reader.nextLine();
            if ("exit".equals(in)) {
                break;
            } else {
                long exeTime = System.currentTimeMillis();
                executeSql(in);
                Float time = (float) (System.currentTimeMillis() - exeTime) / 1000;
                System.out.println("Total Time : " + time + " sec");
            }
        }
//        reader.close();
    }

    private static void executeSql(String sql) {
        if (Utils.isEmpty(sql)) {
            return;
        }
        SqlManager.INSTANCE.execute(sql);
    }

    private static void printContent() {
        System.out.println("******************* Welcome to SpiderSQL *******************");
        System.out.println("*******************                      *******************");
    }

    private static void printFlag() throws InterruptedException {
        System.out.print("SpiderSQL> ");
    }
}
