package com.firespider.spidersql.queue;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/11/7.
 */
public interface QueueManager {

    void regist(Integer id, Integer sourceId, CompletionHandler<GenJsonElement, Integer> handler);

    boolean publish(Integer id, GenJsonElement data);

    boolean consume(Integer id);

    GenJsonElement getAll(Integer id);

    boolean exists(Integer id);

    void clear();
}
