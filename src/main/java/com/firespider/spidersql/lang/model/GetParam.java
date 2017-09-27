package com.firespider.spidersql.lang.model;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;

import java.util.Map;

/**
 * Created by xiaotong.shi on 2017/9/27.
 */
public class GetParam {
    private String url;
    private String charset;
    private Integer timeout;

    private Map<String,String[]> regrexMap;
    private Map<String,String> header;

    public GetParam(GenJsonObject element){
        this.url = element.get("url").getAsString();
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
