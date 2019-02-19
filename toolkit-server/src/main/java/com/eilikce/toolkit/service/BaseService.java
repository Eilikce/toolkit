package com.eilikce.toolkit.service;

import com.eilikce.toolkit.action.ActionException;
import com.eilikce.toolkit.model.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    protected HttpResult baseActionService(ActionFunction function) {
        try {
            return new HttpResult(0, function.apply().orElse("success"), "");
        } catch (ActionException e) {
            LOG.error(e.getMessage(), e);
            return new HttpResult(0, "", e.getMessage());
        }

    }
}
