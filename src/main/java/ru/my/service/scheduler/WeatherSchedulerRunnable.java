package ru.my.service.scheduler;

import com.google.inject.Inject;
import ru.my.Settings;
import ru.my.database.DatabaseService;
import ru.my.database.IDatabaseService;
import ru.my.service.remote.RestClient;
import ru.my.service.remote.services.IRemoteWeatherService;
import ru.my.service.remote.services.WeatherInfo;
import ru.my.service.remote.services.openweathermap.JsonConverter;
import ru.my.service.remote.services.openweathermap.OpenWeatherMapService;
import ru.my.service.remote.services.yahooweather.YahooWeatherService;
import ru.my.service.remote.services.yahooweather.YahooXmlConverter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WeatherSchedulerRunnable implements Runnable{

    private List<IRemoteWeatherService> services;
    private IDatabaseService databaseService;

    @Inject
    protected void setListOfReamoteServices(Set<IRemoteWeatherService> services){
        this.services = new ArrayList<IRemoteWeatherService>(services);

    }

    @Inject
    protected void setDatabaseService(IDatabaseService databaseService){
        this.databaseService = databaseService;
    }


    @Override
    public void run() {
        for(int i=0; i< Settings.getInstance().getCityRU().length;i++){
            for (int j=0; j<services.size(); j++){
                try{
                    String city=Settings.getInstance().getCityRU()[i];
                    WeatherInfo info = services.get(j).getWeatherInfo(city);
                    databaseService.saveWeatherInfo(services.get(j).getName(),city,info);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
