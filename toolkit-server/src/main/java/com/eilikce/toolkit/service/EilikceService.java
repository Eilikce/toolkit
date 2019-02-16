package com.eilikce.toolkit.service;

import com.eilikce.toolkit.action.ActionException;
import com.eilikce.toolkit.action.EilikceAction;
import com.eilikce.toolkit.model.HttpResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EilikceService {

    public HttpResult elasticsearchPrepareAgentInfo(Map<String, String> param) {
        try {
            EilikceAction.choseActions("pai", param);
        } catch (ActionException e) {
            return new HttpResult(0, "", e.getMessage());
        }
        return new HttpResult(0, "success", "");
    }

    public HttpResult elasticsearchCreateAgentInfo(Map<String, String> param) {
        try {
            EilikceAction.choseActions("cai", param);
        } catch (ActionException e) {
            return new HttpResult(0, "", e.getMessage());
        }
        return new HttpResult(0, "success", "");
    }

}
