package com.eilikce.toolkit.action;

import java.util.Map;

public interface BaseAction<R> {

    default BaseAction<R> init(Map<String, String> param) {
        return this;
    }

    R doAction();

}
