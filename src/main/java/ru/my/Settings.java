package ru.my;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Класс синглтон для доступа к основным настройкам приложения
 */
public class Settings {
    private static final String SYSTEM_PROPERTIES_FILE_NAME = "/weather.service.properties";

    private final Properties properties;

    private static Settings instance = new Settings();

    private Settings() {
        properties = new Properties();
        InputStream inputStream = Settings.class.getResourceAsStream(SYSTEM_PROPERTIES_FILE_NAME);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static Settings getInstance(){
        return instance;
    }

    public String getUrlForOpenWeatherMapService(){
        return properties.getProperty("remote.services.OpenWeatherMap");
    }

    public String getUrlForYahooWeatherService(){
        return properties.getProperty("remote.services.YahooWeather");
    }
    
    public String[] getCityRU(){
        return properties.getProperty("towns.ru").toString().split(";");
    }

    public String[] getCityEN(){
        return properties.getProperty("towns.en").toString().split(";");
    }

    public long getTimeOut(){
        return new Long(properties.getProperty("weather.service.time_out.minutes"));
    }

    public String getYahooWeatherCloud(int id){
            return properties.getProperty("remote.services.YahooWeather.cloud."+id);
    }
    
    public String getYahooWeatherServiceName(){
        return properties.getProperty("remote.services.YahooWeather.name");
    }

    public String getOpenWeatherMapServiceName(){
        return properties.getProperty("remote.services.OpenWeatherMap.name");
    }
    
    public String[] getRemoteServiceNames(){
        String[] names= new String[2];
        names[0]=getOpenWeatherMapServiceName();
        names[1]=getYahooWeatherServiceName();
        return names;
    }
}
