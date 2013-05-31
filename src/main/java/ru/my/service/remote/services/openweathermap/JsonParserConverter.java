package ru.my.service.remote.services.openweathermap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.WeatherInfo;

public class JsonParserConverter implements IConverter{
    @Override
    public WeatherInfo convert(String response) throws Exception{
        WeatherInfo info = new WeatherInfo();
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(response);
            JSONObject jsonObj = (JSONObject) obj;

            JSONObject mainJsonObject = (JSONObject) jsonObj.get("main");
            info.setAtmosphericPressure(new Long(mainJsonObject.get("pressure").toString()));
            info.setHumidity(new Long(mainJsonObject.get("humidity").toString()));
            Double temperatura = Double.valueOf(mainJsonObject.get("temp").toString()) - 273.0;
            info.setTemperatura(temperatura.longValue());

            JSONArray ja = (JSONArray)jsonObj.get("weather");
            info.setWeather( ((JSONObject)ja.get(0)).get("description").toString());

            return info;
        }catch (Exception e){
            throw new ParseException(e);
        }
    }
}
