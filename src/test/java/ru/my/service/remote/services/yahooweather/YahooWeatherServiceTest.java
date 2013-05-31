package ru.my.service.remote.services.yahooweather;

import org.junit.Assert;
import org.junit.Test;
import ru.my.Settings;
import ru.my.service.remote.IClient;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.WeatherInfo;

import java.net.URL;
import java.net.URLEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooWeatherServiceTest {

    @Test
    public void getWeatherInfo_returns_correct_response() throws Exception{
        String city="Челябинск";
        String response="RESPONSE";
        String address = Settings.getInstance().getUrlForYahooWeatherService()+ URLEncoder.encode(city, "UTF-8");
        IClient client = mock(IClient.class);
        when(client.getResponse(address)).thenReturn(response);
        IConverter converter = mock(IConverter.class);
        WeatherInfo weatherInfo = mock(WeatherInfo.class);
        when(converter.convert(response)).thenReturn(weatherInfo);

        YahooWeatherService service = new YahooWeatherService(client,converter);
        WeatherInfo result = service.getWeatherInfo(city);
        
        verify(client).getResponse(address);
        verify(converter).convert(response);
        Assert.assertEquals(weatherInfo, result);
    }
}
