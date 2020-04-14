package com.sofrecom.elasticsearch.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class OpeningTime {

    private String day;
    private String  opening;
    private String closing;



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }


//    public Subscriber getSubscriber() {
//        return subscriber;
//    }
//
//    public void setSubscriber(Subscriber subscriber) {
//        this.subscriber = subscriber;
//    }
}
