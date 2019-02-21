package com.eilikce.toolkit.service;

import com.eilikce.toolkit.action.BaseAction;
import com.eilikce.toolkit.model.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    protected <V extends BaseAction> HttpResult baseActionService(Supplier<V> supplier, Function<V, BaseAction> function) {
        try {
            return new HttpResult(0, Optional.ofNullable(function.apply(supplier.get()).doAction()).orElse("success"), "");
        } catch (Exception e) {
            String error = "Action处理失败";
            LOG.error(error, e);
            return new HttpResult(1, "", error);
        }
    }

}
