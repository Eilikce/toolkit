package com.eilikce.toolkit.service;

import com.eilikce.toolkit.kits.elasticsearch.CreateAgentInfo;
import com.eilikce.toolkit.kits.elasticsearch.FileListAction;
import com.eilikce.toolkit.kits.elasticsearch.PrepareAgentInfo;
import com.eilikce.toolkit.kits.mysql.QueryAction;
import com.eilikce.toolkit.kits.mysql.dao.EilikceDao;
import com.eilikce.toolkit.kits.redis.RedisAction;
import com.eilikce.toolkit.model.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ActionService extends BaseService {

    @Resource
    private EilikceDao eilikceDao;

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public HttpResult elasticsearchPrepareAgentInfo(Map<String, String> param) {
        return baseActionService(PrepareAgentInfo::new,
                action -> action.init(param));
    }

    public HttpResult elasticsearchCreateAgentInfo(Map<String, String> param) {
        return baseActionService(CreateAgentInfo::new, action -> action.init(param));
    }

    public HttpResult elasticsearchFileList() {
        FileListAction f = new FileListAction();
        f.doAction();
        return baseActionService(FileListAction::new, fileListAction -> fileListAction);
    }

    public HttpResult mysqlQuery() {
        return baseActionService(QueryAction::new,
                action -> action.init(eilikceDao));
    }

    public HttpResult redisQuery() {
        return baseActionService(RedisAction::new, redisAction -> redisAction.init(redisTemplate));
    }

}
