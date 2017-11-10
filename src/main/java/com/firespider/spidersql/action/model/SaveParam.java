package com.firespider.spidersql.action.model;

import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.lang.GenObject;

/**
 * Created by xiaotong.shi on 2017/9/28.
 */
public class SaveParam extends Param {
    private String path;
    private GenElement data;
    private String type;

    public SaveParam(GenObject element) {
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

    public GenElement getData() {
        return data;
    }

    public void setData(GenElement data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
