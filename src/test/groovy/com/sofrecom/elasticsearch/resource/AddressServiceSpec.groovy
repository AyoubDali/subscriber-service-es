package com.sofrecom.elasticsearch.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.sofrecom.elasticsearch.model.Address

import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

class AddressServiceSpec extends Specification {

//    //declare variables
//    AddressService addressService
//    MockMvc mockMvc
//    AddressRepository addressRepository
//    ObjectMapper mapper = new ObjectMapper()
//    def requestUri = '/address/'
//
//    Address address1
//    Address address2
//    String address1JsonString
//    String address2JsonString
//
//    void setup() {
//        System.out.println("setup")
//        addressRepository = Mock(AddressRepository)
//        addressService = new AddressService([addressRepository: addressRepository])
//        address1 = new Address([id     : 1, city: "Lyon", street: "Quartier Les Halles, Lyon 1er Arrondissemen",
//                                zipCode: 1000, latitude: "48.8633", longitude: "2.34837"])
//
//        //  address1JsonString = mapper.writeValueAsString(address1)
//
//    }


    void 'get customer returns a single customer'() {

        when:
        Address res = addressService.findById(80)

        then:
        res != null
    }

}
