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
 * Created by stone on 2017/10/21.
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

    private synchronized void doSaveLocal(Integer id, GenElement data, String path, CompletionHandler<GenElement, Boolean> handler) throws IOException {
        if (!this.channelMap.containsKey(id)) {
            this.channelMap.put(id, initChannel(id, path));
        }
        if (!this.positionMap.containsKey(id)) {
            this.positionMap.put(id, 0);
        }
        int position = this.positionMap.get(id);
        byte[] bytes = (data.toString() + "\n").getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int nextPosition = position + bytes.length;
        this.positionMap.put(id, nextPosition);
        try {
            this.channelMap.get(id).write(buffer, position).get(TIMEOUT, TimeUnit.MILLISECONDS);
            handler.completed(data, true);
        } catch (Exception e) {
            handler.failed(e, false);
        }

    }

    private synchronized void doSaveRedis(Integer id, GenElement data, String path, CompletionHandler<GenElement, Boolean> handler) {

    }

    private AsynchronousFileChannel initChannel(Integer id, String path) throws IOException {
        String filePath;
        if (Utils.isEmpty(path)) {
            filePath = String.valueOf(id);
        } else {
            filePath = path;
        }
        return AsynchronousFileChannel.open(Utils.getPath(filePath), StandardOpenOption.WRITE);
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
