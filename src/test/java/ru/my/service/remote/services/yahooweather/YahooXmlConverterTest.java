package ru.my.service.remote.services.yahooweather;

import org.junit.Assert;
import org.junit.Test;
import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.ReadingFilesWithResponse;
import ru.my.service.remote.services.WeatherInfo;

//Добавить тесты с null!!! во все конвертеры
public class YahooXmlConverterTest {
    private String INCORRECT_XML="<weather><date>01.01.02</date><t>25</t><p>754</p><h>50</h></weather>";

    @Test
    public void convert_returns_WeatherInfo_if_response_is_correct() throws Exception {
        String correct_response= ReadingFilesWithResponse.readResponseFromFile("/YahooWeatherTest.xml");

        IConverter converter = new YahooXmlConverter();
        WeatherInfo weatherInfo = converter.convert(correct_response);

        Assert.assertEquals(new Long(15), weatherInfo.getTemperatura());
        Assert.assertEquals(new Long(1015), weatherInfo.getAtmosphericPressure());
        Assert.assertEquals(new Long(45), weatherInfo.getHumidity());
        Assert.assertEquals("ясно (день)", weatherInfo.getWeather());
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_not_correct() throws Exception{
        IConverter converter = new YahooXmlConverter();
        converter.convert(INCORRECT_XML);
    }

    @Test(expected = ParseException.class)
    public void convert_throws_ParseException_if_response_is_not_null() throws Exception{
        IConverter converter = new YahooXmlConverter();
        converter.convert(null);
    }
}
