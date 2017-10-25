package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.SaveParam;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.aio.file.DefaultStorageManager;
import com.firespider.spidersql.aio.file.IStorageManager;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class SaveAction extends Action {

    private IStorageManager storageManager;

    SaveAction(Integer id, SaveParam param, CompletionHandler<GenJsonElement, Boolean> handler) {
        super(id, param, handler);
        String type = ((SaveParam) this.param).getType();
        String path = ((SaveParam) this.param).getPath();
        this.storageManager = new DefaultStorageManager(path, type, handler);
    }

    @Override
    void handle() throws IOException, InterruptedException {
        GenJsonElement data = ((SaveParam) this.param).getData();
        this.storageManager.save(this.id, data);
    }
}
