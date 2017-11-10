package com.firespider.spidersql.queue;

import com.firespider.spidersql.lang.GenElement;

import java.nio.channels.CompletionHandler;

/**
 * Created by xiaotong.shi on 2017/11/7.
 */
public interface QueueManager {

    void regist(Integer id, Integer sourceId, CompletionHandler<GenElement, Integer> handler);

    boolean publish(Integer id, GenElement data);

    boolean consume(Integer id);

    GenElement getAll(Integer id);

    boolean exists(Integer id);

    void clear();
}
