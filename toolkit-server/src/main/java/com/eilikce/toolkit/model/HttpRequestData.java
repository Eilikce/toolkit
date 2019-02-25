package com.eilikce.toolkit.model;

import java.util.Map;

public class HttpRequestData {
    private Map<String, Object> source;
    private Map<String, Object> msg;

    public Map<String, Object> getSource() {
        return source;
    }

    public void setSource(Map<String, Object> source) {
        this.source = source;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HttpRequestData{" +
                "source=" + source +
                ", msg=" + msg +
                '}';
    }
}
