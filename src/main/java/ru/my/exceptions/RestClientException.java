package ru.my.exceptions;

public class RestClientException extends Exception{
    
    private static String MESSAGES="Ошибка при подключении к удаленному сервису";

    public RestClientException(Throwable throwable){
        super(MESSAGES, throwable);
    }
}
