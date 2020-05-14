package com.sofrecom.elasticsearch.resource


import com.sofrecom.elasticsearch.model.Address
import com.sofrecom.elasticsearch.model.Subscriber
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class AddressControllerSpec extends Specification {



//    //declare variables
//      AddressController addressController
//      MockMvc mockMvc
//      ObjectMapper mapper = new ObjectMapper()
//      def requestUri = '/address/'
//
//      Address address1
//      Address address2
//      String address1JsonString
//      String address2JsonString
//
//      void setup(){
//          addressRepository = Mock(AddressRepository)
//          addressController = new AddressController()
//          mockMvc = MockMvcBuilders
//                  .standaloneSetup(addressController)
//                  .alwaysDo(MockMvcResultHandlers.print())
//                  .build()
//
//          address1 = new Address([id:1, city:"Lyon", street:"Quartier Les Halles, Lyon 1er Arrondissemen",
//                                  zipCode:1000, latitude:"48.8633", longitude:"2.34837"])
//
//          address2 = new Address([id:2, city:"Lyon", street:"Quartier Les Halles, Lyon 2eme Arrondissemen",
//                                  zipCode:1111, latitude:"48.8633", longitude:"2.34837"])
//
//          address1JsonString = mapper.writeValueAsString(address1)
//          address2JsonString = mapper.writeValueAsString(address2)
//      }
//
//
//   /*  void 'save address'() {
//          given:
//          address1 = new Address([id:1, city:"Lyon", street:"Quartier Les Halles, Lyon 1er Arrondissemen",
//                                  zipCode:1000, latitude:"48.8633", longitude:"2.34837"])
//          address1JsonString = mapper.writeValueAsString(address1)
//
//          1 * addressRepository.save(address1) >> null
//
//          expect:
//          mockMvc.perform(MockMvcRequestBuilders
//                  .post(requestUri+'/add').contentType(MediaType.APPLICATION_JSON).content(address1JsonString))
//                  .andExpect(MockMvcResultMatchers.status().isOk())
//      }
//*/

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
