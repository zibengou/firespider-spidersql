package com.firespider.spidersql.queue;

import com.firespider.spidersql.lang.json.GenJsonArray;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonNull;

import java.nio.channels.CompletionHandler;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by stone on 2017/10/18.
 */
public class QueueManager {
    private Map<Integer, ConcurrentLinkedQueue<GenJsonElement>> queueMap;

    private Map<Integer, List<CompletionHandler<GenJsonElement, Integer>>> completionHandlerMap;

    public QueueManager() {
        this.queueMap = new ConcurrentHashMap<>();
        this.completionHandlerMap = new LinkedHashMap<>();
    }

    public void regist(Integer id) {
        regist(id, null, null);
    }

    public void regist(Integer id, Integer sourceId, CompletionHandler<GenJsonElement, Integer> handler) {
        this.queueMap.put(id, new ConcurrentLinkedQueue<>());
        if (sourceId != null) {
            if (this.completionHandlerMap.containsKey(sourceId)) {
                this.completionHandlerMap.get(sourceId).add(handler);
            } else {
                this.completionHandlerMap.put(sourceId, Arrays.asList(handler));
            }
        }
    }

    public void publish(Integer id, GenJsonElement data) {
        this.queueMap.get(id).add(data);
        List<CompletionHandler<GenJsonElement, Integer>> handler = this.completionHandlerMap.get(id);
        if (handler != null) {
            handler.forEach(exe -> exe.completed(data, id));
        }
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
