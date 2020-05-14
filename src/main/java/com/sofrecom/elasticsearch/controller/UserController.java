package com.sofrecom.elasticsearch.controller;


import com.github.javafaker.Bool;
import com.google.gson.JsonObject;
import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.model.SubscriberDTO;
import com.sofrecom.elasticsearch.model.User;
import com.sofrecom.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/isSubscribed/{user}", method = RequestMethod.GET)
    public Subscriber isSubscribed( @PathVariable String user)  {

        System.out.println(user);
        return  userService.isSubscribed(user);
    }
}
