package com.sofrecom.elasticsearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
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

    @OneToMany(mappedBy="operator")
    private Set<Subscriber> subscribersSet;

    public Operator(int i) {
        id=i ;
    }
}
