package com.firespider.spidersql.queue;

import com.firespider.spidersql.lang.json.GenJsonArray;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonNull;

import java.nio.channels.CompletionHandler;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by stone on 2017/10/18.
 */
public class QueueManager {
    private Map<Integer, ConcurrentLinkedQueue<GenJsonElement>> queueMap;

    private Map<Integer, CompletionHandler<GenJsonElement, Boolean>> completionHandlerMap;

    public QueueManager() {
        this.queueMap = new ConcurrentHashMap<>();
        this.completionHandlerMap = new LinkedHashMap<>();
    }

    public void regist(Integer id) {
        regist(id, null);
    }

    public void regist(Integer id, CompletionHandler<GenJsonElement, Boolean> handler) {
        this.queueMap.put(id, new ConcurrentLinkedQueue<>());
        this.completionHandlerMap.put(id, handler);
    }

    public void publish(Integer id, GenJsonElement data) {
        this.queueMap.get(id).add(data);
    }

    public boolean exists(Integer id) {
        return queueMap.containsKey(id);
    }

    public GenJsonElement getAll(Integer id) {
        GenJsonArray array = new GenJsonArray();
        Iterator iterator = queueMap.get(id).iterator();
        int length = 0;
        while (iterator.hasNext()) {
            length++;
            array.add(iterator.next());
        }
        if (length == 0) {
            return GenJsonNull.INSTANCE;
        } else if (length == 1) {
            return array.get(0);
        } else {
            return array;
        }
    }

    public void clear() {
        queueMap.forEach((k, v) -> v.clear());
        queueMap.clear();
        completionHandlerMap.clear();
    }
}
