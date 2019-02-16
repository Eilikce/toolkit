package com.eilikce.toolkit.action;

public class ActionException extends Exception {

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionException(String message) {
        super(message);
    }
}
