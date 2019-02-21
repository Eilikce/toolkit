package com.eilikce.toolkit.controller;

import com.eilikce.toolkit.model.HttpResult;
import com.eilikce.toolkit.service.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ActionController {

    private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

    @Autowired
    ActionService service;

    @RequestMapping("test")
    HttpResult test(@RequestBody Map<String,String> param) {
        LOG.info(param.toString());
        return new HttpResult(0, param,"");
    }

    @RequestMapping("elasticsearch/pai")
    HttpResult elasticsearchPrepareAgentInfo(@RequestBody Map<String,String> param) {

        return service.elasticsearchPrepareAgentInfo(param);
    }

    @RequestMapping("elasticsearch/cai")
    HttpResult elasticsearchCreateAgentInfo(@RequestBody Map<String,String> param) {

        return service.elasticsearchCreateAgentInfo(param);
    }

    @GetMapping("elasticsearch/list")
    HttpResult elasticsearchFileList() {

        return service.elasticsearchFileList();
    }

    @RequestMapping("mysql/query")
    HttpResult mysqlQuery() {
        return service.mysqlQuery();
    }


}
