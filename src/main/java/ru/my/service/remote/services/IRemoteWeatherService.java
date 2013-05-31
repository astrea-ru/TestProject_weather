package ru.my.service.remote.services;

/**
 * Интерфейс для общения с удаленными сервисами погоды
 */
public interface IRemoteWeatherService {
    
    WeatherInfo getWeatherInfo(String town) throws Exception;
    
    String getName();
}
