package com.firespider.spidersql.action;

import com.firespider.spidersql.io.net.Format;
import com.firespider.spidersql.io.net.HttpAsyncClient;
import com.firespider.spidersql.io.net.Response;
import com.firespider.spidersql.lang.json.GenJsonArray;
import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;
import com.firespider.spidersql.action.model.GetParam;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public class GetAction extends Action {
    private final HttpAsyncClient client;

    private static final int thread = 200;

    private static final int HOLE_TIMEOUT = 60 * 60;

    public GetAction(Integer id, GetParam param, CompletionHandler<GenJsonElement, Boolean> handler) throws IOException {
        super(id, param, handler);
        client = new HttpAsyncClient(thread);
    }

    /***
     * 解析GetParam对象
     * @param param
     */
    private List<String> parse(GetParam param) {
        if (param.getHeader() != null) {
            client.setHeader(param.getHeader());
        }
        if (param.getCharset() != null) {
            client.setCharset(Charset.forName(param.getCharset()));
        }
        return parseUrl(param.getUrl());

    }

    /***
     * 解析URL
     * @param element
     * @return
     */
    private List<String> parseUrl(GenJsonElement element) {
        List<String> urlList = new ArrayList<>();
        if (element instanceof GenJsonArray) {
            Iterator<GenJsonElement> iterator = element.getAsArray().iterator();
            while (iterator.hasNext()) {
                String url = iterator.next().getAsString();
                urlList.addAll(Format.parseSingleUrl(url));
            }
        } else {
            urlList.addAll(Format.parseSingleUrl(element.getAsString()));
        }
        return urlList;
    }


    /***
     * 请求数据
     * todo 解析返回数据
     */
    void handle() throws IOException, InterruptedException {
        List<String> urlList = parse((GetParam) param);
        CountDownLatch latch = new CountDownLatch(urlList.size());
        urlList.forEach(url -> {
            client.handleGet(url, new CompletionHandler<Response, String>() {
                GenJsonObject obj = new GenJsonObject();

                @Override
                public void completed(Response result, String attachment) {
                    obj.addPrimitive("body", result.getBody());
                    obj.addPrimitive("url", attachment);
                    handler.completed(obj, true);
                    latch.countDown();
                }

                @Override
                public void failed(Throwable exc, String attachment) {
                    obj.addPrimitive("url", attachment);
                    handler.completed(obj, false);
                    latch.countDown();
                }
            });
        });
        latch.await(HOLE_TIMEOUT, TimeUnit.SECONDS);
        client.close();
    }

}
