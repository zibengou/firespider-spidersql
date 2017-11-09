package com.firespider.spidersql.action.model;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaotong.shi on 2017/9/27.
 */
public class GetParam extends Param {
    private GenJsonElement url;
    private GenJsonObject parse;
    private String charset;

    private Map<String, String> header;

    public GetParam(GenJsonObject element) {
        this.url = element.get("url").getAsElement();
        if (element.has("filter")) {
            this.parse = element.get("filter").getAsObject();
        } else {
            this.parse = new GenJsonObject();
        }
        if (element.has("header")) {
            this.header = new LinkedHashMap<>();
            element.get("header").getAsObject().entrySet().forEach(e -> {
                String key = e.getKey();
                String value = e.getValue().getAsString();
                this.header.put(key, value);
            });
        }
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public GenJsonElement getUrl() {
        return url;
    }

    public void setUrl(GenJsonElement url) {
        this.url = url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public GenJsonObject getParse() {
        return parse;
    }

    public void setParse(GenJsonObject parse) {
        this.parse = parse;
    }
}
