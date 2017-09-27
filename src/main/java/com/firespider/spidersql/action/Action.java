package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonNull;

import java.nio.channels.CompletionHandler;

public abstract class Action implements Runnable {
    protected Integer id;

    CompletionHandler<GenJsonElement, Integer> handler;

    Action(Integer id, CompletionHandler<GenJsonElement, Integer> handler) {
        this.id = id;
        this.handler = handler;
    }

    public void run() {
        handler.completed(GenJsonNull.INSTANCE, this.id);
    }
}
