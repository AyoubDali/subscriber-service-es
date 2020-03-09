package com.sofrecom.elasticsearch.model;


import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String companyName;
    private double siren;
    private int nic;
    private double siret;
    private String ape;
    private String tva;
    private String description;
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

    @OneToOne(mappedBy = "legalInformation")
    private Subscriber subscriber;
}
