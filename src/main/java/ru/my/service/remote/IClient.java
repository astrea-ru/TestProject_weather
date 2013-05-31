package ru.my.service.remote;

import java.net.MalformedURLException;
import java.net.URL;

public interface IClient {

    String getResponse(String address) throws Exception;

}
