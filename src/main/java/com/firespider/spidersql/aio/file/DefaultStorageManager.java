package com.firespider.spidersql.aio.file;

import com.firespider.spidersql.lang.json.GenJsonElement;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by stone on 2017/10/21.
 */
public class DefaultStorageManager implements IStorageManager {

    private final String path;

    private final SAVE_TYPE type;

    private final CompletionHandler<GenJsonElement, Boolean> handler;

    private final Map<Integer, AsynchronousFileChannel> channelMap;

    private enum SAVE_TYPE {
        LOCAL, REDIS;
    }

    public DefaultStorageManager(String path, String type, CompletionHandler handler) {
        this.channelMap = new ConcurrentHashMap<>();
        this.path = path;
        switch (type.toLowerCase()) {
            case "local":
                this.type = SAVE_TYPE.LOCAL;
                break;
            case "redis":
                this.type = SAVE_TYPE.REDIS;
                break;
            default:
                this.type = SAVE_TYPE.LOCAL;
        }
        this.handler = handler;
    }

    public DefaultStorageManager(String path, CompletionHandler handler) {
        this(path, "local", handler);
    }

    public DefaultStorageManager(CompletionHandler handler) {
        this("", handler);
    }

    @Override
    public void save(Integer id, GenJsonElement data) throws IOException {
        switch (this.type) {
            case LOCAL:
                doSaveLocal(id, data);
                break;
            case REDIS:
                doSaveRedis(id, data);
                break;
            default:
                doSaveLocal(id, data);
        }
    }

    private void doSaveLocal(Integer id, GenJsonElement data) throws IOException {
        if (!this.channelMap.containsKey(id)) {
            this.channelMap.put(id, initChannel(id, this.path));
        }
        byte[] bytes = data.toString().getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        this.channelMap.get(id).write(buffer, 0, data, new CompletionHandler<Integer, GenJsonElement>() {
            @Override
            public void completed(Integer result, GenJsonElement attachment) {
                handler.completed(attachment, true);
            }

            @Override
            public void failed(Throwable exc, GenJsonElement attachment) {
                handler.completed(attachment, false);
            }
        });
    }

    private void doSaveRedis(Integer id, GenJsonElement data) {

    }

    private AsynchronousFileChannel initChannel(Integer id, String path) throws IOException {
        return AsynchronousFileChannel.open(Paths.get(String.valueOf(id), path), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}
