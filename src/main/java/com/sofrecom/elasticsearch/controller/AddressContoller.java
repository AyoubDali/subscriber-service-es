package com.sofrecom.elasticsearch.controller;

import com.sofrecom.elasticsearch.model.Address;
import com.sofrecom.elasticsearch.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")

public class AddressContoller {

    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public Address addAdderss(@RequestBody Address address){

        addressService.addAddress(address);
        return address;
    }
}
