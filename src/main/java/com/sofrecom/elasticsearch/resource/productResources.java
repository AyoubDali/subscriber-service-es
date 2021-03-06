package com.sofrecom.elasticsearch.resource;


import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
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
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
public class productResources {

    RestHighLevelClient client;

    public productResources(RestHighLevelClient client) {
        Config conf = new Config();
        this.client = conf.client();
    }

    @GetMapping("/product/addIndex")
    public String addProduct() throws IOException {

        CreateIndexRequest request = new CreateIndexRequest("db.elasticsearchapp.subscriber");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 2)
        );


        org.elasticsearch.client.indices.CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("response id: "+indexResponse.index());

        return "OK";
    }


    @GetMapping("/product/add/{id}")
    public RestStatus saveProduct(@PathVariable String id) throws IOException {


        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest("users3", "doc", id)
                .source(XContentType.JSON,"field", "foo"+id));


        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);

        return bulkResponse.status();
    }


    @GetMapping("/product/delete/{id}")
     public RestStatus deleteDoc(@PathVariable String id) throws IOException {
         BulkRequest request = new BulkRequest();
         request.add(new DeleteRequest("users3", "doc", id));

         BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);

         return bulkResponse.status();
     }

     @GetMapping("/product/{id}")
    public String getDocById(@PathVariable String id) throws IOException {

         GetRequest getRequest = new GetRequest("mongo5.elasticsearchapp.subscriber","doc",id);
         GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        return getResponse.getSourceAsString();

     }


     @GetMapping("/product/all")
     public List<String> getAllDocs() throws IOException {

         SearchRequest searchRequest = new SearchRequest();
         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
         searchSourceBuilder.query(QueryBuilders.matchAllQuery());
         searchRequest.source(searchSourceBuilder);
         searchRequest.indices("test2.elasticsearchapp.subscriber");

         SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

         SearchHits hits = searchResponse.getHits();
         SearchHit[] searchHits = hits.getHits();
         List<String> results = new ArrayList<String>();
         for (SearchHit hit : searchHits) {
             String sourceAsString = hit.getSourceAsString();
             Map<String, Object> sourceAsMap = hit.getSourceAsMap();
             System.out.println(sourceAsMap);

             String fieldRes = (String) sourceAsMap.get("payload");
             System.out.println(fieldRes);

             results.add(fieldRes);
         }
         return results;
     }

}
