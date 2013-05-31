package ru.my;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import ru.my.database.DatabaseService;
import ru.my.database.IDatabaseService;
import ru.my.service.remote.IClient;
import ru.my.service.remote.RestClient;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.IRemoteWeatherService;
import ru.my.service.remote.services.openweathermap.JsonConverter;
import ru.my.service.remote.services.openweathermap.OpenWeatherMapService;
import ru.my.service.remote.services.yahooweather.YahooWeatherService;
import ru.my.service.remote.services.yahooweather.YahooXmlConverter;
import ru.my.service.scheduler.IWeatherScheduledService;
import ru.my.service.scheduler.WeatherScheduledService;
import ru.my.service.scheduler.WeatherSchedulerRunnable;

public class ApplicationConfiguration extends AbstractModule {
    @Override
    protected void configure() {
        bind(IClient.class)
                 .to(RestClient.class);

        bind(IConverter.class)
                 .annotatedWith(Names.named("YahooConverter"))
                 .to(YahooXmlConverter.class);
        bind(IConverter.class)
                .annotatedWith(Names.named("OpenWeatherMap"))
                .to(JsonConverter.class);


        bind(Runnable.class)
                .to(WeatherSchedulerRunnable.class);

        Multibinder<IRemoteWeatherService> actionBinder = Multibinder.newSetBinder(binder(), IRemoteWeatherService.class);
        actionBinder.addBinding().to(YahooWeatherService.class);
        actionBinder.addBinding().to(OpenWeatherMapService.class);

        bind(IDatabaseService.class)
                .to(DatabaseService.class);

        bind(IWeatherScheduledService.class)
                .to(WeatherScheduledService.class);
    }
}
