package com.sofrecom.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.sofrecom.elasticsearch.model.*;
import com.sofrecom.elasticsearch.repository.*;
import com.sofrecom.elasticsearch.resource.Config;
import com.sofrecom.elasticsearch.service.NextSequenceService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
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
    private SubscriberRepository subscriberRepository;

    @Autowired
    NextSequenceService nextSequenceService;
    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws ParseException, IOException {


        Faker faker = new Faker(new Locale("en"));
       // saveSubscriber(faker);
    }





    private void saveSubscriber(Faker faker) throws ParseException, IOException {


        ArrayList<String> professionTypeList = new ArrayList<String>();
        professionTypeList.add("Restaurant");
        professionTypeList.add("Pharmacie");
        professionTypeList.add("Avocat");
        professionTypeList.add("Médecin");
        professionTypeList.add("Vetements femmes");
        professionTypeList.add("Vetements hommes");
        professionTypeList.add("Musée");
        professionTypeList.add("Opticien");
        professionTypeList.add("Coiffeur");
        professionTypeList.add("Hotel");



        for (int i = 0; i < 20; i++) {
            String name = faker.name().fullName();
            int phone1 = (int) faker.number().randomNumber(8, true);
            int phone2 = (int) faker.number().randomNumber(8, true);
            int phone3 = (int) faker.number().randomNumber(8, true);
            String email = name.replaceAll("\\s+","") + "@gmail.com";
            //String websiteUrl = "www." + name.replaceAll("\\s+","") + ".fr";
            Operator operator = new Operator();
            operator.setName("Orange");
            ProfessionType professionType = new ProfessionType();
            professionType.setCategory("professionnel");
            professionType.setType("resto");
//            professionType.setType(professionTypeList.get((int)(Math.random() * 10)));
            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(email);
            subscriber.setName(name);
            subscriber.setOperator(operator);
            subscriber.setPhoneNumber(phone1);
            subscriber.setPhoneNumber2(phone2);
            subscriber.setPhoneNumber3(phone3);
            subscriber.setProfessionType(professionType);
           // subscriber.setWebsiteUrl(websiteUrl);
            subscriber.setAddressSet(saveAddress(faker));
            subscriber.setLegalInformation(saveLegalInformation(faker));
            subscriber.setOpeningTimeSet(saveOpeningTime());
            //subscriber.setId(nextSequenceService.getNextSequence("customSequences"));
           // subscriberRepository.save(subscriber);


            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(subscriber);
            System.out.println(jsonInString);


        }

    }

    private ArrayList<Address> saveAddress(Faker faker) throws ParseException {

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Paris");
        cities.add("Lyon");
        cities.add("nice");
        cities.add("marseille");

        String city = cities.get((int)(Math.random() * 4));
        String uri="https://nominatim.openstreetmap.org/search.php?format=json&q="+city;
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);
        //System.out.println(result);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(result);


        JSONObject item = new JSONObject((Map<String, ?>) json.get(0));
        String lat = (String) item.get("lat");
        String lon = (String) item.get("lon");
        Object id = item.get("place_id");
        Object street = item.get("display_name");
        int zipCode = 75001;
        //System.out.println(id.toString());
        //System.out.println(lat);
        Address address = new Address();
        address.setZipCode(zipCode);
        address.setStreet(street.toString());
        address.setCity(city);
        address.setLatitude(lat);
        address.setLongitude(lon);
        ArrayList<Address> addressSet = new ArrayList<>() ;

        addressSet.add(address);
        return addressSet;
        }


    private LegalInformation saveLegalInformation(Faker faker) {

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
        return li ;


    }


    private ArrayList<OpeningTime> saveOpeningTime()  {

        List<String> dayList = new ArrayList<String>();
        dayList.add("Lundi");
        dayList.add("Mardi");
        dayList.add("Mercredi");
        dayList.add("Jeudi");
        dayList.add("Vendredi");
        dayList.add("Samedi");
        dayList.add("Dimanche");
        ArrayList<OpeningTime>openingSet = new ArrayList<OpeningTime>();

            for (int i = 0; i < 7; i++) {
                String day = dayList.get(i);

                OpeningTime opening = new OpeningTime();
                opening.setClosing("18:00");
                opening.setOpening("08:00");
                opening.setDay(day);
                openingSet.add(opening);
            }

            return openingSet;

    }

}




