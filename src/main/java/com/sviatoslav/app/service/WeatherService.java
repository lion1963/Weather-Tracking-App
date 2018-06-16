package com.sviatoslav.app.service;

import com.sviatoslav.app.model.Statistic;
import com.sviatoslav.app.model.Weather;

public interface WeatherService {

    Weather getWeather(String city);

    Statistic getStatisticFromDatabase(Long dateFrom, Long dateTo);

    Weather getWeatherFromDatabase();

}

