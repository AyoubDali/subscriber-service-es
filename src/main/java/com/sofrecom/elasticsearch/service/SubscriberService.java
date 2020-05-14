package com.sofrecom.elasticsearch.service;


import com.google.gson.Gson;
import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.resource.Config;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SubscriberService {



    @Autowired
    private Gson gson;

    RestHighLevelClient client;

    public SubscriberService() {
        Config conf = new Config();
        this.client = conf.client();
    }



    public List searchForSubscriber(String query) throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(20);
        System.out.println(query);

        //QueryStringQueryBuilder matchQueryBuilder = new   QueryStringQueryBuilder(query);
        MultiMatchQueryBuilder matchQueryBuilder = new MultiMatchQueryBuilder(query);
        matchQueryBuilder.type(MultiMatchQueryBuilder.Type.CROSS_FIELDS);
        matchQueryBuilder.operator(Operator.fromString("AND"));
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
//        searchRequest.indices("db.elasticsearchapp.subscriber");
        searchRequest.indices("mongosource.elasticsearchapp.subscriber");

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        List results = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            String json = hit.getSourceAsString();
            //json = json.replaceAll("\\\\", "");
            Map<String, Object> map = hit.getSourceAsMap();
            Object json2 = map.get("after");
            Subscriber subscriber = gson.fromJson((String) json2, Subscriber.class);
            subscriber.setEs_id(hit.getId());
            System.out.println(hit.getId());
            results.add(subscriber);
            System.out.println(hit.getScore());
            System.out.println(subscriber.getName());

        }
        return results;
    }






//    public List<Subscriber> findAll() throws NoDataFoundException, IOException {
//
//        List<Subscriber> subscriberList = this.getAllDocs();
//
//        if (subscriberList.isEmpty()) {
//
//            throw new NoDataFoundException();
//        }
//
//        return subscriberList;
//    }
//
//
//    public List<Subscriber> getAllDocs() throws IOException {
//
//        SearchRequest searchRequest = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.size(20);
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequest.source(searchSourceBuilder);
//        searchRequest.indices("mongosource.elasticsearchapp.subscriber");
//
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = hits.getHits();
//        List<Subscriber> results = new ArrayList<>();
//        for (SearchHit hit : searchHits) {
//            String json = hit.getSourceAsString();
//            Subscriber subscriber = gson.fromJson(json, Subscriber.class);
//            results.add(subscriber);
//        }
//        return results;
//    }



//    public Subscriber getSubscriberById(String id) throws IOException {
//
//        GetRequest getRequest = new GetRequest("mongosource.elasticsearchapp.subscriber","subscriber",id);
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//
//        String json = getResponse.getSourceAsString();
//        Subscriber subscriber = gson.fromJson(json, Subscriber.class);
//        return subscriber;
//    }
}
