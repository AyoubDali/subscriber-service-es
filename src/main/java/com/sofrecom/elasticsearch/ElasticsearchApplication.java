package com.sofrecom.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.sofrecom.elasticsearch.model.*;
import com.sofrecom.elasticsearch.repository.*;
import com.sofrecom.elasticsearch.service.NextSequenceService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutput;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
public class ElasticsearchApplication implements ApplicationRunner {

    @Autowired
    OperatorRepository operatorRepository;
    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    ProfessionTypeRepository professionTypeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    NextSequenceService nextSequenceService;
    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws ParseException, IOException {


//        Operator operator = new Operator();
//        operator.setName("Orange");
//        operatorRepository.save(operator);
//        Operator operator2 = new Operator();
//        operator2.setName("France télécom");
//        operatorRepository.save(operator2);
//
//        ProfessionType professionType = new ProfessionType();
//        professionType.setType("Restaurant");
//        professionType.setCategory("professionnel");
//        professionTypeRepository.save(professionType);
//
//        ProfessionType professionType2 = new ProfessionType();
//        professionType2.setType("Pharmacie");
//        professionType2.setCategory("professionnel");
//        //System.out.println(roleRepository.findAll());
//        professionTypeRepository.save(professionType2);

    }





}




