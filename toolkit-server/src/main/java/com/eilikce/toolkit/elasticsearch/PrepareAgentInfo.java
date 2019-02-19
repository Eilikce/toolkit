package com.eilikce.toolkit.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eilikce.toolkit.action.BaseAction;
import com.eilikce.toolkit.elasticsearch.dao.BaseDao;
import com.eilikce.toolkit.file.FileUtil;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrepareAgentInfo implements BaseAction<Void> {

    private BaseDao dao = BaseDao.getInstance();

    private String responseData;
    private String bakDataFile;

    @Override
    public BaseAction init(Map<String,String> param) {
        Optional.of(param.get("url")).ifPresent(url -> dao.init(url));
        Optional.ofNullable(param.get("bakDataFile")).ifPresent(fileName -> bakDataFile=fileName);
        return this;
    }

    @Override
    public Void doAction() {
        prepareData();
        bulkActionsCreate();
        return null;
    }

    private void prepareData() {

        if (bakDataFile != null && !"".equals(bakDataFile)) {
            responseData = FileUtil.readFile(bakDataFile);
        } else {
            responseData = dao.baseHandler(restClient -> {
                String data = null;
                try {
                    Request request = new Request(
                            "GET",
                            "/uas_agent_info/_search?size=10000");
                    Response response = restClient.performRequest(request);
                    data = EntityUtils.toString(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return data;
            });
            FileUtil.saveData("bakData_" + System.currentTimeMillis() + ".json", responseData);
        }
    }

    /**
     * bulk操作块生成器
     */
    private void bulkActionsCreate() {
        //获取命中数据
        JSONArray hits = JSON.parseObject(responseData).getJSONObject("hits").getJSONArray("hits");

        //拼接bulk操作块
        JSONArray bulkActions = hits.stream()
                .map(hit -> {
                    JSONObject source = ((JSONObject) hit).getJSONObject("_source");

                    if (!source.containsKey("collectorType")){
                        source.fluentPut("collectorType", "PINPOINT");
                    }
                    if (!source.containsKey("sqlTimeThreshold")){
                        source.fluentPut("sqlTimeThreshold", "20000");
                    }

                    return source;
                })
                .map(agentInfo -> {
                    JSONArray jsonArray = new JSONArray();

                    String agentId = agentInfo.getString("agentId");
                    String action = "{ \"index\" : { \"_index\" : \"uas_agent_info\", \"_type\" : \"doc\"}, \"retry_on_conflict\" : 3 }";

                    JSONObject jsonObject = JSON.parseObject(action);
                    jsonObject.getJSONObject("index").put("_id", agentId);

                    jsonArray.add(jsonObject);
                    jsonArray.add(agentInfo);

                    return jsonArray;
                })
                .flatMap(JSONArray::stream)
                .collect(Collectors.toCollection(JSONArray::new));

        String bulkJson = bulkActions.toJSONString();
        FileUtil.saveData("bulkActions.json", bulkJson);
    }

}
