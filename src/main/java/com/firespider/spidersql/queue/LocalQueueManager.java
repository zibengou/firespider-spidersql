package com.firespider.spidersql.queue;

import com.firespider.spidersql.action.Action;
import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.lang.GenPrimitive;

import java.nio.channels.CompletionHandler;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by stone on 2017/10/18.
 */
public class LocalQueueManager implements QueueManager {
    private Map<Integer, ConcurrentLinkedQueue<GenElement>> queueMap;

    private Map<Integer, List<CompletionHandler<GenElement, Integer>>> completionHandlerMap;

    public LocalQueueManager() {
        this.queueMap = new ConcurrentHashMap<>();
        this.completionHandlerMap = new LinkedHashMap<>();
    }

    public void regist(Integer id, Integer sourceId, CompletionHandler<GenElement, Integer> handler) {
        this.queueMap.put(id, new ConcurrentLinkedQueue<>());
        if (sourceId != null) {
            if (this.completionHandlerMap.containsKey(sourceId)) {
                this.completionHandlerMap.get(sourceId).add(handler);
            } else {
                this.completionHandlerMap.put(sourceId, new ArrayList<>(Arrays.asList(handler)));
            }
        }
    }

    public boolean publish(Integer id, GenElement data) {
        boolean res = this.queueMap.get(id).offer(data);
        return res;
    }

    public boolean consume(Integer id) {
        if (!this.completionHandlerMap.containsKey(id) || this.completionHandlerMap.get(id) == null) {
            return false;
        }
        boolean hasNext = true;
        GenElement data = this.queueMap.get(id).poll();
        if (data != null) {
            String finishFlag = id + Action.FINISH_FLAG;
            if (data instanceof GenPrimitive && finishFlag.equals(data.getAsString())) {
                hasNext = false;
            } else {
                List<CompletionHandler<GenElement, Integer>> handler = this.completionHandlerMap.get(id);
                if (handler != null) {
                    handler.forEach(exe -> exe.completed(data, id));
                }
            }
        }
        return hasNext;
    }

    public boolean exists(Integer id) {
        return queueMap.containsKey(id);
    }

    public GenElement getAll(Integer id) {
        GenArray array = new GenArray();
        Iterator iterator = queueMap.get(id).iterator();
        int length = 0;
        while (iterator.hasNext()) {
            length++;
            array.add(iterator.next());
        }
        if (length == 0) {
            return GenNull.INSTANCE;
        } else if (length == 1) {
            return array.get(0);
        } else {
            return array;
        }
    }

    public void clear() {
        queueMap.forEach((k, v) -> v.clear());
        completionHandlerMap.forEach((k, v) -> {
            if (v != null) v.clear();
        });
        queueMap.clear();
        completionHandlerMap.clear();
    }
}
