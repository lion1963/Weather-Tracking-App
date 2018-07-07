package com.sviatoslav.app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;
    private String description;
    private Double temperature;
    private String humidity;
    private String pressure;
    private Long date;

}
