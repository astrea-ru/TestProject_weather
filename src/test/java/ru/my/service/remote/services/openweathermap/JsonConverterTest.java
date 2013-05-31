package ru.my.service.remote.services.openweathermap;

import org.junit.Assert;
import org.junit.Test;
import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.ReadingFilesWithResponse;
import ru.my.service.remote.services.WeatherInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonConverterTest {
    private String INCORRECT_JSON="{\"weather\":{\"t\":5,\"p\":754}}";

    @Test
    public void convert_returns_WeatherInfo_if_response_is_correct() throws Exception {
        String correct_response= ReadingFilesWithResponse.readResponseFromFile("/OpenWeatherMapCorrectJson.json");
        
        IConverter converter = new JsonConverter();
        WeatherInfo weatherInfo = converter.convert(correct_response);

        Assert.assertEquals(new Long(15), weatherInfo.getTemperatura());
        Assert.assertEquals(new Long(1022), weatherInfo.getAtmosphericPressure());
        Assert.assertEquals(new Long(72), weatherInfo.getHumidity());
        Assert.assertEquals("пасмурно", weatherInfo.getWeather());
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_not_correct() throws Exception{
        IConverter converter = new JsonConverter();
        converter.convert(INCORRECT_JSON);
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_null() throws Exception{
        IConverter converter = new JsonConverter();
        converter.convert(null);
    }
}
