package com.sofrecom.elasticsearch.service;


import com.sofrecom.elasticsearch.model.Address;
import com.sofrecom.elasticsearch.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address addAddress(Address address){

        addressRepository.save(address);
        return address;
    }

}
