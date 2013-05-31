package ru.my.service.remote.services.openweathermap;

import com.google.gson.Gson;

import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.WeatherInfo;
import ru.my.service.remote.services.openweathermap.jsons.WeatherInCity;

public class JsonConverter implements IConverter{
    @Override
    public WeatherInfo convert(String response) throws Exception{
        try{
            Gson gson = new Gson();
            WeatherInCity obj = gson.fromJson(response, WeatherInCity.class);
            WeatherInfo info = new WeatherInfo();
            info.setAtmosphericPressure(obj.getMain().getPressure().longValue());
            info.setHumidity(obj.getMain().getHumidity().longValue());
            info.setTemperatura(obj.getMain().getTemp().longValue() - 273);
            info.setWeather(obj.getWeather().get(0).getDescription());
            return info;
        }catch (Exception e){
            throw new ParseException(e);
        }
    }
}
