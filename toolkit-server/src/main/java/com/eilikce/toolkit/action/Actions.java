package com.eilikce.toolkit.action;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Actions {

    /**
     * 命令选择器
     */
    public static <R> Optional<R> choseActions(Supplier<BaseAction<R>> action, Map<String, String> param) throws ActionException {
        try {
            return Optional.ofNullable(action.get().init(param).doAction());
        } catch (Exception e) {
            throw new ActionException("Action执行异常",e);
        }
    }

    /**
     * 命令选择器
     */
    public static <R> Optional<R> choseActions(Supplier<BaseAction<R>> action) throws ActionException {
        return choseActions(action, null);
    }
}
