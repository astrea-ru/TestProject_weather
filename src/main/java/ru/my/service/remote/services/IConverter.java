package ru.my.service.remote.services;


/**
 * Интерфейс определяющий конвертр, для получения нжного объект по ответу от удаленного сервиса
 */
public interface IConverter {
    WeatherInfo convert(String response) throws Exception;
}
