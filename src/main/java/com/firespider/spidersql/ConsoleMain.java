package com.firespider.spidersql;

import com.firespider.spidersql.utils.Utils;

import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) throws InterruptedException {
        // TODO: 2017/11/10 Sql语法格式化
        // TODO: 2017/11/10 readme文档整理
        printContent();
        handleInput();
    }

    private static void handleInput() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        printFlag();
        while (true) {
            String in = sc.nextLine();
            if ("exit".equals(in)) {
                break;
            } else {
                executeSql(in);
                printFlag();
            }
        }

    }

    private static void executeSql(String sql) {
        if (Utils.isEmpty(sql)) {
            return;
        }
        long exeTime = System.currentTimeMillis();
        SqlManager.INSTANCE.execute(sql);
        Float time = (float) (System.currentTimeMillis() - exeTime) / 1000;
        System.out.println("Total Time : " + time + " sec");
    }

    private static void printContent() {
        System.out.println("******************* Welcome to SpiderSQL *******************");
        System.out.println("*******************                      *******************");
    }

    private static void printFlag() throws InterruptedException {
        System.out.print("SpiderSQL> ");
    }
}
