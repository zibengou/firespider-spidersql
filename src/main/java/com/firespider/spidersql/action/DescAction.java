package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.Param;
import com.firespider.spidersql.lang.GenElement;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class DescAction extends Action {
    DescAction(Integer id, Param param, CompletionHandler<GenElement, Boolean> handler) {
        super(id, param, handler);
    }

    @Override
    void handle() throws IOException, InterruptedException {

    }

}
