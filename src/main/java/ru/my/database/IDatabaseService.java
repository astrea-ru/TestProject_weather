package ru.my.database;

import ru.my.database.tables.Weather;
import ru.my.service.remote.services.WeatherInfo;

import java.util.Date;
import java.util.List;

public interface IDatabaseService {
    
    void saveWeatherInfo(String serviceName, String city, WeatherInfo weatherInfo) throws Exception;

    List<Weather> searchWeather(String city, String serviceName, Date startDate, Date endDate) throws Exception;
}
