package com.eilikce.toolkit.elasticsearch.dao;

import com.eilikce.toolkit.elasticsearch.client.SimpleClient;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class BaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);

    private static BaseDao INSTANCE = new BaseDao();
    private String url;

    public static BaseDao getInstance() {
        return INSTANCE;
    }

    private BaseDao() {
    }

    public void init(String url) {
        this.url = url;
    }

    public <R> R baseHandler(Function<RestClient,R> function) {
        R r;
        SimpleClient simpleClient = new SimpleClient();
        try {
            r = function.apply(simpleClient.connect(url));
        } finally {
            try {
                simpleClient.close();
            } catch (IOException e) {
                LOG.error("Elasticsearch操作失败", e);
            }
        }

        return r;
    }

    public void baseHandler(Consumer<RestClient> consumer) {
            RestClient client = new SimpleClient().connect(url);
            try {
                consumer.accept(client);
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    LOG.error("Elasticsearch操作失败", e);
                }
            }
        }


}
