package com.sofrecom.elasticsearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String day;
    private LocalTime opening;
    private LocalTime closing;
    private Date created_at;
    private Date updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Subscriber subscriber;
}
