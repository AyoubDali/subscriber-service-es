package com.sofrecom.elasticsearch;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.github.javafaker.PhoneNumber;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.sofrecom.elasticsearch.model.*;
import com.sofrecom.elasticsearch.repository.*;
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
public class ElasticsearchApplication implements ApplicationRunner {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private LegalInformationRepository legalInformationRepository;

    @Autowired
    private OpeningTimeRepository openingTimeRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {

      //  Faker faker = new Faker(new Locale("fr"));

        //saveLegalInformation(faker);
        //  saveSubscriber(faker);
        // saveAddress(faker);
        // saveOpeningTime();
        //System.out.println("end");



    }

    private void saveOpeningTime() throws java.text.ParseException {

        List<String> dayList = new ArrayList<String>();
        dayList.add("Lundi");
        dayList.add("Mardi");
        dayList.add("Mercredi");
        dayList.add("Jeudi");
        dayList.add("Vendredi");
        dayList.add("Samedi");
        dayList.add("Dimanche");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");

        for (int j = 59; j < 69; j++) {
            for (int i = 0; i < 7; i++) {
                String day = dayList.get(i);
              //  String op = "08:00 AM";
               // String cl = "06:00 PM";
                LocalTime op = LocalTime.parse("08:00");
                LocalTime cl = LocalTime.parse("18:00");

               // Date opp = dateFormat.parse(op);
                //Date closing =  dateFormat.parse(cl);
                Subscriber subscriber = subscriberRepository.findById(j);
                OpeningTime opening = new OpeningTime();
                opening.setClosing(cl);
                opening.setOpening(op);
                opening.setDay(day);
                opening.setSubscriber(subscriber);
                openingTimeRepository.save((opening));
            }
        }

    }
    private void saveAddress(Faker faker) throws ParseException {

        String uri="https://nominatim.openstreetmap.org/search.php?format=json&q=75001";
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);
            for (int i=0;i<10;i++){

                JSONObject item = new JSONObject((Map<String, ?>) json.get(i));
                String lat = (String) item.get("lat");
                String lon = (String) item.get("lon");
                Object id = item.get("place_id");
                Object street = item.get("display_name");
                int zipCode = 75001;
                String city = "Paris";
                System.out.println(id.toString());
                System.out.println(lat);
                Address address = new Address();
                address.setZipCode(zipCode);
                address.setStreet(street.toString());
                address.setCity(city);
                address.setLatitude(lat);
                address.setLongitude(lon);
                Subscriber subscriber = subscriberRepository.findById(i+59);
                address.setSubscriber(subscriber);
                addressRepository.save(address);
        }

    }



    private void saveSubscriber(Faker faker) {

        for (int i = 49; i < 59; i++) {
            String name = faker.name().fullName();
            int phone1 = (int) faker.number().randomNumber(8,true);
            int phone2 = (int) faker.number().randomNumber(8,true);
            int phone3 = (int) faker.number().randomNumber(8, true);
            String email = name.trim() + "@gmail.com";
            String websiteUrl = "www." + name.trim() + ".fr";
            LegalInformation li = legalInformationRepository.findById(i);
            System.out.println(li.getId());
            ProfessionType profession = new ProfessionType((int)(Math.random() * 13)+1);
            Operator operator = new Operator((int)(Math.random() * 3)+1);
            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(email);
            subscriber.setLegalInformation(li);
            subscriber.setName(name);
            subscriber.setOperator(operator);
            subscriber.setPhoneNumber(phone1);
            subscriber.setPhoneNumber2(phone2);
            subscriber.setPhoneNumber3(phone3);
            subscriber.setProfessionType(profession);
            subscriber.setWebsiteUrl(websiteUrl);
            subscriberRepository.save(subscriber);
        }

    }


    private void saveLegalInformation(Faker faker) {

        for (int i = 0; i < 10; i++) {
            String company = faker.company().name();
            double siren = faker.number().randomNumber(9, true);
            int nic = (int) faker.number().randomNumber(5, true);
            double siret = faker.number().randomNumber(14, true);
            double ape = faker.number().randomDouble(2, 10, 99);

            int tva = (int) faker.number().randomNumber(10, true);
            String tvaFr = "FR" + tva;
            String description = faker.lorem().paragraph();
            LegalInformation li = new LegalInformation();
            li.setApe(String.valueOf(ape));
            li.setDescription(description);
            li.setNic(nic);
            li.setSiren(siret);
            li.setSiret(siret);
            li.setTva(tvaFr);
            li.setCompanyName(company);
            legalInformationRepository.save(li);
        }

    }
}
