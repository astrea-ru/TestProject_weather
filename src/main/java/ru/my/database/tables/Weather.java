package ru.my.database.tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WEATHER")
public class Weather {
    //идентиифкатор
    @Id
    @Column(name = "WEATHER_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="WEATHER_ID_SEQ")
    @SequenceGenerator(name="WEATHER_ID_SEQ", sequenceName="WEATHER_ID_SEQ")
    private Long id;
    //Город
    @Column(name= "CITY", nullable = false, length = 100)
    private String city;
    //Дата
    @Column(name= "WEATHER_DATE")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date weatherDate;
    //Температура
    @Column(name= "TEMPERATURA", nullable = false)
    private Long temperatura;
    //Давление
    @Column(name= "ATMOSPHERIC_PRESSURE", nullable = false)
    private Long atmosphericPressure;
    //Влажность
    @Column(name= "HUMIDITY", nullable = false)
    private Long humidity;
    //Осадки
    @Column(name= "PRECIPITATION", nullable = false, length = 100)
    private String precipitation;
    //Имя сервиса, от которого получили результат
    @Column(name= "SERVICE_NAME", nullable = false, length = 100)
    private String serviceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(Long atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return serviceName;
    }
}
