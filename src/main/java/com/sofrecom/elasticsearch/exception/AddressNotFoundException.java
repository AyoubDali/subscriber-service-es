package com.sofrecom.elasticsearch.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(int id) {
        super(String.format("Address with Id %d not found", id));

    }
}
