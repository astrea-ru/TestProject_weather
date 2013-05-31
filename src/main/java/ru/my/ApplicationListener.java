package ru.my;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import ru.my.service.scheduler.IWeatherScheduledService;
import ru.my.service.scheduler.WeatherScheduledService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener  extends GuiceServletContextListener {

    IWeatherScheduledService service=null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        service = getInjector().getInstance(IWeatherScheduledService.class);
        service.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (service!=null)
            service.stop();
        super.contextDestroyed(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ApplicationConfiguration(), new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/").with(WeatherServlet.class);
            }
        });
    }
}
