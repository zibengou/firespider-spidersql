package com.firespider.spidersql.queue;

import com.firespider.spidersql.lang.json.GenJsonArray;
import com.firespider.spidersql.lang.json.GenJsonElement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by stone on 2017/10/18.
 */
public class QueueManager {
    private Map<Integer, ConcurrentLinkedQueue<GenJsonElement>> queueMap;

    public QueueManager() {
        this.queueMap = new ConcurrentHashMap<>();
    }

    public void regist(Integer id) {
        queueMap.put(id, new ConcurrentLinkedQueue());
    }

    public void publish(Integer id, GenJsonElement data) {
        queueMap.get(id).add(data);
    }

    public GenJsonElement consume(Integer id) {
        return queueMap.get(id).poll();
    }

    public boolean exists(Integer id) {
        return queueMap.containsKey(id);
    }

    public GenJsonArray getAll(Integer id) {
        GenJsonArray array = new GenJsonArray();
        queueMap.get(id).iterator().forEachRemaining(array::add);
        return array;
    }

    public void clear() {
        queueMap.forEach((k, v) -> v.clear());
    }
}
