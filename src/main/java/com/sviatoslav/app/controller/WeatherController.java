package com.sviatoslav.app.controller;

import com.sviatoslav.app.model.Statistic;
import com.sviatoslav.app.model.Weather;
import com.sviatoslav.app.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @RequestMapping("/weather")
    public ModelAndView getView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/weather/current")
    public Weather getCurrentWeather(){
        return weatherRepository.getCurrentWeather();
    }

    @GetMapping("/weather/statistic")
    public Statistic getStatistic(@RequestParam(value = "timestamp1") Long date1, @RequestParam(value = "timestamp2") Long date2){

        if (date1 > date2)
            return getStatisticFromDatabase(date2, date1);
        else
            return getStatisticFromDatabase(date1,date2);

    }

    private Statistic getStatisticFromDatabase(Long date1, Long date2){

        Statistic statistic = new Statistic();
        statistic.setAvgTemperature(weatherRepository.getAverageTemperatureForPeriod(date1, date2));
        statistic.setMaxTemperature(weatherRepository.getMaxTemperatureForPeriod(date1, date2));
        statistic.setMinTemperature(weatherRepository.getMinTemperatureForPeriod(date1, date2));

        return  statistic;
    }

}
