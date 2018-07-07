package com.sviatoslav.app.controller;

import com.sviatoslav.app.model.Statistic;
import com.sviatoslav.app.model.Weather;
import com.sviatoslav.app.service.WeatherService;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WeatherController {

    final static Logger LOGGER = Logger.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/weather")
    public ModelAndView getView(){
        LOGGER.info("Received /weather request");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/weather/current")
    public Weather getCurrentWeather(){
        LOGGER.info("Received /weather/current request");
        return weatherService.getWeatherFromDatabase();
    }

    @GetMapping("/weather/statistic")
    public Statistic getStatistic(@RequestParam(value = "timestamp1") Long dateFrom,
                                  @RequestParam(value = "timestamp2") Long dateTo){

        LOGGER.info("Received /weather/statistic request with parameters timestamp1 = "+dateFrom+" and timestamp2 = "+dateTo);

        if (dateFrom > dateTo)
            return weatherService.getStatisticFromDatabase(dateTo, dateFrom);
        else
            return weatherService.getStatisticFromDatabase(dateFrom, dateTo);

    }



}
