package ru.my.service.remote;

import ru.my.exceptions.RestClientException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient implements IClient{
    @Override
    public String getResponse(String address) throws RestClientException {
        String result="";

        try{
            URL url = new URL(address);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("User-Agent", "Java bot");

            conn.connect();

            int code=conn.getResponseCode();

            if (code==200) {
               BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

               String inputLine;

               while ((inputLine = in.readLine()) != null) {
                   result+=inputLine;
               }

               in.close();
             }

             conn.disconnect();
             conn=null;
        }catch (Exception e){
            throw  new RestClientException(e);
        }

        return result;
    }
}
