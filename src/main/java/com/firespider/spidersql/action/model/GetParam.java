package com.firespider.spidersql.action.model;

import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.lang.GenObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaotong.shi on 2017/9/27.
 */
public class GetParam extends Param {
    private GenElement url;
    private GenObject parse;
    private String charset;

    private Map<String, String> header;

    public GetParam(GenObject element) {
        this.url = element.get("url").getAsElement();
        if (element.has("filter")) {
            this.parse = element.get("filter").getAsObject();
        } else {
            this.parse = new GenObject();
        }
        if (element.has("header")) {
            this.header = new LinkedHashMap<>();
            element.get("header").getAsObject().entrySet().forEach(e -> {
                String key = e.getKey();
                String value = e.getValue().getAsString();
                this.header.put(key, value);
            });
        }
        if (element.has("charset")) {
            this.charset = element.get("charset").getAsString();
        }
        if (element.has("timeout")) {
            this.setTimeout(element.get("timeout").getAsInteger());
        }
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public GenElement getUrl() {
        return url;
    }

    public void setUrl(GenElement url) {
        this.url = url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public GenObject getParse() {
        return parse;
    }

    public void setParse(GenObject parse) {
        this.parse = parse;
    }
}
