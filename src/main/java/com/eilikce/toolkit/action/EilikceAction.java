package com.eilikce.toolkit.action;

import com.eilikce.toolkit.elasticsearch.CreateAgentInfo;
import com.eilikce.toolkit.elasticsearch.PrepareAgentInfo;
import com.eilikce.toolkit.model.ActionOptions;

import java.util.Map;
import java.util.Optional;

public interface EilikceAction {

    EilikceAction init(Map<String, String> options);

    void doAction();

    /**
     * 命令选择器
     *
     * @return
     */
    static void choseActions(String option, Map<String, String> param) throws ActionException {
        EilikceAction action;
        switch (ActionOptions.check(option)) {
            case PAI:
                action = new PrepareAgentInfo();
                break;
            case CAI:
                action = new CreateAgentInfo();
                break;
            default:
                throw new ActionException("没有与" + option + "匹配的Action");
        }

        try {
            action.init(param).doAction();
        } catch (Exception e) {
            throw new ActionException("Action执行异常",e);
        }
    }

}
