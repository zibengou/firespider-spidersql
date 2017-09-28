package com.firespider.spidersql.action.model;

/**
 * Created by xiaotong.shi on 2017/9/28.
 */
public abstract class Param {
    private int timeout;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
