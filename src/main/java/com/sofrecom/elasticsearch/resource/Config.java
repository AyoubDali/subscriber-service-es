package com.sofrecom.elasticsearch.resource;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public  class Config {


    @Bean
    public RestHighLevelClient client(){
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("elasticsearch:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
