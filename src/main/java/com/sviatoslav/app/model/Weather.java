package com.sviatoslav.app.model;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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

    public Weather(String city, String description, Double temperature, String humidity, String pressure, Long date) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.date = date;
    }

    public Weather(){
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", date=" + date +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }





}
