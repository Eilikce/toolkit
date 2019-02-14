package com.eilikce.toolkit.model;

public class HttpResult {

    private int code;
    private Object msg;
    private String error;

    public HttpResult() {

    }

    public HttpResult(int code, Object msg, String error) {
        this.code = code;
        this.msg = msg;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg=" + msg +
                ", error='" + error + '\'' +
                '}';
    }
}
