package com.sofrecom.elasticsearch.controller;

import com.sofrecom.elasticsearch.exception.NoDataFoundException;
import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/subscriber")
@CrossOrigin
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;


    @GetMapping("/search/{query}")
    public List<Subscriber> searchSubscriber(@PathVariable String query) throws IOException, NoDataFoundException {

        System.out.println("request found");
        return subscriberService.searchForSubscriber(query);
    }



}
