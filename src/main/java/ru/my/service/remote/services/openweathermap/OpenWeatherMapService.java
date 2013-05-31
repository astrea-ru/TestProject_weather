package ru.my.service.remote.services.openweathermap;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.my.Settings;
import ru.my.service.remote.IClient;
import ru.my.service.remote.services.AbstractRemoteWeatherService;
import ru.my.service.remote.services.IConverter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OpenWeatherMapService extends AbstractRemoteWeatherService {

    private String address= Settings.getInstance().getUrlForOpenWeatherMapService();
    private String name = "Open Weather Map";

    @Inject
    public OpenWeatherMapService(IClient client,@Named("OpenWeatherMap") IConverter converter){
        super(client, converter);
    }
    
    protected String buildFullAddress(String town) throws UnsupportedEncodingException {
         return address+ URLEncoder.encode(town, "UTF-8");
    }

    public String getName(){
        return Settings.getInstance().getOpenWeatherMapServiceName();
    }
}
