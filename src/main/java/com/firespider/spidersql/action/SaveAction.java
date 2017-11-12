package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.SaveParam;
import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.aio.file.DefaultStorageManager;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class SaveAction extends Action {

    SaveAction(Integer id, SaveParam param, CompletionHandler<GenElement, Boolean> handler) {
        super(id, param, handler);
    }

    @Override
    void handle() throws IOException, InterruptedException {
        GenElement data = ((SaveParam) this.param).getData();
        String type = ((SaveParam) this.param).getType();
        String path = ((SaveParam) this.param).getPath();
        DefaultStorageManager.INSTANCE.save(this.id, data, path, type, this.handler);
    }
}
