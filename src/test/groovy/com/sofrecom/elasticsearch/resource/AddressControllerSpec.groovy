package com.sofrecom.elasticsearch.resource


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import com.fasterxml.jackson.databind.ObjectMapper
import com.sofrecom.elasticsearch.controller.AddressController
import com.sofrecom.elasticsearch.model.Address
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
class AddressControllerSpec extends Specification {

    //declare variables
      AddressController addressController
      MockMvc mockMvc
      ObjectMapper mapper = new ObjectMapper()
      def requestUri = '/address/'

      Address address1
      Address address2
      String address1JsonString
      String address2JsonString

      void setup(){
          addressRepository = Mock(AddressRepository)
          addressController = new AddressController()
          mockMvc = MockMvcBuilders
                  .standaloneSetup(addressController)
                  .alwaysDo(MockMvcResultHandlers.print())
                  .build()

          address1 = new Address([id:1, city:"Lyon", street:"Quartier Les Halles, Lyon 1er Arrondissemen",
                                  zipCode:1000, latitude:"48.8633", longitude:"2.34837"])

          address2 = new Address([id:2, city:"Lyon", street:"Quartier Les Halles, Lyon 2eme Arrondissemen",
                                  zipCode:1111, latitude:"48.8633", longitude:"2.34837"])

          address1JsonString = mapper.writeValueAsString(address1)
          address2JsonString = mapper.writeValueAsString(address2)
      }


   /*  void 'save address'() {
          given:
          address1 = new Address([id:1, city:"Lyon", street:"Quartier Les Halles, Lyon 1er Arrondissemen",
                                  zipCode:1000, latitude:"48.8633", longitude:"2.34837"])
          address1JsonString = mapper.writeValueAsString(address1)

          1 * addressRepository.save(address1) >> null

          expect:
          mockMvc.perform(MockMvcRequestBuilders
                  .post(requestUri+'/add').contentType(MediaType.APPLICATION_JSON).content(address1JsonString))
                  .andExpect(MockMvcResultMatchers.status().isOk())
      }
*/


    void 'get address'() {

        given:
            def response = mockMvc.perform(MockMvcRequestBuilders
                .get("/address/$id"))
                .andReturn().response

            def content = new JsonSlurper().parseText(response.contentAsString)


        expect:
            content.city == result
        where:
            id || result
            "73" || "Paris"
    }

    def "test 4"() {
        when:
        def response = mockMvc.perform(get("/address/75"))

        then:
        response.andExpect(status().isOk())

        //response.andReturn().response.contentAsString == "hello spock"
    }

    void 'get customer returns a single customer'() {
        expect: "Status is 200 and the response is 'Hello world!'"
        mockMvc.perform(get("/address/80"))
                .andExpect(status().isOk())
    }


}
