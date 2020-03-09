package com.sofrecom.elasticsearch.model;


import com.github.javafaker.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int phoneNumber;
    private int phoneNumber2;
    private int phoneNumber3;
    private String email;
    private String websiteUrl;
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

    @OneToOne
    private LegalInformation legalInformation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProfessionType professionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Operator operator;

    @OneToMany(mappedBy="subscriber")
    private Set<Address> addressSet;

    @OneToMany(mappedBy="subscriber")
    private Set<OpeningTime> openingTimeSet;



}
