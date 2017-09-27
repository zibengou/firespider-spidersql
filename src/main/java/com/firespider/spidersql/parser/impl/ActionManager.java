package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.action.Action;
import com.firespider.spidersql.action.GetAction;
import com.firespider.spidersql.lang.json.GenJsonArray;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonNull;
import com.firespider.spidersql.lang.json.GenJsonObject;
import com.firespider.spidersql.lang.model.GetParam;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaotong.shi on 2017/9/27.
 */
public class ActionManager {
    //变量与ID的映射关系，需要保序
    private final Map<String, Integer> varIdMap = new LinkedHashMap<>();
    //ID与数据结果映射关系，线程安全
    private final Map<Integer, GenJsonElement> idData = new ConcurrentHashMap<>();

    private final ActionChecker checker;

    private final ExecutorService service;

    public ActionManager(int threadNum) {
        this.service = Executors.newFixedThreadPool(threadNum);
        this.checker = new ActionChecker();
    }


    /***
     * 接收并执行动作
     * @param element
     * @param type
     * @return
     */
    public Integer accept(GenJsonElement element, String type) {
        Integer id = 0;
        if (!checker.check(element, type)) {
            return id;
        }
        // TODO: 2017/9/27 完善剩余action内容 
        switch (type) {
            case "get":
                try {
                    id = acceptGet((GenJsonObject) element);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Get client init error!");
                }
                break;
            case "scan":
                break;
            case "desc":
                break;
            case "print":
                break;
            case "save":
                break;
        }
        return id;
    }

    private Integer acceptGet(GenJsonObject element) throws IOException {
        String url = element.get("url").getAsString();
        Integer id = url.hashCode();
        if (idData.containsKey(id)) {
            return id;
        }
        GenJsonArray value = new GenJsonArray();
        // TODO: 2017/9/27 确认是否会出现回调地狱
        // 在handler中对value赋值，该块堆内存会在另外N份线程中做add操作
        Action action = new GetAction(id,new GetParam(element), new CompletionHandler<GenJsonElement, Integer>() {
            @Override
            public void completed(GenJsonElement result, Integer attachment) {
                if (result instanceof GenJsonArray) {
                    value.addAll((GenJsonArray) result);
                } else {
                    value.add(result);
                }
            }

            @Override
            public void failed(Throwable exc, Integer attachment) {
                value.add(GenJsonNull.INSTANCE);
            }
        });
        // 将value的指针与查询ID绑定
        idData.put(id, value);
        service.execute(action);
        return id;
    }

    /***
     * 绑定变量名与动作ID
     * @param var
     * @param id
     */
    public void bind(String var, Integer id) {
        varIdMap.put(var, id);
    }

    /***
     * 等待线程池中任务全部执行完毕
     * @param timeout
     * @return
     */
    public boolean await(int timeout) {
        try {
            service.shutdown();
            if(!service.isTerminated()) {
                service.awaitTermination(timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            service.shutdownNow();
            return false;
        }
    }

    public Map<String, GenJsonElement> getAll() {
        Map<String, GenJsonElement> resMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> map : varIdMap.entrySet()) {
            resMap.put(map.getKey(), idData.get(map.getValue()));
        }
        return resMap;
    }

    public void clear() {
        // TODO: 2017/9/27 清空service 避免内存泄露
        varIdMap.clear();
        idData.clear();
    }

    public void close() {
        service.shutdownNow();
    }
}
