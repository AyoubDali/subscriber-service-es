package com.sofrecom.elasticsearch.resource


import com.sofrecom.elasticsearch.model.Address
import com.sofrecom.elasticsearch.model.Subscriber
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class AddressControllerSpec extends Specification {



    void 'test get address return a valid address '(){

        given:
            RestTemplate restTemplate = new RestTemplate()
            Address result = restTemplate.getForObject("http://localhost:8080/address/80", Address.class)
        expect:
            result.city == 'Paris'

    }

    void 'test add address save an address '(){

        given:
            RestTemplate restTemplate = new RestTemplate()
            Address address1 = new Address([ city: "Lyon", street: "Quartier Les Halles, Lyon 1er Arrondissemen",
                                zipCode: 1000, latitude: "48.8633", longitude: "2.34837"])
            ResponseEntity<Address> result = restTemplate.postForEntity("http://localhost:8080/address/add", address1, Address.class)

        expect:
            result.body.zipCode == 1000

    }


    void 'test search for all subscribers returns non empty results '(){

        given:
            RestTemplate restTemplate = new RestTemplate()
            ArrayList<Subscriber> resultSet = restTemplate.getForObject("http://localhost:8080/subscriber/", ArrayList.class)

        expect:
            !resultSet.isEmpty()

    }


    void 'test search by existed subscriber phone-number returns non empty result '(){

        given:
            RestTemplate restTemplate = new RestTemplate()
            ArrayList<Subscriber> resultSet = restTemplate.getForObject("http://localhost:8080/subscriber/{query}", Subscriber.class)
        expect:
            !resultSet.isEmpty()

    }
}
