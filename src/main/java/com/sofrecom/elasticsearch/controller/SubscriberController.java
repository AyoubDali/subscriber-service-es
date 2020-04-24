package com.sofrecom.elasticsearch.controller;

import com.sofrecom.elasticsearch.exception.NoDataFoundException;
import com.sofrecom.elasticsearch.model.Subscriber;
import com.sofrecom.elasticsearch.payload.request.SignupRequest;
import com.sofrecom.elasticsearch.repository.SubscriberRepository;
import com.sofrecom.elasticsearch.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/subscriber")
@CrossOrigin
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;
    @Autowired
    SubscriberRepository subscriberRepository;



    @GetMapping("/")
    public List<Subscriber> getAllSubscribers() throws IOException, NoDataFoundException {

        return subscriberService.findAll();
    }

    @GetMapping("/search/{query}")
    public List<Subscriber> searchSubscriber(@PathVariable String query) throws IOException, NoDataFoundException {

        System.out.println("request found");
        return subscriberService.searchForSubscriber(query);
    }


    @PostMapping("/search")
    public ModelAndView searchForSubscriber(@RequestParam String query) throws IOException, NoDataFoundException {

        System.out.println("query"+query);
        List<Subscriber> searchResult = subscriberService.searchForSubscriber(query);
        ModelAndView modelAndView = new ModelAndView("Listings");
        modelAndView.addObject("searchResult", searchResult);
        //System.out.println(searchResult.get(0).id);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView getSingleListingPage(@PathVariable String id) throws IOException {

        ModelAndView modelAndView = new ModelAndView("single_listing");
        Subscriber subscriber = subscriberService.getSubscriberById(id);
        modelAndView.addObject("subscriber", subscriber);
//        System.out.println("type "+subscriber.getName());
        return modelAndView;
    }


    //@PostMapping("/add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveSubscriber(@RequestBody Subscriber subscriber) throws IOException, NoDataFoundException {

        System.out.println("query "+subscriber.getOperator().getName());

        subscriberRepository.save(subscriber);
        return "received";
    }
}
