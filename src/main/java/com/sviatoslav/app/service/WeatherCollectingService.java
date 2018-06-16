package com.sviatoslav.app.service;

import com.sviatoslav.app.model.Weather;
import com.sviatoslav.app.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class WeatherCollectingService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherService weatherService;

    @Value("${app.city}")
    private String city;

    @Scheduled(fixedDelay = 600000)
    public void collectWeather(){
        Weather weather = weatherService.getWeather(city);
        weatherRepository.save(weather);
    }

}

