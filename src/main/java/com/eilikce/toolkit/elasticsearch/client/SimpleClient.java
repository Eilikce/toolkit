package com.eilikce.toolkit.elasticsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class SimpleClient {

    private RestClient client;

    public RestClient connect(String url) {
        String[] urlParse = url.split(":");
        String host = urlParse[0];
        int port = urlParse.length == 2 ? Integer.parseInt(urlParse[1]) : 80;

        return connect(host, port);
    }

    public RestClient connect(String host, int port) {
        System.out.println("Elasticsearch connect "+host+":"+port);
        return client = RestClient.builder(
                new HttpHost(host, port, "http")).build();
    }

    public void close() throws IOException {
        client.close();
    }
}
