package com.firespider.spidersql.aio.file;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.io.IOException;

/**
 * Created by stone on 2017/10/21.
 */
public interface IStorageManager {

    void save(Integer id, GenJsonElement data) throws IOException;
}
