package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.Param;
import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.lang.GenPrimitive;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

public abstract class Action implements Runnable {

    //ID 用于标识每一个动作，绑定结果数据
    protected Integer id;

    public final static String FINISH_FLAG = ":finish";

    Param param;

    //回调方法
    CompletionHandler<GenElement, Boolean> handler;

    Action(Integer id, Param param, CompletionHandler<GenElement, Boolean> handler) {
        this.id = id;
        this.param = param;
        this.handler = handler;
    }

    abstract void handle() throws IOException, InterruptedException;

    public void run() {
        try {
            handle();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("client close error");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("latch await error");
        } finally {
            finished();
        }
    }

    /***
     * 添加动作结束符
     * 用于判断是否到对尾
     */
    void finished() {
        GenElement finalElement = new GenPrimitive<>(id + FINISH_FLAG);
        this.handler.completed(finalElement, true);
    }
}
