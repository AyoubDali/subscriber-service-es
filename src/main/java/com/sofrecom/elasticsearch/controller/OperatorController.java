package com.sofrecom.elasticsearch.controller;

import com.sofrecom.elasticsearch.model.Operator;
import com.sofrecom.elasticsearch.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OperatorController {

    @Autowired
    OperatorRepository operatorRepository;

    @GetMapping("/api/operator/all")
    public List<Operator> getAllOperators() {

        return operatorRepository.findAll();
    }



}
