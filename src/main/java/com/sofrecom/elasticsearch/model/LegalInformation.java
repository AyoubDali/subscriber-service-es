package com.sofrecom.elasticsearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class LegalInformation {



    private String companyName;
    private double siren;
    private int nic;
    private double siret;
    private String ape;
    private String tva;
    private String description;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSiren() {
        return siren;
    }

    public void setSiren(double siren) {
        this.siren = siren;
    }

    public int getNic() {
        return nic;
    }

    public void setNic(int nic) {
        this.nic = nic;
    }

    public double getSiret() {
        return siret;
    }

    public void setSiret(double siret) {
        this.siret = siret;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//    public Subscriber getSubscriber() {
//        return subscriber;
//    }
//
//    public void setSubscriber(Subscriber subscriber) {
//        this.subscriber = subscriber;
//    }
}
