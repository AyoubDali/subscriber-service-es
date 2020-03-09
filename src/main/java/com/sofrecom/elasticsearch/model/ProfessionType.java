package com.sofrecom.elasticsearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String category;
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

    @OneToMany(mappedBy="professionType")
    private Set<Subscriber> subscribersSet;

    public ProfessionType(int i) {
        id = i;
    }
}
