package com.firespider.spidersql.aio.file;

import com.firespider.spidersql.lang.GenElement;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

/**
 * Created by stone on 2017/10/21.
 */
public interface IStorageManager {

    void save(Integer id, GenElement data, String path, String type, CompletionHandler<GenElement, Boolean> handler) throws IOException;

    void close();
}
