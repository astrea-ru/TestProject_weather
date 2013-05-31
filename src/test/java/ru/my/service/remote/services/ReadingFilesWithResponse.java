package ru.my.service.remote.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingFilesWithResponse {

    public static String readResponseFromFile(String path) throws Exception{
        InputStream inputStream = ReadingFilesWithResponse.class.getResourceAsStream(path);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        String result="";

        while ((inputLine = in.readLine()) != null) {
            result+=inputLine;
        }

        in.close();

        return result;
    }
}
