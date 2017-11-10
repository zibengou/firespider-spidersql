package com.firespider.spidersql.action.model;

import com.firespider.spidersql.lang.GenObject;

/**
 * Created by xiaotong.shi on 2017/9/28.
 */
public class ScanParam extends Param{
    String host;
    String port;

    public ScanParam(GenObject element){
        this.host = element.get("host").getAsString();
        this.port = element.get("port").getAsString();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
