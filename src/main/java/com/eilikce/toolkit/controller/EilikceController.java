package com.eilikce.toolkit.controller;

import com.eilikce.toolkit.model.HttpResult;
import com.eilikce.toolkit.service.EilikceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EilikceController {

    private static final Logger LOG = LoggerFactory.getLogger(EilikceController.class);

    @Autowired
    EilikceService service;

    @RequestMapping("test")
    HttpResult eilikceTest(@RequestParam Map<String,String> param) {
        LOG.info(param.toString());
        return new HttpResult(0, param,"");
    }

    @RequestMapping("elasticsearch/pai")
    HttpResult elasticsearchPrepareAgentInfo(@RequestParam Map<String,String> param) {

        return service.elasticsearchPrepareAgentInfo(param);
    }

    @RequestMapping("elasticsearch/cai")
    HttpResult elasticsearchCreateAgentInfo(@RequestParam Map<String,String> param) {

        return service.elasticsearchCreateAgentInfo(param);
    }


}
