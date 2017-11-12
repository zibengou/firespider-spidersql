package com.firespider.spidersql.aio.file;

import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 默认存储管理模块
 */
public class DefaultStorageManager implements IStorageManager {

    private static final Map<Integer, AsynchronousFileChannel> channelMap = new ConcurrentHashMap<>();

    private static final Map<Integer, Integer> positionMap = new ConcurrentHashMap<>();

    private static final int TIMEOUT = 1000;

    public static final IStorageManager INSTANCE = new DefaultStorageManager();

    @Override
    public void save(Integer id, GenElement data, String path, String type, CompletionHandler<GenElement, Boolean> handler) throws IOException {
        switch (type.toLowerCase()) {
            case "local":
                doSaveLocal(id, data, path, handler);
                break;
            case "redis":
                doSaveRedis(id, data, path, handler);
                break;
            default:
                doSaveLocal(id, data, path, handler);
        }
    }

    /***
     * 本地存储模式
     * 存储不会成为瓶颈，加锁保证数据有序
     */
    private synchronized void doSaveLocal(Integer id, GenElement data, String path, CompletionHandler<GenElement, Boolean> handler) throws IOException {
        if (!channelMap.containsKey(id)) {
            channelMap.put(id, initChannel(id, path));
        }
        if (!positionMap.containsKey(id)) {
            positionMap.put(id, 0);
        }
        int position = positionMap.get(id);
        byte[] bytes = (data.toString() + "\n").getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int nextPosition = position + bytes.length;
        positionMap.put(id, nextPosition);
        try {
            channelMap.get(id).write(buffer, position).get(TIMEOUT, TimeUnit.MILLISECONDS);
            handler.completed(data, true);
        } catch (Exception e) {
            handler.failed(e, false);
        }

    }

    private synchronized void doSaveRedis(Integer id, GenElement data, String path, CompletionHandler<GenElement, Boolean> handler) {

    }

    /***
     * 初始化AIO通道
     * @param id
     * @param path
     * @return
     * @throws IOException
     */
    private AsynchronousFileChannel initChannel(Integer id, String path) throws IOException {
        String filePath;
        if (Utils.isEmpty(path)) {
            filePath = String.valueOf(id);
        } else {
            filePath = path;
        }
        return AsynchronousFileChannel.open(Utils.getPath(filePath), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    public void close() {
        positionMap.clear();
        channelMap.forEach((k, v) -> {
            try {
                v.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        channelMap.clear();
    }
}
