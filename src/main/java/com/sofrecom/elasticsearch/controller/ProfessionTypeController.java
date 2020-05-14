package com.sofrecom.elasticsearch.controller;


import com.sofrecom.elasticsearch.model.Operator;
import com.sofrecom.elasticsearch.model.ProfessionType;
import com.sofrecom.elasticsearch.repository.OperatorRepository;
import com.sofrecom.elasticsearch.repository.ProfessionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ProfessionTypeController {

    @Autowired
    ProfessionTypeRepository professionTypeRepository;

    @GetMapping("/api/professionType/all")
    public List<ProfessionType> getAllProfessionTypes() {


        return professionTypeRepository.findAll();
    }


}
