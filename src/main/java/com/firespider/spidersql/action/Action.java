package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.Param;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonNull;
import com.firespider.spidersql.lang.json.GenJsonPrimitive;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

public abstract class Action implements Runnable {

    //ID 用于标识每一个动作，绑定结果数据
    protected Integer id;

    private final static String FINISH_FLAG = ":finish";

    Param param;

    //回调方法
    CompletionHandler<GenJsonElement, Boolean> handler;

    Action(Integer id, Param param, CompletionHandler<GenJsonElement, Boolean> handler) {
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

    void finished() {
        GenJsonElement finalElement = new GenJsonPrimitive<String>(id + "finish");
        this.handler.completed(finalElement, true);
    }
}
