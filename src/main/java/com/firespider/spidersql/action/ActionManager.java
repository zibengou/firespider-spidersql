package com.firespider.spidersql.action;

import com.firespider.spidersql.action.model.SaveParam;
import com.firespider.spidersql.action.model.ScanParam;
import com.firespider.spidersql.aio.file.DefaultStorageManager;
import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.action.model.GetParam;
import com.firespider.spidersql.queue.LocalQueueManager;
import com.firespider.spidersql.queue.QueueManager;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.util.*;
import java.util.concurrent.*;

public class ActionManager {
    //变量与ID的映射关系，需要保序
    private final Map<String, Integer> varIdMap = new LinkedHashMap<>();

    private final Map<Integer, Action> actionMap = new HashMap<>();

    //ID与数据结果映射关系，线程安全
//    private final Map<Integer, GenElement> idData = new ConcurrentHashMap<>();
    private final QueueManager queueManager;
    private final ActionChecker checker;

    private final int threadNum;

    private ExecutorService service;

    public ActionManager(int threadNum) {
        this.service = Executors.newFixedThreadPool(threadNum);
        this.checker = new ActionChecker();
        this.queueManager = new LocalQueueManager();
        this.threadNum = threadNum;
    }

    public enum TYPE {
        GET, SCAN, DESC, PRINT, SAVE, VALUE;
    }

    /***
     * 接收并执行动作
     * @param element
     * @param type
     * @return
     */
    public Integer accept(GenElement element, TYPE type) {
        Integer id = element.hashCode();
        queueManager.regist(id, null, null);
        Action action = accept(element, type, id);
        actionMap.put(id, action);
        return id;
    }


    public Action accept(GenElement element, TYPE type, Integer id) {
        Action action = null;
        if (actionMap.containsKey(id)) {
            return actionMap.get(id);
        }
        if (!checker.check(element, type)) {
            return action;
        }
        // TODO: 2017/9/27 完善剩余action内容
        try {
            switch (type) {
                case GET:
                    action = acceptGet(element.getAsObject(), id);
                    break;
                case SCAN:
                    action = acceptScan(element.getAsObject(), id);
                    break;
                case DESC:
                    break;
                case PRINT:
                    acceptPrint(element.getAsElement());
                    break;
                case SAVE:
                    action = acceptSave(element.getAsObject(), id);
                    break;
                case VALUE:
                    queueManager.publish(id, element.getAsElement());
                    break;
                default:
                    return action;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(type + " init error!");
        }
        return action;
    }

    public Integer acceptHandler(GenElement element, TYPE type, Integer sourceId) {
        Integer id = element.hashCode();
        this.queueManager.regist(id, sourceId, new CompletionHandler<GenElement, Integer>() {
            @Override
            public void completed(GenElement result, Integer attachment) {
                GenElement newElement = element.deepCopy();
                newElement.setJsonVarElement(result);
                Action action = accept(newElement, type, id);
                if (action != null) {
                    action.run();
                }
            }

            @Override
            public void failed(Throwable exc, Integer attachment) {

            }
        });
        return id;
    }

    private Action acceptGet(GenObject element, Integer id) throws IOException {

        GenArray value = new GenArray();
        // TODO: 2017/9/27 确认是否会出现回调地狱
        // 在handler中对value赋值，该块堆内存会在另外N份线程中做add操作
        Action action = new GetAction(id, new GetParam(element), new CompletionHandler<GenElement, Boolean>() {
            @Override
            public void completed(GenElement result, Boolean attachment) {
                if (attachment) {
                    if (result instanceof GenArray) {
                        result.getAsArray().iterator().forEachRemaining(ele -> queueManager.publish(id, ele));
                    } else {
                        queueManager.publish(id, result);
                    }
                } else {
                    queueManager.publish(id, GenNull.INSTANCE);
                }
            }

            @Override
            public void failed(Throwable exc, Boolean attachment) {
                value.add(GenNull.INSTANCE);
            }
        });
        return action;
    }

    private Action acceptScan(GenObject element, Integer id) throws IOException {
        Action action = new ScanAction(id, new ScanParam(element), new CompletionHandler<GenElement, Boolean>() {
            @Override
            public void completed(GenElement result, Boolean attachment) {
                if (attachment) {
                    queueManager.publish(id, result);
                }
            }

            @Override
            public void failed(Throwable exc, Boolean attachment) {

            }
        });
        return action;
    }

    private void acceptPrint(GenElement element) {
        removeFinishFlag(element);
        System.out.println(element.toString());
    }

    private Action acceptSave(GenObject element, Integer id) {
        removeFinishFlag(element.get("data"));
        Action action = new SaveAction(id, new SaveParam(element), new CompletionHandler<GenElement, Boolean>() {
            @Override
            public void completed(GenElement result, Boolean attachment) {
                queueManager.publish(id, new GenPrimitive<>(attachment));
            }

            @Override
            public void failed(Throwable exc, Boolean attachment) {

            }
        });
        return action;
    }

    private void removeFinishFlag(GenElement element) {
        if (element instanceof GenArray && element.getAsArray().size() > 0) {
            int last = element.getAsArray().size() - 1;
            GenElement ele = element.getAsArray().get(last);
            if (ele instanceof GenPrimitive && ele.getAsPrimitive().getAsString().contains(Action.FINISH_FLAG)) {
                try {
                    Integer.valueOf(ele.getAsPrimitive().getAsString().replace(Action.FINISH_FLAG, ""));
                    ((GenArray) element).remove(last);
                } catch (Exception ignored) {

                }

            }

        } else if (element instanceof GenVar && element.getAsVar().getElement().isArray()) {
            removeFinishFlag(element.getAsVar().getElement().getAsArray());
        }
    }

    /***
     * 绑定变量名与动作ID
     * @param var
     * @param id
     */
    public void bind(String var, Integer id) {
        varIdMap.put(var, id);
    }

//    public void regist(Integer id, Integer sourceId, CompletionHandler<GenElement, Integer> handler) {
//        this.queueManager.regist(id, sourceId, handler);
//    }

    public void execute(int timeout) {
        if (actionMap == null || actionMap.size() == 0)
            return;
        actionMap.forEach((k, v) -> {
            if (v != null) {
                this.service.execute(v);
                this.service.execute(() -> {
                    while (queueManager.consume(k)) ;
                });
            }
        });
        this.await(timeout);
        actionMap.clear();
    }

    /***
     * 等待线程池中任务全部执行完毕
     * @param timeout
     * @return
     */
    public boolean await(int timeout) {
        try {
            service.shutdown();
            if (!service.isTerminated()) {
                service.awaitTermination(timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            service.shutdownNow();
            return false;
        } finally {
            this.service = Executors.newFixedThreadPool(this.threadNum);
        }
    }

    public Map<String, GenElement> getAll() {
        Map<String, GenElement> resMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> map : varIdMap.entrySet()) {
            resMap.put(map.getKey(), queueManager.getAll(map.getValue()));
        }
        return resMap;
    }

    public String getVar(Integer id) {
        Iterator<Map.Entry<String, Integer>> iterator = varIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (id == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void clear() {
        varIdMap.clear();
        queueManager.clear();
        DefaultStorageManager.INSTANCE.close();
    }

    public void close() {
        service.shutdownNow();
    }
}
