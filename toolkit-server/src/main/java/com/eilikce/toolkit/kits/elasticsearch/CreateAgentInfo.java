package com.eilikce.toolkit.kits.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.eilikce.toolkit.action.BaseAction;
import com.eilikce.toolkit.kits.elasticsearch.dao.BaseDao;
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

public class CreateAgentInfo implements BaseAction<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(CreateAgentInfo.class);

    private BaseDao dao = BaseDao.getInstance();

    private String bulkActions;

    @Override
    public CreateAgentInfo init(Map<String, String> param) {
        Optional.of(param.get("url")).ifPresent(url -> dao.init(url));
        return this;
    }

    @Override
    public Void doAction() {
        parseBulkActions();
        createData();
        return null;
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
        String bulkJson = FileUtil.readFile("bulkActions.json");
        bulkActions = JSON.parseArray(bulkJson).stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n", "", "\n"));
        LOG.info(bulkActions);
    }

}
