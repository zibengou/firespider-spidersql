package com.firespider.spidersql.aio.file;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * Created by stone on 2017/10/21.
 */
public interface IStorageManager {

    void save(Integer id, GenJsonElement data, String path, String type, CompletionHandler<GenJsonElement, Boolean> handler) throws IOException;

    void close();
}
