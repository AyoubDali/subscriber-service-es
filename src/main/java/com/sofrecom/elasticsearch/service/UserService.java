package com.sofrecom.elasticsearch.service;

import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.model.User;
import com.sofrecom.elasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    public Subscriber isSubscribed(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        if(user.get().getSubscriber() != null) {
            return user.get().getSubscriber();
        }

        return null;
    }
}
