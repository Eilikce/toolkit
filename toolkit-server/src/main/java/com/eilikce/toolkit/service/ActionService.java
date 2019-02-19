package com.eilikce.toolkit.service;

import com.eilikce.toolkit.action.Actions;
import com.eilikce.toolkit.elasticsearch.CreateAgentInfo;
import com.eilikce.toolkit.elasticsearch.FileListAction;
import com.eilikce.toolkit.elasticsearch.PrepareAgentInfo;
import com.eilikce.toolkit.model.HttpResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActionService extends BaseService {

    public HttpResult elasticsearchPrepareAgentInfo(Map<String, String> param) {
        return baseActionService(() -> Actions.choseActions(PrepareAgentInfo::new, param));
    }

    public HttpResult elasticsearchCreateAgentInfo(Map<String, String> param) {
        return baseActionService(() -> Actions.choseActions(CreateAgentInfo::new, param));
    }

    public HttpResult elasticsearchFileList() {
        return baseActionService(() -> Actions.choseActions(FileListAction::new));
    }

}
