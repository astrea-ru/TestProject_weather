package ru.my.service.remote.services.yahooweather;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import ru.my.Settings;
import ru.my.exceptions.ParseException;
import ru.my.service.remote.services.IConverter;
import ru.my.service.remote.services.WeatherInfo;

import java.io.InputStream;

public class YahooXmlConverter implements IConverter{
    
    private String PREFIX="yweather";
    private String NAMESPACE_URL="http://xml.weather.yahoo.com/ns/rss/1.0";
    
    @Override
    public WeatherInfo convert(String response) throws Exception {
        try{
            InputStream inputStream = IOUtils.toInputStream(response);

            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build( inputStream );

            Element channel = doc.getRootElement().getChild("channel");
            Namespace namespace = Namespace.getNamespace(PREFIX, NAMESPACE_URL);
            Element yweather_atmosphere = channel.getChild("atmosphere", namespace) ;
            Element yweather_condition = channel.getChild("item").getChild("condition", namespace);

            Long temperatura = new Long(yweather_condition.getAttributeValue("temp"));
            Long atmosphericPressure = Double.valueOf(yweather_atmosphere.getAttributeValue("pressure")).longValue();
            Long humidity = new Long(yweather_atmosphere.getAttributeValue("humidity"));
            int clouds = new Integer(yweather_condition.getAttributeValue("code"));

            IOUtils.closeQuietly(inputStream);

            WeatherInfo info = new WeatherInfo();
            info.setAtmosphericPressure(atmosphericPressure);
            info.setHumidity(humidity);
            info.setTemperatura(temperatura);
            info.setWeather(Settings.getInstance().getYahooWeatherCloud(clouds));
            return info;
        } catch (Exception e){
            throw new ParseException(e);
        }
    }
}
