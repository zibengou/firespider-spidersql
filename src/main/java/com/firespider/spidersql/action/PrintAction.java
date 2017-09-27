package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class PrintAction extends Action {
    public PrintAction(Integer id, CompletionHandler<GenJsonElement, Integer> handler) {
        super(id, handler);
    }

    @Override
    public void run() {
        super.run();
    }
}
