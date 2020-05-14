package com.sofrecom.elasticsearch.service;


import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.model.User;
import com.sofrecom.elasticsearch.repository.SubscriberRepository;
import com.sofrecom.elasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    UserRepository userRepository;

    public Subscriber saveSubscriber(Subscriber subscriber, User user) {

        Subscriber subscriber1 = subscriberRepository.save(subscriber);
        user.setSubscriber(subscriber1);
        userRepository.save(user);
        return subscriber1 ;

    }


    public Subscriber editSubscriber(Subscriber editSubscriber, User user) {


        Optional<User> user1 = userRepository.findByUsername(user.getUsername());
        User editedUser =  user1.get();
        editedUser.setSubscriber(editSubscriber);
        userRepository.save(editedUser);
        return subscriberRepository.save(editSubscriber);


    }

    public void deleteSubscriber(String subscriberId) {

        subscriberRepository.deleteById(subscriberId);
        User user = userRepository.findBySubscriberId(subscriberId);
        user.setSubscriber(null);
        userRepository.save(user);
    }
}
