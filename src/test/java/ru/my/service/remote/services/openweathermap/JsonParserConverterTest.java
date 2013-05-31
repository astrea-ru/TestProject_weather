package ru.my.service.remote.services.openweathermap;

import org.junit.Assert;
import org.junit.Test;
import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.ReadingFilesWithResponse;
import ru.my.service.remote.services.WeatherInfo;

public class JsonParserConverterTest {
    private String INCORRECT_JSON="{\"weather\":{\"t\":5,\"p\":754}}";
    private String correct="{\"coord\":{\"lon\":61.429722,\"lat\":55.154442},\"sys\":{\"country\":\"RU\",\"sunrise\":1369697116,\"sunset\":1369757877},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"пасмурно\",\"icon\":\"04d\"}],\"base\":\"global stations\",\"main\":{\"temp\":288.15,\"pressure\":1022,\"humidity\":72,\"temp_min\":288.15,\"temp_max\":288.15},\"wind\":{\"speed\":3,\"deg\":190},\"rain\":{\"3h\":2.5},\"clouds\":{\"all\":75},\"dt\":1369731600,\"id\":1508291,\"name\":\"Chelyabinsk\",\"cod\":200}";

    @Test
    public void convert_returns_WeatherInfo_if_response_is_correct() throws Exception {
        String correct_response= ReadingFilesWithResponse.readResponseFromFile("/OpenWeatherMapCorrectJson.json");

        IConverter converter = new JsonParserConverter();
        WeatherInfo weatherInfo = converter.convert(correct_response);

        Assert.assertEquals(new Long(15), weatherInfo.getTemperatura());
        Assert.assertEquals(new Long(1022), weatherInfo.getAtmosphericPressure());
        Assert.assertEquals(new Long(72), weatherInfo.getHumidity());
        Assert.assertEquals("пасмурно", weatherInfo.getWeather());
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_not_correct() throws Exception{
        IConverter converter = new JsonParserConverter();
        converter.convert(INCORRECT_JSON);
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_null() throws Exception{
        IConverter converter = new JsonParserConverter();
        converter.convert(null);
    }
}
