package ru.my.database;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.hibernate.transform.ResultTransformer;
import ru.my.Settings;
import ru.my.database.tables.Weather;
import ru.my.service.remote.services.WeatherInfo;
import ru.my.exceptions.*;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DatabaseService implements IDatabaseService{

    private SessionFactory sessionFactory;

    public DatabaseService(){
        AnnotationConfiguration aconf = new AnnotationConfiguration();
        aconf.addAnnotatedClass(Weather.class);
        sessionFactory=aconf.buildSessionFactory();
    }

    @Override
    public void saveWeatherInfo(String serviceName, String city, WeatherInfo weatherInfo) throws Exception{
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();

            Weather weather = new Weather();
            weather.setServiceName(serviceName);
            weather.setCity(city);
            weather.setWeatherDate(new Date());
            weather.setTemperatura(weatherInfo.getTemperatura());
            weather.setAtmosphericPressure(weatherInfo.getAtmosphericPressure());
            weather.setHumidity(weatherInfo.getHumidity());
            weather.setPrecipitation(weatherInfo.getWeather());

            transaction = session.beginTransaction();
            transaction.begin();
            session.save(weather);
            transaction.commit();
            transaction=null;
        } catch (Throwable throwable){
            throw new DatabaseException(throwable);
        }finally {
            if (transaction!=null)
                transaction.rollback();
            if (session!=null)
                session.close();
        }
    }

    @Override
    public List<Weather> searchWeather(String city, String serviceName, Date startDate, Date endDate) throws Exception {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Weather.class);
            criteria.addOrder(Order.desc("weatherDate"));
            if (city!=null && StringUtils.isNotBlank(city))
                criteria.add(Expression.eq("city", city));
            if (serviceName!=null && StringUtils.isNotBlank(serviceName))
                criteria.add(Expression.eq("serviceName", serviceName));
            else
                criteria.add(Expression.in("serviceName", Settings.getInstance().getRemoteServiceNames()));

            if(startDate!=null){
                criteria.add(Expression.ge("weatherDate",startDate));
            }
            if(endDate!=null){
                criteria.add(Expression.le("weatherDate",endDate));
            }

            return criteria.list();
        } catch (Throwable throwable){
            throw new DatabaseException(throwable);
        }finally {
            if (session!=null)
                session.close();
        }
    }
}
