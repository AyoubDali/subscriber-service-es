package com.sofrecom.elasticsearch;

import com.sofrecom.elasticsearch.resource.Config;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ElasticsearchApplication  implements CommandLineRunner {


    private RestHighLevelClient client;

    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


        Config conf = new Config();
        this.client = conf.client();

        GetRequest getRequest = new GetRequest("db.elasticsearchapp.subscriber","doc","1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);


        System.out.println("response id: "+getResponse.getSourceAsString());


    }
}




