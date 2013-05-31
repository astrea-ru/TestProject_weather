package ru.my.service.scheduler;

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

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherScheduledService implements IWeatherScheduledService{

    private ScheduledExecutorService worker=null;
    private Runnable scheduledService=null;

    @Inject
    protected void setScheduledService(Runnable service){
        this.scheduledService = service;
    }

    @Override
    public void start() {
        if (worker!= null)
            return;
        worker = Executors.newScheduledThreadPool(1);
        worker.scheduleWithFixedDelay(scheduledService, 0,Settings.getInstance().getTimeOut(), TimeUnit.MINUTES);
    }

    @Override
    public void stop() {
        if (worker!=null){
            worker.shutdown();
            worker=null;
        }
    }
}
