package com.sofrecom.elasticsearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SearchController {


    @GetMapping("/")
    public ModelAndView getIndexPage() {

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }



}
