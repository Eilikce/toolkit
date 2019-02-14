package com.eilikce.toolkit.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.eilikce.toolkit.action.EilikceAction;
import com.eilikce.toolkit.elasticsearch.dao.BaseDao;
import com.eilikce.toolkit.file.FileUtil;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateAgentInfo implements EilikceAction {

    private static final Logger LOG = LoggerFactory.getLogger(CreateAgentInfo.class);

    private BaseDao dao = BaseDao.getInstance();

    private String bulkActions;

    @Override
    public EilikceAction init(Map<String, String> options) {

        Optional.of(options.get("url")).ifPresent(url -> dao.init(url));
        return this;
    }

    @Override
    public void doAction() {
        parseBulkActions();
        createData();
    }

    private void createData() {
        dao.baseHandler(restClient -> {
            try {
                Request request = new Request(
                        "PUT",
                        "_bulk?pretty");
                StringEntity stringEntity = new StringEntity(bulkActions, "UTF-8");
                stringEntity.setContentType("application/x-ndjson");
                request.setEntity(stringEntity);

                LOG.info("执行Bulk操作 ...");
                Response response = restClient.performRequest(request);
                System.out.println("Result: " + EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void parseBulkActions() {
        LOG.info("解析 bulkActions.json ...");
        String bulkJson = FileUtil.ReadFile("bulkActions.json");
        bulkActions = JSON.parseArray(bulkJson).stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n", "", "\n"));
        LOG.info(bulkActions);
    }

}
