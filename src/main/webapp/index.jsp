<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.my.database.tables.Weather" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Сервис информации о погоде</title>
        <link type="text/css" rel="stylesheet" href="style.css">
    </head>
    <body>
    <div id="allcontent">
        <p class="sun"><img src="img/day_.png" alt=""></p>
        <p class="night"><img src="img/night_.png" alt=""></p>
        <h1>Наши сведения о погоде</h1>
        <div name="current_weather">
        </div>
        <div name="search_panel">

            <form action="" method="get">
                <table class="search_panel">
                    <tr>
                        <td>
                            <strong>Выбери город:</strong>
                            <select name="CITY">
                                <option value=""></option>
                                <% for (String city : (List<String>)request.getAttribute("city_list")) {%>
                                <option value=<%= city%>><%= city%></option>
                                <% } %>
                            </select>
                        </td>
                        <td>
                            <strong>Выбери интересующий сервис:</strong>
                            <select name="SERVICE_NAME">
                                <option value=""></option>
                                <% for (String name : (List<String>)request.getAttribute("service_list")) {%>
                                <option value=<%= "'"+name+"'"%>><%= name%></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Период, за который интересны данные:</strong></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <strong>С:</strong>
                            <input type="datetime-local" name="START_DATE">
                        </td>
                        <td>
                            <strong>ПО:</strong>
                            <input type="datetime-local" name="END_DATE">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="ПОИСК" name="submit"/></td>
                    </tr>
                </table>
            </form>

        </div>
        <div name="all_weather">
            <table class="weather_data">
                <tr>
                    <td><b>Город</b></td>
                    <td><b>Осадки</b></td>
                    <td><b>Температура</b></td>
                    <td><b>Дата</b></td>
                    <td><b>Сервис</b></td>
                </tr>

                <% for (Weather weather : (List<Weather>)request.getAttribute("weather_list")) {%>

                <tr>
                    <%SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");%>
                    <td><%= weather.getCity()%></td>
                    <td><%= weather.getPrecipitation()%></td>
                    <td><%= weather.getTemperatura()%></td>
                    <td><%= format.format(weather.getWeatherDate())%></td>
                    <td><%= weather.getServiceName()%></td>
                </tr>
                <% }%>

            </table>
        </div>
    </div>
    </body>
</html>