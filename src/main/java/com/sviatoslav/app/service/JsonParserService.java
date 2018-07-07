package com.sviatoslav.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sviatoslav.app.model.Weather;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonParserService {

private final static Logger LOGGER = Logger.getLogger(JsonParserService.class);

    public Weather getWeatherFromJson(String json){

        Weather weather = new Weather();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            //get date
            JsonNode dateNode = rootNode.path("dt");
            Long date = dateNode.asLong();

            //get city
            JsonNode cityNode = rootNode.path("name");
            String city = cityNode.asText();

            //get temperature, pressure, humidity
            JsonNode mainNode = rootNode.path("main");

            JsonNode temperatureNode = mainNode.path("temp");
            Double temperature = temperatureNode.asDouble();

            JsonNode pressureNode = mainNode.path("pressure");
            String pressure = pressureNode.asText();

            JsonNode humidityNode = mainNode.path("humidity");
            String humidity = humidityNode.asText();

            //get description
            JsonNode descriptionNode = rootNode.findPath("description");
            String description = descriptionNode.asText();

            weather.setDate(date);
            weather.setCity(city);
            weather.setTemperature(temperature);
            weather.setPressure(pressure);
            weather.setHumidity(humidity);
            weather.setDescription(description);

        }
        catch (IOException e){
            LOGGER.error("Exception is throwed : " + e);
        }

        return weather;

    }
}
