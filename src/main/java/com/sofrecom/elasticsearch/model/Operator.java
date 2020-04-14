package com.sofrecom.elasticsearch.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class Operator {


    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
