package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class SaveAction extends Action {
    public SaveAction(Integer id, CompletionHandler<GenJsonElement, Integer> handler) {
        super(id, handler);
    }
}
