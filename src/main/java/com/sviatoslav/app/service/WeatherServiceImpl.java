package com.sviatoslav.app.service;

import com.sun.org.apache.regexp.internal.RE;
import com.sviatoslav.app.model.Statistic;
import com.sviatoslav.app.model.Weather;
import com.sviatoslav.app.repository.WeatherRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class WeatherServiceImpl implements WeatherService{

    private final static Logger LOGGER = Logger.getLogger(WeatherService.class);

    @Value("${app.city}")
    private String city;

    @Value("${app.id}")
    private String appId;

    @Value("${app.openWeatherMapURL}")
    private String weatherURL;

    @Autowired
    private JsonParserService jsonParserService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Weather getWeather(String city){
        return getWeatherFromJson(getJsonFromServer(city));
    }

    private Weather getWeatherFromJson(String json){
        return jsonParserService.getWeatherFromJson(json);
    }

    private String getJsonFromServer(String city) {
        return restTemplate.getForObject(weatherURL + city + "&APPID="+ appId + "&units=metric", String.class);
    }

    @Override
    public Statistic getStatisticFromDatabase(Long dateFrom, Long dateTo){

        Statistic statistic = new Statistic();
        statistic.setAvgTemperature(weatherRepository.getAverageTemperatureForPeriod(dateFrom, dateTo));
        statistic.setMaxTemperature(weatherRepository.getMaxTemperatureForPeriod(dateFrom, dateTo));
        statistic.setMinTemperature(weatherRepository.getMinTemperatureForPeriod(dateFrom, dateTo));

        LOGGER.info("Got statistic : "+statistic+" from database");
        return  statistic;
    }

    @Override
    public Weather getWeatherFromDatabase(){
        return weatherRepository.getCurrentWeather();
    }



    @Scheduled(fixedDelay = 600000)
    public void collectWeather(){
        Weather weather = getWeather(city);
        weatherRepository.save(weather);

        LOGGER.info("Saved weather: "+ weather+" to database");
    }

}
