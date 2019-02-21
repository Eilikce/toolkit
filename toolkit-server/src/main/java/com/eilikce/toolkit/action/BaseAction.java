package com.eilikce.toolkit.action;

import java.util.Map;

public interface BaseAction<R> {

    default BaseAction init(Map<String, String> param) {
        return this;
    }

    default BaseAction init(Object... objects) {
        return this;
    }

    R doAction();

}
