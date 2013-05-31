package ru.my.service.remote.services;

/**
 DTO для хранения информации о погоде
 */
public class WeatherInfo {
    private Long temperatura; //температура, должна быть в цельсиях
    private Long humidity; //влажность в %
    private Long atmosphericPressure; //атмосферное давление в мм. рт.ст.
    private String weather; //тип погоды

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Long getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(Long atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String toString(){
        return String.format("[%d]:[%d]:[%d]:[%s]", temperatura, humidity, atmosphericPressure, weather);
    }
}
