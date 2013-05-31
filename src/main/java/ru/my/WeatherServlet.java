package ru.my;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang.StringUtils;
import ru.my.database.DatabaseService;
import ru.my.database.IDatabaseService;
import ru.my.database.tables.Weather;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Singleton
public class WeatherServlet extends HttpServlet {

    protected IDatabaseService service;

    @Inject
    protected void setDatabaseService(IDatabaseService service){
        this.service = service;
    }

    protected Date getDate(String value) throws Exception{
        if (value==null || !StringUtils.isNotBlank(value))
            return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return format.parse(value);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        String city = request.getParameter("CITY");
        String serviceName = request.getParameter("SERVICE_NAME");
        Date startDate = getDate(request.getParameter("START_DATE"));
        Date endDate = getDate(request.getParameter("END_DATE"));
        List<Weather> list=service.searchWeather(city, serviceName, startDate, endDate);
        request.setAttribute("weather_list", list);
        request.setAttribute("city_list", Arrays.asList(Settings.getInstance().getCityRU()));
        request.setAttribute("service_list", Arrays.asList(Settings.getInstance().getRemoteServiceNames()));
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
