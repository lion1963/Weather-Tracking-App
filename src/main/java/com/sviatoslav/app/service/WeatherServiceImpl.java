package com.sviatoslav.app.service;

import com.sun.org.apache.regexp.internal.RE;
import com.sviatoslav.app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${app.id}")
    private String appId;

    @Autowired
    private JsonParserService jsonParserService;

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
        String json ="";

        Weather weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="+ city + "&APPID="+ appId + "&units=metric", Weather.class);

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ city + "&APPID="+ appId + "&units=metric");
            URLConnection urlConnection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                json += json.concat(inputLine);
            }
            in.close();
        }
        catch (IOException e){
            e.printStackTrace();//logger
        }
        return json;
    }


}
