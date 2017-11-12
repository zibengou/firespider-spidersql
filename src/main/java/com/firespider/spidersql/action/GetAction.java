package com.firespider.spidersql.action;

import com.firespider.spidersql.io.net.Format;
import com.firespider.spidersql.io.net.HttpAsyncClient;
import com.firespider.spidersql.io.net.Response;
import com.firespider.spidersql.lang.*;
import com.firespider.spidersql.action.model.GetParam;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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


    public GetAction(Integer id, GetParam param, CompletionHandler<GenElement, Boolean> handler) throws IOException {
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
    private List<String> parseUrl(GenElement element) {
        List<String> urlList = new ArrayList<>();
        if (element instanceof GenArray) {
            Iterator<GenElement> iterator = element.getAsArray().iterator();
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
     * 解析Body
     * 解析HTML 解析JSON 解析STRING
     */
    private GenObject parseBody(String body, Set<Map.Entry> parseMap) {
        GenObject res = new GenObject();
        RESPONSE_TYPE type = parseResponseType(body);
        Object preBody = doParseBodyPre(body, type);
        for (Map.Entry<String, GenElement> entry : parseMap) {
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
                return JsonPath.parse(body);
            case STRING:
                return body;
            default:
                return body;
        }
    }

    private GenElement doParsebody(String regrex, Object body, RESPONSE_TYPE type) {
        List<Object> parseResList = null;
        switch (type) {
            case HTML:
                parseResList = doParseHtmlBody(regrex, (Document) body);
                break;
            case JSON:
                parseResList = doParseJsonBody(regrex, (DocumentContext) body);
                break;
            case STRING:
                parseResList = doParseStringBody(regrex, (String) body);
                break;
        }
        if (parseResList == null || parseResList.size() == 0) {
            return GenNull.INSTANCE;
        } else if (parseResList.size() == 1) {
            return new GenPrimitive<>(parseResList.get(0));
        } else {
            GenArray array = new GenArray();
            parseResList.forEach(l -> array.add(new GenPrimitive<>(l)));
            return array;
        }
    }

    private List<Object> doParseHtmlBody(String regrex, Document doc) {
        List<Object> resList = new ArrayList<>();
        try {
            for (String l : Xsoup.compile(regrex).evaluate(doc).list()) {
                resList.add(l);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resList;
    }

    private List<Object> doParseJsonBody(String regrex, DocumentContext ctx) {
        List<Object> resList = new ArrayList<>();
        try {
            Object res = ctx.read(regrex);
            if (res instanceof List) {
                resList.addAll((Collection<? extends String>) res);
            } else {
                resList.add(res);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resList;
    }

    private List<Object> doParseStringBody(String regrex, String body) {
        List<Object> resList = new ArrayList<>();
        return resList;
    }


    /***
     * 请求数据
     */
    void handle() throws IOException, InterruptedException {
        List<String> urlList = parse((GetParam) param);
        Set filterMap = ((GetParam) param).getParse().entrySet();
        CountDownLatch latch = new CountDownLatch(urlList.size());
        urlList.forEach(url -> {
            client.handleGet(url, new CompletionHandler<Response, String>() {
                GenObject obj = new GenObject();

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
