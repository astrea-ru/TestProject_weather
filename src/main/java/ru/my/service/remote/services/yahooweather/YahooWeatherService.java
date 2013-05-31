package ru.my.service.remote.services.yahooweather;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.my.Settings;
import ru.my.service.remote.IClient;
import ru.my.service.remote.services.AbstractRemoteWeatherService;
import ru.my.service.remote.services.IConverter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class YahooWeatherService extends AbstractRemoteWeatherService {

    private String address = Settings.getInstance().getUrlForYahooWeatherService();
    private String name = "Yahoo Weather";

    @Inject
    public YahooWeatherService(IClient client, @Named("YahooConverter") IConverter converter){
        super(client,converter);
    }

    protected String buildFullAddress(String town) throws UnsupportedEncodingException {
        return address+ URLEncoder.encode(town, "UTF-8");
    }

    public String getName(){
        return Settings.getInstance().getYahooWeatherServiceName();
    }
}
