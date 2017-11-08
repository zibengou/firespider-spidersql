package com.firespider.spidersql.action.model;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;

/**
 * Created by xiaotong.shi on 2017/9/28.
 */
public class SaveParam extends Param {
    private String path;
    private GenJsonElement data;
    private String type;

    public SaveParam(GenJsonObject element) {
        this.path = element.get("path").getAsString();
        this.type = element.get("type").getAsString();
        this.data = element.get("data").getAsElement();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GenJsonElement getData() {
        return data;
    }

    public void setData(GenJsonElement data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
