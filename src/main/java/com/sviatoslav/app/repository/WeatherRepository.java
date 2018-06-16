package com.sviatoslav.app.repository;

import com.sviatoslav.app.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {


    @Query("select w from Weather w where w.date = (SELECT MAX(w.date) from Weather w) and w.id = (SELECT MAX(w.id) from Weather w)")
    Weather getCurrentWeather();

    @Query("SELECT MAX(w.temperature) from Weather w where w.date between ?1 and ?2")
    Double getMaxTemperatureForPeriod(Long date1, Long date2);

    @Query("SELECT MIN(w.temperature) from Weather w where w.date between ?1 and ?2")
    Double getMinTemperatureForPeriod(Long date1, Long date2);

    @Query("SELECT AVG(w.temperature) from Weather w where w.date between ?1 and ?2")
    Double getAverageTemperatureForPeriod(Long date1, Long date2);
}
