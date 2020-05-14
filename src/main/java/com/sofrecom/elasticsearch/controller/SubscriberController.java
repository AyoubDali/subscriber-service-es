package com.sofrecom.elasticsearch.controller;

import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.model.SubscriberDTO;
import com.sofrecom.elasticsearch.model.User;
import com.sofrecom.elasticsearch.repository.SubscriberRepository;
import com.sofrecom.elasticsearch.repository.UserRepository;
import com.sofrecom.elasticsearch.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/subscriber")
@CrossOrigin
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;
    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Subscriber saveSubscriber(@RequestBody SubscriberDTO subscriberDto)  {

        Optional<User> user = userRepository.findByUsername(subscriberDto.getUser());
        System.out.println("query "+user.get().getSubscriber());

        if(user.get().getSubscriber() == null )
            return subscriberService.saveSubscriber(subscriberDto.getSubscriber(), user.get());

        return  null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Subscriber editSubscriber(@RequestBody SubscriberDTO subscriberDto)  {

        Optional<User> user = userRepository.findByUsername(subscriberDto.getUser());
        return subscriberService.editSubscriber(subscriberDto.getSubscriber(), user.get());


    }

    @RequestMapping(value = "/delete/{subscriberId}", method = RequestMethod.DELETE)
    public void deleteSubscriber( @PathVariable String subscriberId)  {

        System.out.println(subscriberId);
        subscriberService.deleteSubscriber(subscriberId);

    }

}
