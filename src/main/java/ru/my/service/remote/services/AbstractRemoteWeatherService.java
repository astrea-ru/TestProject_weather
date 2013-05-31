package ru.my.service.remote.services;

import com.google.inject.Inject;
import ru.my.service.remote.IClient;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public abstract class AbstractRemoteWeatherService implements IRemoteWeatherService{

    protected IClient client;
    protected IConverter converter;


    public AbstractRemoteWeatherService(IClient client, IConverter converter){
        this.client = client;
        this.converter = converter;
    }

    @Override
    public WeatherInfo getWeatherInfo(String town) throws Exception {
        String fullAddress = buildFullAddress(town);
        String response = client.getResponse(fullAddress);
        return converter.convert(response);
    }

    protected abstract String buildFullAddress(String town) throws Exception;
}
