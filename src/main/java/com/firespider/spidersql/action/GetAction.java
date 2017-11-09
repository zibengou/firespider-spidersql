package com.firespider.spidersql.action;

import com.firespider.spidersql.io.net.Format;
import com.firespider.spidersql.io.net.HttpAsyncClient;
import com.firespider.spidersql.io.net.Response;
import com.firespider.spidersql.lang.json.*;
import com.firespider.spidersql.action.model.GetParam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
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

    private enum RESPONSE_TYPE {
        JSON, HTML, STRING;
    }


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

    private GenJsonObject parseBody(String body, Set<Map.Entry> parseMap) {
        GenJsonObject res = new GenJsonObject();
        RESPONSE_TYPE type = parseResponseType(body);
        Object preBody = doParseBodyPre(body, type);
        for (Map.Entry<String, GenJsonElement> entry : parseMap) {
            String key = entry.getKey();
            String regrex = entry.getValue().getAsString();
            res.add(key, doParsebody(regrex, preBody, type));
        }
        return res;
    }

    private RESPONSE_TYPE parseResponseType(String body) {
        String topStr = body.substring(0, body.length() > 50 ? 50 : body.length());
        String firstStr = topStr.trim().substring(0, 1);
        if (topStr.contains("<html")) {
            return RESPONSE_TYPE.HTML;
        } else if (firstStr.equals("{") || firstStr.equals("[")) {
            return RESPONSE_TYPE.JSON;
        } else {
            return RESPONSE_TYPE.STRING;
        }
    }

    private Object doParseBodyPre(String body, RESPONSE_TYPE type) {
        switch (type) {
            case HTML:
                return Jsoup.parse(body);
            case JSON:
                return body;
            case STRING:
                return body;
            default:
                return body;
        }
    }

    private GenJsonElement doParsebody(String regrex, Object body, RESPONSE_TYPE type) {
        List<String> parseResList = null;
        switch (type) {
            case HTML:
                parseResList = doParseHtmlBody(regrex, (Document) body);
                break;
            case JSON:
                parseResList = doParseJsonBody(regrex, (String) body);
                break;
            case STRING:
                parseResList = doParseStringBody(regrex, (String) body);
                break;
        }
        if (parseResList == null || parseResList.size() == 0) {
            return GenJsonNull.INSTANCE;
        } else if (parseResList.size() == 1) {
            return new GenJsonPrimitive<>(parseResList.get(0));
        } else {
            GenJsonArray array = new GenJsonArray();
            parseResList.forEach(l -> array.add(new GenJsonPrimitive<>(l)));
            return array;
        }
    }

    private List<String> doParseHtmlBody(String regrex, Document doc) {
        List<String> resList = new ArrayList<>();
        try {
            resList = Xsoup.compile(regrex).evaluate(doc).list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resList;
    }

    private List<String> doParseJsonBody(String regrex, String body) {
        List<String> resList = new ArrayList<>();
        return resList;
    }

    private List<String> doParseStringBody(String regrex, String body) {
        List<String> resList = new ArrayList<>();
        return resList;
    }


    /***
     * 请求数据
     * todo 解析返回数据
     */
    void handle() throws IOException, InterruptedException {
        List<String> urlList = parse((GetParam) param);
        Set filterMap = ((GetParam) param).getParse().entrySet();
        CountDownLatch latch = new CountDownLatch(urlList.size());
        urlList.forEach(url -> {
            client.handleGet(url, new CompletionHandler<Response, String>() {
                GenJsonObject obj = new GenJsonObject();

                @Override
                public void completed(Response result, String attachment) {
                    obj = parseBody(result.getBody(), filterMap);
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
